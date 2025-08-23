import { useQuery } from "@tanstack/react-query";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";

import AddTopicComponent from "./AddTopicComponent";
import DropDownComponent from "./DropDownComponent";
import getTopics from "../APIs/getTopics";
import type { RootState } from "../Store/store";
import { addError } from "../Store/errorState";
import { TOPICS_ARRAY_STALE_TIME, TOPICS_ARRAY_QUERY_KEY } from "../Utils/queryConsts";

function MainPage(){
    const usersData = useSelector((state: RootState) => state.USER_INFO);
    const dispatch = useDispatch();

    const { data, isError, error} = useQuery({
        queryKey: [TOPICS_ARRAY_QUERY_KEY],
        queryFn: () => getTopics(),
        staleTime: TOPICS_ARRAY_STALE_TIME,
    });

    useEffect(() => {
        dispatch(addError(error?.message!));
    }, [isError])

    return (
        <div>
            <DropDownComponent title='Information' topicsArray={data!}/>
            <DropDownComponent title='General' topicsArray={data!}/>

            {(!!usersData && usersData.role === 'ADMIN') && <AddTopicComponent/>}
        </div>
    )
}

export default MainPage;