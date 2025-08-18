import { HTTP_CODES, THREADS_REQUEST, type AddThreadDTO } from "./const";

async function addNewThread({topicsName, title, content}: AddThreadDTO ): Promise<null | undefined>{
    try{
        const response = await fetch(`${THREADS_REQUEST}/${topicsName}`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title: title, content: content}), 
        });

        console.warn(response);

        if(response.status === HTTP_CODES.OK){
            return null;
        }

    } catch(e){
        console.error(e);
    }
    return undefined;
}

export default addNewThread;