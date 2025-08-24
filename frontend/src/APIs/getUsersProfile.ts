import { USERS_CONTROLLER_URL} from "./const";
import { type UserInformation } from "../Store/utils";
import exceptionHandler from "../Errors/exceptionHandler";

async function getUsersProfile(): Promise<UserInformation>{
    //try{
        const response = await fetch(USERS_CONTROLLER_URL, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json"
            }
        })

        return exceptionHandler(response, USERS_CONTROLLER_URL, 'GET');

    /*}catch(e){
        console.error(e);
        console.warn('Error with gettin users info');
    }
    throw new Error('This is error for gettin users info');*/
}

export default getUsersProfile;