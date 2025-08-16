import { Outlet } from "react-router-dom";
import TopBar from "../TopBar/TopBar";
import DropDownComponent from "../Components/DropDownComponent";
import { useQuery } from "@tanstack/react-query";

//TEMP
import { TOPICS_REQUEST, type TopicsInfo } from "../APIs/const";
import AddTopicComponent from "../Components/AddTopicComponent";

function MainPage(){

    const unprotectedAPI = async (): Promise<TopicsInfo[]> => {
        const response = await fetch(TOPICS_REQUEST, {
            method: 'GET',
            credentials: 'include'
        });

        const data: TopicsInfo[] = await response.json();
        console.log(data);

        return data;
    }

    const { data, isError, error } = useQuery({
        queryKey: ['topics'],
        queryFn: () => unprotectedAPI(),
        staleTime: 60 * 10 * 1000,
    });

    return (
        <div>
            <TopBar />
            {isError && <div>{error?.message}</div>}

            <DropDownComponent title='Information' topicsArray={data?.filter(i => i.topicType === "INFORMATION")!}/>
            <DropDownComponent title='General' topicsArray={data?.filter(i => i.topicType === "GENERAL")!}/>

            <AddTopicComponent/>

            <Outlet />
        </div>
    )
}

export default MainPage;