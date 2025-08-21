const COOKIE_CONTROLLER_URL:string = import.meta.env.VITE_COOKIE_CONTROLLER;
const TOPICS_CONTROLLER_URL:string = import.meta.env.VITE_TOPICS_CONTROLLER;
const USERS_CONTROLLER_URL:string = import.meta.env.VITE_USERS_CONTROLLER;
const THREADS_CONTROLLER_URL:string = import.meta.env.VITE_THREADS_CONTROLLER;
const POSTS_CONTROLLER_URL:string = import.meta.env.VITE_POST_CONTROLLER;


export const REGISTER_REQUEST = `${COOKIE_CONTROLLER_URL}/register`;
export const LOGIN_REQUEST = `${COOKIE_CONTROLLER_URL}/login`;
export const TOPICS_REQUEST = TOPICS_CONTROLLER_URL;
export const USERS_PROFILE_REQUEST = USERS_CONTROLLER_URL;
export const THREADS_REQUEST = THREADS_CONTROLLER_URL;
export const POST_REQUEST = POSTS_CONTROLLER_URL;

export enum HTTP_CODES {
    OK = 200,
    BAD_REQUEST = 400,
    NO_CONTENT = 204,
    UNAUTHORIZED = 401,
}

export type TOPICS = 
    | "INFORMATION" 
    | "GENERAL"

export type TopicsInfo = {
    topicsName: string,
    username: string
    created:string,
    description: string
    threadCount: number
    postCount: number
    topicType: TOPICS,
}

export type AddTopicsDTO = {
    topicsName: string,
    description: string,
    topicType: string,
}

export type ThreadsInfo = {
    username: string;
    lastOnline: string;
    title: string;
    dateOfCreation: string;
    commentCount: number;
    upvoteCount: number;    
}

export type ThreadsDTO = {
    topicsName: string,
    description: string,
    threads: ThreadsInfo[],
}

export type AddThreadDTO = {
    topicsName: string,
    title: string,
    content: string,
}

export type GetCommentsDTO = {
    username: string;
    joined: string;
    postCount: number;
    reputation: number;

    dateOfCreation: string;
    comment: string;

    reply: string;
    commentId: number;
}

export type PostInfo = {
    title: string,
    content: string,
    dateOfCreation: string,
    commentCount: number,
    upvoteCount: number,
    commentId: number
    comments: GetCommentsDTO[],
}

export type AddCommentDTO = {
    threadName: string;
    comment: string;
    replyId: number;
}