import { GoogleLogin } from "@react-oauth/google";
import { useMutation } from "@tanstack/react-query";
import { type CredentialResponse } from "@react-oauth/google";
import { jwtDecode } from "jwt-decode";

import logInOrRegister from '../../APIs/logInOrRegister';

export type Register = {
    name: string,
    email: string,
}

function GoogleOAuth(){

    const { isError, error, mutate } = useMutation({
        mutationFn: async (e: CredentialResponse) => {
            const data:Register = jwtDecode(e.credential!);
            await logInOrRegister({ username: data?.name!, email: data?.email!})
    }});

    return (
        <div>
            {isError && <div>{error?.message}</div>}
            <GoogleLogin onSuccess={(e) => mutate(e)}/>
        </div>
    );
}

export default GoogleOAuth;