import { HTTP_CODES, THREADS_REQUEST, type ThreadsDTO } from "./const";

async function getThreads(topicsName: string): Promise<ThreadsDTO | null> {
    try{
        const response = await fetch(`${THREADS_REQUEST}/${topicsName}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })

        const data = await response.json();

        if(response.status === HTTP_CODES.OK)
            return data;

    } catch(e){
        console.error(e);
        console.warn('Error with gettin topics threads');
    }
    return null;
}

export default getThreads;