import { GoogleLogin } from "@react-oauth/google";

function GoogleOAuth(){

    return (
        <div>
            <GoogleLogin onSuccess={() => alert('Google OK')}/>
        </div>
    );
}

export default GoogleOAuth;