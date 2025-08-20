import { HTTP_CODES, POST_REQUEST, type PostInfo } from "./const";

async function getPosts(threadsName: string): Promise<PostInfo>{
    try{
        const response = await fetch(POST_REQUEST + `/${threadsName}`, {
            method: 'GET',
            credentials: 'include'
        });

        console.log(response);

        if(response.status === HTTP_CODES.OK){
            return await response.json();
        }

    } catch(e){
        console.error(e);
    }
    throw new Error('Blet getting the threads info with comments');
}

export default getPosts;