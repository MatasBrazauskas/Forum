import { REGISTER_REQUEST, HTTP_CODES } from "./const";
import { type UserInformation } from "../Store/utils";

type temp = {
    username:string,
    email: string,
}

async function authRequest(credentials: temp){
    try{
        const response = await fetch(REGISTER_REQUEST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
            credentials: 'include'
        })

        const data: UserInformation = await response.json();

        if(response.status === HTTP_CODES.OK){
            return data;
        }
    } catch (e){
        console.error(e);
        console.warn('Error is getting users info')
    }
    throw new Error('Failed to fecth for authentication');
}

export default authRequest;