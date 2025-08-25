import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { useRef } from 'react';

import { useWebSocketComment } from "../Hooks/useWebSocketComment";
import getPosts from "../APIs/getPosts";
import { type AddCommentDTO } from "../Utils/RequestDTOs";
import { COMMENTS_QUERY_KEY, COMMENTS_STALE_TIME } from "../Utils/queryConsts";
import { TypeConverter, type GetCommentDTO } from "../Utils/ResponseDTOs";
import { COMMENTS_LENGTH } from "../Utils/inputLengths";
import { queryClient } from "../main";

import './commentStyle.css';

function PostPage(){
    const { threadsName } = useParams();

    const postQuery = useQuery({
        queryKey: [COMMENTS_QUERY_KEY, threadsName],
        queryFn: () => getPosts(threadsName!),
        staleTime: COMMENTS_STALE_TIME,
    })

    const { typing, sendComment, sendTyping } = useWebSocketComment();    
    const comment = useRef<HTMLInputElement>(null);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const data: AddCommentDTO = {
            threadName: threadsName!,
            comment: comment?.current?.value!,
            replyId: -1,
        }

        sendComment(data);
        queryClient.invalidateQueries({queryKey: [COMMENTS_QUERY_KEY, threadsName]});
    }

    const commentInputOnChange = () => {
        sendTyping('Matas Brazauskas');
        console.log(`What is this? ${typing}`);
    }

    return (
        <div className='commentPage'>
            {postQuery.isSuccess && <CommentAndProfileTile commentInfo={TypeConverter(postQuery?.data!)}/>}

            {!!typing && <div>{typing}</div>}
            <form onSubmit={(e) => handleSubmit(e)}>
                <input type='text' maxLength={COMMENTS_LENGTH} ref={comment} placeholder='Enter comment' onChange={() => commentInputOnChange()}/>
                <button type='submit'>Sumbit</button>
            </form>
                {postQuery?.data?.comments.map((comment) => {
                    return (
                        <CommentAndProfileTile commentInfo={comment}/>
                    )
                })}
        </div>
    )
}

const CommentAndProfileTile = ({commentInfo}: {commentInfo: GetCommentDTO}) => {
    return (
        <div key={commentInfo.commentId} className='commentTile'>
            <div className='profile'>
                <div className='items-center text-lg font-bold'>
                    <div className='items-center'>{commentInfo?.partialProfile.username}</div>
                </div>
                <div className='flex justify-between bg-zinc-600 p-2 text-sm'>
                    <div>Joined:</div>
                    <div>{commentInfo?.partialProfile.joined}</div>
                </div>
                <div className='flex justify-between bg-zinc-600 p-2 text-sm'>
                    <div>Posts:</div>
                    <div>{commentInfo?.partialProfile.postCount}</div>
                </div>
                <div className='flex justify-between bg-zinc-600 p-2 text-sm'>
                    <div>Reputation:</div>
                    <div>{commentInfo?.partialProfile.reputation}</div>
                </div>
            </div>

            <div className='text'>
                <div className='top'>{commentInfo?.dateOfCreation}</div>
                <div className='bottom'>{commentInfo?.comment}</div>
            </div>
        </div>
    )
}

export default PostPage;