const COOKIE_CONTROLLER_URL:string = import.meta.env.VITE_COOKIE_CONTROLLER;
const TOPICS_CONTROLLER_URL:string = import.meta.env.VITE_TOPICS_CONTROLLER;
const USERS_CONTROLLER_URL:string = import.meta.env.VITE_USERS_CONTROLLER;

export const REGISTER_REQUEST = `${COOKIE_CONTROLLER_URL}/register`;
export const LOGIN_REQUEST = `${COOKIE_CONTROLLER_URL}/login`;
export const TOPICS_REQUEST = `${TOPICS_CONTROLLER_URL}`;
export const USERS_PROFILE_REQUEST = `${USERS_CONTROLLER_URL}`;

export enum HTTP_CODES {
    OK = 200,
    BAD_REQUEST = 400,
    UNAUTHORIZED = 401,
}

export type TOPICS = 
    |"INFORMATION" 
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