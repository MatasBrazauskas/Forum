import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import getThreads from "../APIs/getThreads"; 
import AddNewTheadComponent from "./AddNewThreadComponent";
import { percentEncoding } from "../APIs/const";
import { THREADS_STALE_TIME, THREADS_QUERY_KEY } from "../Utils/queryConsts";

import './threadStyle.css';

function ThreadPage(){
    const { topicsName } = useParams();
    const navigation = useNavigate();

    const threadsRequest = useQuery({
        queryKey: [THREADS_QUERY_KEY],
        queryFn: () => getThreads(topicsName!),
        staleTime: THREADS_STALE_TIME,
    })

    return (
        <div>
            <div className='mainContent'>
                <div className='font-black'>{threadsRequest?.data?.topicsName}</div>
                <div>{threadsRequest?.data?.description}</div>
            </div>
            <AddNewTheadComponent topicsName={topicsName!}/>
            <div className='bg-zinc-800 p-3 font-black'>Threads</div>

            {threadsRequest?.data?.threads.map((thread, i) => {
                return (
                    <div className="container temp" key={i}>
                        <div className='w-160'>
                            <div onClick={() => navigation(percentEncoding('posts', thread.title))}>{thread.title}</div>
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
            })}
        </div>
    )
}

export default ThreadPage;