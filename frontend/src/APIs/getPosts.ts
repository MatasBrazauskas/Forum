import { POSTS_CONTROLLER_URL} from "./const";
import { type GetCommentsDTO} from "../Utils/ResponseDTOs";
import exceptionHandler from "../Errors/exceptionHandler";
import { percentEncoding } from "./const";

async function getPosts(threadsName: string): Promise<GetCommentsDTO>{
    //try{
        const response = await fetch(percentEncoding(POSTS_CONTROLLER_URL, threadsName), {
            method: 'GET',
            credentials: 'include'
        });

        return exceptionHandler(response, percentEncoding(POSTS_CONTROLLER_URL, threadsName), 'GET');

        /*console.log(response);

        if(response.status === HTTP_CODES.OK){
            return await response.json();
        }

    } catch(e){
        console.error(e);
    }
    throw new Error('Blet getting the threads info with comments');*/
}

export default getPosts;