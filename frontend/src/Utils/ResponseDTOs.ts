/////////////////////////////////////
export type GetTopicsDTO = {
    topicsName: string,
    username: string
    created:string,
    description: string
    threadCount: number
    postCount: number
    topicType: topics,
}
///////////////////////////
export type topics = 
    | 'INFORMATION' 
    | 'GENERAL';

    ////////////////////////////////////
export type GetThreadInfo = {
    username: string;
    lastOnline: string;
    title: string;
    dateOfCreation: string;
    commentCount: number;
    upvoteCount: number;    
}

///////////////////////////////////////
export type GetThreadsDTO = {
    topicsName: string,
    description: string,
    threads: GetThreadInfo[],
}

export type PartialProfileInfoDTO = {
    username: string,
    joined: string,
    postCount: number,
    reputation: number,
}

//???????????????????????????????????
export type GetCommentDTO = {
    partialProfile: PartialProfileInfoDTO,

    dateOfCreation: string;
    comment: string;

    reply: string;
    replyId: number;
}

//???????????????????????????????????/
export type GetCommentsDTO = {
    partialProfile: PartialProfileInfoDTO,

    title: string,
    content: string;
    contentDateOfCreation: string;

    comments: GetCommentDTO[];
}

export type ErrorInformationDTO = {
    
}