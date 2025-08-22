import { HTTP_CODES, THREADS_REQUEST } from "./const";
import { type AddThreadDTO } from "../Utils/RequestDTOs";

async function addNewThread(addThreadDTO: AddThreadDTO ): Promise<null | undefined>{
    try{
        const response = await fetch(THREADS_REQUEST, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(addThreadDTO), 
        });

        console.warn(response);

        if(response.status === HTTP_CODES.OK){
            return null;
        }

    } catch(e){
        console.error(e);
    }
    return undefined;
}

export default addNewThread;