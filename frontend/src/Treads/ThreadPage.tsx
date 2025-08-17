import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import getThreads from "../APIs/getThreads"; 
import AddNewTheadComponent from "./AddNewThreadComponent";

function ThreadPage(){
    const { topicsName } = useParams();

    const threadsRequest = useQuery({
        queryKey: ['threads', topicsName],
        queryFn: () => getThreads(topicsName!),
    })

    return (
        <div>
            <div>{threadsRequest?.data?.topicsName}</div>
            <div>{threadsRequest?.data?.description}</div>
            <AddNewTheadComponent />

            {threadsRequest?.data?.threads.map((thread, i) => {
                return (
                    <div key={i}>{thread.title}</div>
                )
            })}
        </div>
    )
}

export default ThreadPage;