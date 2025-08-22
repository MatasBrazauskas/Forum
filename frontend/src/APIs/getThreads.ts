import { HTTP_CODES, THREADS_REQUEST } from "./const";
import { type GetThreadsDTO } from "../Utils/ResponseDTOs";


async function getThreads(topicsName: string): Promise<GetThreadsDTO> {
    try{
        const response = await fetch(`${THREADS_REQUEST}/${topicsName}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })

        if(response.status === HTTP_CODES.OK)
            return await response.json();

    } catch(e){
        console.error(e);
        console.warn('Error with gettin topics threads');
    }
    throw new Error('IDK THO');
}

export default getThreads;