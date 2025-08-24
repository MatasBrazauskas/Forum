import { REGISTER_REQUEST } from "./const";
import exceptionHandler from "../Errors/exceptionHandler";
import { type CreateUserCookiesDTO } from "../Utils/RequestDTOs";

async function logInOrRegister(credentials: CreateUserCookiesDTO){
    //try{
        const response = await fetch(REGISTER_REQUEST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
            credentials: 'include'
        })

        return exceptionHandler(response, REGISTER_REQUEST, 'POST');
    /*} catch (e){
        console.error(e);
        console.warn('Error is getting users info')
    }
    throw new Error('Failed to fecth for authentication');*/
}

export default logInOrRegister;