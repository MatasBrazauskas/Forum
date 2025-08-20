import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import { useWebSocketComment } from "../Hooks/useWebSocketComment";
import getPosts from "../APIs/getPosts";

function PostPage(){
    const { comments, } = useWebSocketComment();
    const { threadsName } = useParams();


    const postQuery = useQuery({
        queryKey: ['posts'],
        queryFn: () => getPosts(threadsName!),
        staleTime: 60 * 10 * 1000,
    })

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
        </div>
    )
}

export default PostPage;