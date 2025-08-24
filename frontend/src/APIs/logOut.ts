import { COOKIE_CONTROLLER_URL } from "./const"
import exceptionHandler from "../Errors/exceptionHandler"

async function logOut(){
    const response = await fetch(COOKIE_CONTROLLER_URL, {
        method: 'DELETE',
        credentials: 'include'
    })

    return exceptionHandler(response, COOKIE_CONTROLLER_URL, 'DELETE');
}

export default logOut;