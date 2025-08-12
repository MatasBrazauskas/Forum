import { REGISTER_REQUEST, HTTP_CODES } from "./const";
import { type UserInformation } from "../Store/utils";

async function authRequest(username: string, email: string){
    try{
        const response = await fetch(REGISTER_REQUEST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({'username' : username, 'email' : email}),
            credentials: 'include'
        })

        const data: UserInformation = await response.json();
        console.log(data);

        if(response.status === HTTP_CODES.OK){
            return data;
        }

        if(response.status === HTTP_CODES.UNAUTHORIZED){
            throw new Error('UNAUTHRIZED');
        }
    } catch (e){
        console.error(e);
    }
    throw new Error('Failed to fecth for authentication');
}

export default authRequest;