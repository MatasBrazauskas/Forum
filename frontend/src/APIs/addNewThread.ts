import { HTTP_CODES, THREADS_REQUEST, type ThreadsInfo } from "./const";

async function addNewThread(topicsName: string): Promise<ThreadsInfo | null>{
    try{
        const response = await fetch(`${THREADS_REQUEST}/${topicsName}`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({}), 
        });

        const data = await response.json();

        if(response.status === HTTP_CODES.OK){
            return data;
        }

    } catch(e){
        console.error(e);
    }
    return null;
}

export default addNewThread;