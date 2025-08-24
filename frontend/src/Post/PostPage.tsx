import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { useRef } from 'react';

import { useWebSocketComment } from "../Hooks/useWebSocketComment";
import getPosts from "../APIs/getPosts";
import { type AddCommentDTO } from "../Utils/RequestDTOs";
import { COMMENTS_QUERY_KEY, COMMENTS_STALE_TIME } from "../Utils/queryConsts";
import type { PartialProfileInfoDTO } from "../Utils/ResponseDTOs";

function PostPage(){
    const { threadsName } = useParams();

    const postQuery = useQuery({
        queryKey: [COMMENTS_QUERY_KEY],
        queryFn: () => getPosts(threadsName!),
        staleTime: COMMENTS_STALE_TIME,
    })

    const { sendComment } = useWebSocketComment();    
    const comment = useRef<HTMLInputElement>(null);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const data: AddCommentDTO = {
            threadName: threadsName!,
            comment: comment?.current?.value!,
            replyId: -1,
        }

        sendComment(data);
    }

    return (
        <div>
            <PartialUserProfile partialProfile={postQuery?.data?.partialProfile!} i={-1}/>
            <div>
                <div>{postQuery?.data?.title}</div>
                <div>{postQuery?.data?.content}</div>
                <div>{postQuery?.data?.contentDateOfCreation}</div>
            </div>

            <form onSubmit={(e) => handleSubmit(e)}>
                <input type='text' ref={comment} placeholder='Enter comment'/>
                <button type='submit'>Sumbit</button>
            </form>

            {postQuery?.data?.comments?.map((comment, i) => {
                return (
                    <PartialUserProfile partialProfile={comment.partialProfile} i={i}/>
                )
            })}
        </div>
    )
}

const PartialUserProfile = ({partialProfile, i}: {partialProfile: PartialProfileInfoDTO, i: number}) => {
    return (
        <div key={i}>
            <div>{partialProfile?.username}</div>
            <div>{partialProfile?.joined}</div>
            <div>{partialProfile?.postCount}</div>
            <div>{partialProfile?.reputation}</div>
        </div>
    )
}

export default PostPage;