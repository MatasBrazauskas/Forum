import { HTTP_CODES, USERS_PROFILE_REQUEST } from "./const";
import { type UserInformation } from "../Store/utils";

async function getUsersProfile(): Promise<UserInformation>{
    try{
        const response = await fetch(USERS_PROFILE_REQUEST, {
            method: 'GET',
            credentials: 'include',
        })

        const data: UserInformation = await response.json();

        if(response.status === HTTP_CODES.OK){
            return data;
        }
    }catch(e){
        console.error(e);
        console.warn('Error with gettin users info');
    }
    throw new Error('This is error for gettin users info');
}

export default getUsersProfile;