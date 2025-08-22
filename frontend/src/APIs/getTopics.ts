import { HTTP_CODES, TOPICS_REQUEST } from "./const";
import { type GetTopicsDTO } from "../Utils/ResponseDTOs";

async function getTopics(): Promise<GetTopicsDTO[]>{
    try{
        const response = await fetch(TOPICS_REQUEST, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json",
            }
        }); 


        if(response.status === HTTP_CODES.OK){
            return await response.json();
        }

    } catch(e){
        console.error(e);
        console.warn('Error is on getting topics');
    }
    return [];
}

export default getTopics;