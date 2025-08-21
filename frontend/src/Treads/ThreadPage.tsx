import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import getThreads from "../APIs/getThreads"; 
import AddNewTheadComponent from "./AddNewThreadComponent";
import { type GetThreadsDTO } from "../Types/ResponseDTOs";

import './threadStyle.css';

function ThreadPage(){
    const { topicsName } = useParams();

    const threadsRequest = useQuery({
        queryKey: ['threads'],
        queryFn: () => getThreads(topicsName!),
    })

    return (
        <div>
            <div className='mainContent'>
                <div className='font-black'>{threadsRequest?.data?.topicsName}</div>
                <div>{threadsRequest?.data?.description}</div>
            </div>
            <AddNewTheadComponent topicsName={topicsName!}/>
            <div className='bg-zinc-800 p-3 font-black'>Threads</div>

            {threadsRequest?.data?.threads.map((thread) => {
                return (
                    <ThreadsTitle thread={thread}/>
                )
            })}
        </div>
    )
}

function ThreadsTitle({thread} :{thread: ThreadsInfo}){

    const navigation = useNavigate();

    return (
        <div className="container temp">
            <div className='w-160'>
                <div onClick={() => navigation(`/posts/${thread.title}`)}>{thread.title}</div>
                <div>{thread.dateOfCreation}</div>
            </div>

            <div>
                <div>Upvotes: {thread.upvoteCount}</div>
                <div>Comments: {thread.commentCount}</div>
            </div>
            <div>
                <div>{thread.username}</div>
                <div>{thread.lastOnline}</div>
            </div>
        </div>
    )
}

export default ThreadPage;