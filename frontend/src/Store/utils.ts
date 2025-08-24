export type UserInformation = {
    username: string,
    role: ROLES,
    dateOfCreation?: string,
    lastOnline?: string,
    postCount?: number,
    reputation?: number,
}

export type ROLES = "ADMIN" | "USER" | "GUEST"

export const USER_INFO = 'userInfo';

export type ErrorsInformation = {
    errors: string[],
}

export const ERRORS_INFO = 'errorInfo';