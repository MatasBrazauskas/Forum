import { HTTP_CODES, type AddTopicsDTO } from "./const";
import { TOPICS_REQUEST } from "./const";

async function addNewTopic(addTopic: AddTopicsDTO){
    try{
        const response = await fetch(TOPICS_REQUEST,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(addTopic),
            credentials: 'include'
        });

        console.log(response);

        if(response.status === HTTP_CODES.OK){
            console.warn("Everythink is ok with addiction of topic");
        }
    } catch(e) {
        console.error(e);
    }
    console.warn("There is somethink bad about the addition of topic.");
}

export default addNewTopic;