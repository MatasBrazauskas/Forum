import { HTTP_CODES } from "../APIs/const";
import { UnauthorizedError, NotFoundError } from "./CustomError";

async function exceptionHandler(response: Response, path: string, apiCall: string) {
    switch(response.status){
        case HTTP_CODES.OK:
            return await response.json();
        case HTTP_CODES.UNAUTHORIZED:
            throw new UnauthorizedError(path, apiCall, await response.text());
        case HTTP_CODES.NOT_FOUND:
            throw new NotFoundError(path, apiCall, await response.text());
    }
}

export default exceptionHandler;