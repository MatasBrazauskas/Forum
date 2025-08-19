import { useParams } from "react-router-dom";
import { useWebSocketComment } from "../Hooks/useWebSocketComment";

function PostPage(){
    const { comments, typing, sendComment, sendTyping } = useWebSocketComment();
    const { threadsName } = useParams();

    return (
        <div>
            <div>{threadsName}</div>
        </div>
    )
}

export default PostPage;