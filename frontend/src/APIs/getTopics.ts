import { HTTP_CODES, TOPICS_REQUEST } from "./const";

async function getTopics() {
    try {
        const response = await fetch(TOPICS_REQUEST, {
            method: 'GET',
        });

        const data = await response.json();

        if(response.status === HTTP_CODES.OK)
        {
            return data;
        }
    } catch(e){
        console.error(e);
    }
    return [];
}

export default getTopics;