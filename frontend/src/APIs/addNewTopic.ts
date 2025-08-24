import { TOPICS_CONTROLLER_URL} from "./const";
import { type AddTopicsDTO } from "../Utils/RequestDTOs";
import exceptionHandler from "../Errors/exceptionHandler";

async function addNewTopic(addTopic: AddTopicsDTO){
    //try{
        const response = await fetch(TOPICS_CONTROLLER_URL,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(addTopic),
            credentials: 'include'
        });

        return exceptionHandler(response, TOPICS_CONTROLLER_URL, 'POST');

        /*console.log(response);

        if(response.status === HTTP_CODES.OK){
            console.warn("Everythink is ok with addiction of topic");
        }
    } catch(e) {
        console.error(e);
    }
    console.warn("There is somethink bad about the addition of topic.");*/
}

export default addNewTopic;