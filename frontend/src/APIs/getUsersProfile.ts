import { USERS_PROFILE_REQUEST } from "./const";
import { type UserInformation } from "../Store/utils";
import exceptionHandler from "../Errors/exceptionHandler";

async function getUsersProfile(): Promise<UserInformation>{
    //try{
        const response = await fetch(USERS_PROFILE_REQUEST, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json"
            }
        })

        return exceptionHandler(response, USERS_PROFILE_REQUEST, 'GET');

    /*}catch(e){
        console.error(e);
        console.warn('Error with gettin users info');
    }
    throw new Error('This is error for gettin users info');*/
}

export default getUsersProfile;