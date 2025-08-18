import { useQuery } from "@tanstack/react-query";
import { useSelector } from "react-redux";

import AddTopicComponent from "./AddTopicComponent";
import DropDownComponent from "./DropDownComponent";
import getTopics from "../APIs/getTopics";
import type { RootState } from "../Store/store";

function MainPage(){
    const usersData = useSelector((state: RootState) => state.USER_INFO);

    const topicsArray = useQuery({
        queryKey: ['topics'],
        queryFn: () => getTopics(),
        staleTime: 60 * 10 * 1000,
    });

    return (
        <div>
            <DropDownComponent title='Information' topicsArray={topicsArray?.data?.filter(i => i.topicType === "INFORMATION")!}/>
            <DropDownComponent title='General' topicsArray={topicsArray?.data?.filter(i => i.topicType === "GENERAL")!}/>

            {(!!usersData && usersData.role === 'ADMIN') && <AddTopicComponent/>}
        </div>
    )
}

export default MainPage;