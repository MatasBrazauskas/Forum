import { REGISTER_REQUEST, HTTP_CODES } from "./const";
import { UnauthorizedError } from "../Errors/CustomError";
import { type CreateUserCookiesDTO } from "../Utils/RequestDTOs";

async function logInOrRegister(credentials: CreateUserCookiesDTO){
    try{
        const response = await fetch(REGISTER_REQUEST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
            credentials: 'include'
        })

        console.warn(response);

        switch(response.status){
            case HTTP_CODES.OK:
                return await response.json();
            case HTTP_CODES.UNAUTHORIZED:
                throw new UnauthorizedError(REGISTER_REQUEST, "API call getting session and persistent cookies.");
        }
    } catch (e){
        console.error(e);
        console.warn('Error is getting users info')
    }
    throw new Error('Failed to fecth for authentication');
}

export default logInOrRegister;