export type UserInformation = {
    username: string,
    role: ROLES,
    dateOfCreation?: string,
    lastOnline?: string,
    postCount?: string,
    reputation?: string,
}

export type ROLES = "ADMIN" | "USER" | "GUEST"

export const USER_INFO = 'userInfo';

export type ErrorInfo = {
    message: string,
    info: string,
}

export type ErrorsInformation = {
    errors: ErrorInfo[],
}

export const ERRORS_INFO = 'errorInfo';