import { THREADS_CONTROLLER_URL} from "./const";
import { type AddThreadDTO } from "../Utils/RequestDTOs";
import exceptionHandler from "../Errors/exceptionHandler";

async function addNewThread(addThreadDTO: AddThreadDTO ): Promise<null | undefined>{
    //try{
        const response = await fetch(THREADS_CONTROLLER_URL, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(addThreadDTO), 
        });

        return exceptionHandler(response, THREADS_CONTROLLER_URL, 'POST');

        /*console.warn(response);

        if(response.status === HTTP_CODES.OK){
            return null;
        }

    } catch(e){
        console.error(e);
    }
    return undefined;*/
}

export default addNewThread;