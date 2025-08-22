import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { useRef } from 'react';

import { useWebSocketComment } from "../Hooks/useWebSocketComment";
import getPosts from "../APIs/getPosts";
import { type AddCommentDTO } from "../Utils/RequestDTOs";

function PostPage(){
    const { threadsName } = useParams();

    const postQuery = useQuery({
        queryKey: ['posts'],
        queryFn: () => getPosts(threadsName!),
        staleTime: 60 * 10 * 1000,
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

        console.warn('Sending data with WebSocket');

        sendComment(data);
    }

    return (
        <div>
            <div>{postQuery?.data?.title}</div>
            <div>{postQuery?.data?.content}</div>
            <div>{postQuery?.data?.dateOfCreation}</div>

            {postQuery?.data?.comments?.map((comment, i) => {
                return (
                    <div key={i}>
                        <div>{comment.username}</div>
                        <div>{comment.dateOfCreation}</div>
                        <div>{comment.comment}</div>
                        <div>{comment.reply}</div>
                    </div>
                )
            })}


            <form onSubmit={(e) => handleSubmit(e)}>
                <input type='text' ref={comment} placeholder='Enter comment'/>
                <button type='submit'>Sumbit</button>
            </form>

        </div>
    )
}

export default PostPage;