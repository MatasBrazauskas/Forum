import { HTTP_CODES, TOPICS_REQUEST, type TopicsInfo } from "./const";

async function getTopics(): Promise<TopicsInfo[]>{
    try{
        const response = await fetch(TOPICS_REQUEST, {
            method: 'GET',
            credentials: 'include',
            headers: {
                "Content-Type":"application/json",
            }
        }); 


        if(response.status === HTTP_CODES.OK){
            const data: TopicsInfo[] = await response.json();
            return data;
        }

    } catch(e){
        console.error(e);
        console.warn('Error is on getting topics');
    }
    return [];
}

export default getTopics;