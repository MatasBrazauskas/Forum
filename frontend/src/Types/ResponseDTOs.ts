export type GetTopicsDTO = {
    topicsName: string,
    username: string
    created:string,
    description: string
    threadCount: number
    postCount: number
    topicType: "INFORMATION" | "GENERAL"
}

export type ThreadsInfo = {
    username: string;
    lastOnline: string;
    title: string;
    dateOfCreation: string;
    commentCount: number;
    upvoteCount: number;    
}

export type GetThreadsDTO = {
    topicsName: string,
    description: string,
    threads: ThreadsInfo[],
}

//???????????????????????????????????
export type GetCommentsDTO = {
    username: string;
    joined: string;
    postCount: number;
    reputation: number;

    dateOfCreation: string;
    comment: string;

    reply: string;
    replyId: number;
}

//???????????????????????????????????/
export type GetPostDTO = {
    title: string,
    content: string,
    dateOfCreation: string,
    comments: GetCommentsDTO[],
}