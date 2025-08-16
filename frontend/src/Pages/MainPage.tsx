import { Outlet } from "react-router-dom";
import TopBar from "../TopBar/TopBar";
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

    const temp = useQuery({
        queryKey: ['topics'],
        queryFn: () => getTopics(),
        staleTime: 60 * 10 * 1000,
    });

    const usersInfo = useQuery({
        queryKey: ['userInfo'],
        queryFn: () => getUsersProfile(),
        staleTime: Infinity,
        enabled: temp.isSuccess,
    });

    useEffect(() => {
        if(usersInfo.isSuccess){
            dispatch(adduserInfo(usersInfo.data));
        }
    }, [usersInfo.data]);

    return (
        <div>
            <TopBar />

            <DropDownComponent title='Information' topicsArray={temp.data?.filter(i => i.topicType === "INFORMATION").map(obj => ({...obj, created: new Date(obj.created).toISOString()}))!}/>
            <DropDownComponent title='General' topicsArray={temp.data?.filter(i => i.topicType === "GENERAL")!}/>

            {usersData.role === 'ADMIN' && <AddTopicComponent/>}

            <Outlet />
        </div>
    )
}

export default MainPage;