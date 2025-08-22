import { useQuery } from "@tanstack/react-query";
import { useSelector } from "react-redux";

import AddTopicComponent from "./AddTopicComponent";
import DropDownComponent from "./DropDownComponent";
import getTopics from "../APIs/getTopics";
import type { RootState } from "../Store/store";
import { TOPICS_ARRAY_STALE_TIME, TOPICS_ARRAY_QUERY_KEY } from "../Utils/queryConsts";

function MainPage(){
    const usersData = useSelector((state: RootState) => state.USER_INFO);

    const topicsArray = useQuery({
        queryKey: [TOPICS_ARRAY_QUERY_KEY],
        queryFn: () => getTopics(),
        staleTime: TOPICS_ARRAY_STALE_TIME,
    });

    return (
        <div>
            <DropDownComponent title='Information' topicsArray={topicsArray?.data!}/>
            <DropDownComponent title='General' topicsArray={topicsArray?.data!}/>

            {(!!usersData && usersData.role === 'ADMIN') && <AddTopicComponent/>}
        </div>
    )
}

export default MainPage;