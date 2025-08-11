import GoogleOAuth from "./GoogleOAuth";
import FacebookOAuth from "./FacebookOAuth";
import TwitterOAuth from "./TwitterOAuth";

function Login(){

    return (
        <div>
            <GoogleOAuth />
            <FacebookOAuth />
            <TwitterOAuth />
        </div>
    )
}

export default Login;