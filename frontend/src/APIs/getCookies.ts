import { COOKIE_LOGIN } from "./const";
import { HTTP_CODES } from "./const";

async function getCookies(username: string): Promise<void>{

    console.warn('Getting cookies');

    try{
        const response = await fetch(COOKIE_LOGIN, {
            method: 'POST',
            body: JSON.stringify({'username' : username}),
            headers:{
                'Content-Type': 'application/json',
            },
        });

        console.log(await response);

        if(response.status === HTTP_CODES.OK){
            return;
        }
    } catch (e) {
        console.error(e);
    }
    throw new Error('Error with the cookies');
}

export default getCookies;