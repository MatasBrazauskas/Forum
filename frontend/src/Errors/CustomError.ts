abstract class CustomError extends Error{
    public additionalInfo: string;
    constructor(message: string, additionalInfo: string){
        super(message);
        this.additionalInfo = additionalInfo;
        this.name = 'CustomError'
        console.error(this.message);
        console.error(this.additionalInfo);
    }
}

class UnauthorizedError extends CustomError {
    constructor(path: string, apiCall:string, additionalInfo: string){
        super(`User is not authrorized for this enpoint: ${path}, call ${apiCall}`, additionalInfo);
    }
}

class NotFoundError extends CustomError{
    constructor(path: string, apiCall: string, additionalInfo: string){
        super(`Not Found error from this endpoint: ${path}, call ${apiCall}`, additionalInfo);
    }
}

export { UnauthorizedError, NotFoundError };
