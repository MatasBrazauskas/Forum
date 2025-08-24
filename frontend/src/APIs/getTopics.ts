import { TOPICS_CONTROLLER_URL} from "./const";
import { type GetTopicsDTO } from "../Utils/ResponseDTOs";
import exceptionHandler from "../Errors/exceptionHandler";

async function getTopics(): Promise<GetTopicsDTO[]>{
    /*try{*/
        const response = await fetch(TOPICS_CONTROLLER_URL, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json",
            }
        }); 

        return exceptionHandler(response, TOPICS_CONTROLLER_URL, 'GET');
    /*} catch(e){
        console.error(e);
        console.warn('Error is on getting topics');
    }
    return [];*/
}

export default getTopics;