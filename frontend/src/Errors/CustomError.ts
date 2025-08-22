abstract class CustomError extends Error{
    constructor(message: string){
        super(message);
        this.name = 'CustomError'
        console.error(this.message);
    }
}

class UnauthorizedError extends CustomError {
    constructor(path: string, apiCall:string){
        super(`User is not authrorized for this enpoint: ${path}, call ${apiCall}`);
    }
}

class NotFoundError extends CustomError{
    constructor(path: string, apiCall: string){
        super(`Not Found error from this endpoint: ${path}, call ${apiCall}`);
    }
}

export { UnauthorizedError, NotFoundError };
