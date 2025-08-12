const COOKIE_CONTROLLER_URL:string = import.meta.env.VITE_COOKIE_CONTROLLER;

export const REGISTER_REQUEST = `${COOKIE_CONTROLLER_URL}/register`;
export const LOGIN_REQUEST = `${COOKIE_CONTROLLER_URL}/login`;

export enum HTTP_CODES {
    OK = 200,
    UNAUTHORIZED = 401,
}