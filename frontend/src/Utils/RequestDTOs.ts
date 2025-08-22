///////////////////////////////////////
export type CreateUserCookiesDTO = {
    username: string,
    email: string,
}

//////////////////////////////
export type AddTopicsDTO = {
    topicsName: string,
    description: string,
    topicType: string,
}
/////////////////////////////
export type AddThreadDTO = {
    topicsName: string,
    title: string,
    content: string,
}

/////////////////////////////
export type AddCommentDTO = {
    threadName: string;
    comment: string;
    replyId: number;
}