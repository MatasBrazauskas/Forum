import DropDownComponent from "../Components/DropDownComponent";
import { useQuery } from "@tanstack/react-query";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";

import AddTopicComponent from "../Components/AddTopicComponent";
import { adduserInfo } from "../Store/userState";
import getTopics from "../APIs/getTopics";
import getUsersProfile from "../APIs/getUsersProfile";
import type { RootState } from "../Store/store";

function MainPage(){
    const dispatch = useDispatch();
    const usersData = useSelector((state: RootState) => state.USER_INFO);

    const topicsArray = useQuery({
        queryKey: ['topics'],
        queryFn: () => getTopics(),
        staleTime: 60 * 10 * 1000,
    });

    const usersInfo = useQuery({
        queryKey: ['usersInfo'],
        queryFn: () => getUsersProfile(),
        staleTime: Infinity,
        enabled: topicsArray.isSuccess,
    });

    useEffect(() => {
        if(usersInfo.isSuccess && !!usersInfo){
            dispatch(adduserInfo(usersInfo.data!));
        }
    }, [usersInfo.data]);

    return (
        <div>
            <DropDownComponent title='Information' topicsArray={topicsArray?.data?.filter(i => i.topicType === "INFORMATION")!}/>
            <DropDownComponent title='General' topicsArray={topicsArray?.data?.filter(i => i.topicType === "GENERAL")!}/>

            {(!!usersData && usersData.role === 'ADMIN') && <AddTopicComponent/>}
        </div>
    )
}

export default MainPage;