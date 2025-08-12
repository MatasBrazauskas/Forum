import { LOGIN_REQUEST} from "./const";
import { HTTP_CODES } from "./const";

async function getCookies(): Promise<void>{
    try{
        const response = await fetch(LOGIN_REQUEST, {
            method: 'GET',
            credentials: 'include'
        });

        //const data = await response.json();
        //console.log(data);

        if(response.status === HTTP_CODES.OK){
            return;
        }
    } catch (e) {
        console.warn(e);
    }
    throw new Error('Error with the cookies');
}

export default getCookies;