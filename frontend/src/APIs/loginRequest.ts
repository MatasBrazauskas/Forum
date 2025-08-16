/*import type { UserInformation } from "../Store/utils";
import { LOGIN_REQUEST} from "./const";
import { HTTP_CODES } from "./const";

async function loginRequest(): Promise<UserInformation | null>{
    try{
        const response = await fetch(LOGIN_REQUEST, {
            method: 'GET',
            credentials: 'include'
        });

        const data: UserInformation = await response.json();

        if(response.status === HTTP_CODES.OK){
            return data;
        }
    } catch {
        return null;
    }

    return null;
}

export default loginRequest;*/