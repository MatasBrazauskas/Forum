import { useParams } from "react-router-dom";

function PostPage(){

    const { threadsName } = useParams();

    return (
        <div>
            <div>{threadsName}</div>
        </div>
    )
}

export default PostPage;