export const COOKIE_CONTROLLER_URL:string = import.meta.env.VITE_COOKIE_CONTROLLER;
export const TOPICS_CONTROLLER_URL:string = import.meta.env.VITE_TOPICS_CONTROLLER;
export const USERS_CONTROLLER_URL:string = import.meta.env.VITE_USERS_CONTROLLER;
export const THREADS_CONTROLLER_URL:string = import.meta.env.VITE_THREADS_CONTROLLER;
export const POSTS_CONTROLLER_URL:string = import.meta.env.VITE_POST_CONTROLLER;


export const REGISTER_REQUEST = `${COOKIE_CONTROLLER_URL}/register`;

export enum HTTP_CODES {
    OK = 200,
    NO_CONTENT = 204,
    BAD_REQUEST = 400,
    UNAUTHORIZED = 401,
    NOT_FOUND = 404,
}

export const percentEncoding = (url: string, ...queries: string[]) => {
    let addedUrl = url;

    for(let i = 0; i < queries.length; i++){
        addedUrl += `/${queries[i]}`;
    }
    return addedUrl;
}
