import { THREADS_CONTROLLER_URL} from "./const";
import { type GetThreadsDTO } from "../Utils/ResponseDTOs";
import { percentEncoding } from "./const";
import exceptionHandler from "../Errors/exceptionHandler";


async function getThreads(topicsName: string): Promise<GetThreadsDTO> {
    //try{
        const route = percentEncoding(THREADS_CONTROLLER_URL, topicsName)
        const response = await fetch(route, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })

        return exceptionHandler(response, route, 'GET');


    /*} catch(e){
        console.error(e);
        console.warn('Error with gettin topics threads');
    }
    throw new Error('IDK THO');*/
}

export default getThreads;