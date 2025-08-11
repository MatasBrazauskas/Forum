import { GoogleLogin } from "@react-oauth/google";
import { useMutation } from "@tanstack/react-query";

import getCookies from "../APIs/getCookies";

function GoogleOAuth(){

    const { isError, error, mutate } = useMutation({
        mutationFn: (username:string) => getCookies(username)
    });

    return (
        <div>
            {isError && <div>{error?.message}</div>}
            <GoogleLogin onSuccess={() => mutate("Matas Brazauskas")}/>
        </div>
    );
}

export default GoogleOAuth;