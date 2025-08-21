abstract class CustomError extends Error{
    constructor(message: string){
        super(message);
        this.name = 'CustomError'
    }
}

class UnauthorizedError extends CustomError {

    constructor(path: string, additionalInfo: string){
        super(`User is not authrorized for this enpoint: ${path}`);
        console.error(this.message);
        console.error(additionalInfo);
    }
}

export { UnauthorizedError };
