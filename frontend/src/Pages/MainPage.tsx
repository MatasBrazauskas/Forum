import { Outlet } from "react-router-dom";
import TopBar from "../TopBar/TopBar";
import DropDownComponent from "../Components/DropDownComponent";
import { useQuery } from "@tanstack/react-query";
import { useDispatch } from "react-redux";
import { useEffect } from "react";

import AddTopicComponent from "../Components/AddTopicComponent";
import { adduserInfo } from "../Store/userState";
import getTopics from "../APIs/getTopics";
import getUsersProfile from "../APIs/getUsersProfile";

function MainPage(){
    const dispatch = useDispatch();

    //const { data: topicsArray, isError, error } = useQuery({
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

            <DropDownComponent title='Information' topicsArray={temp.data?.filter(i => i.topicType === "INFORMATION")!}/>
            <DropDownComponent title='General' topicsArray={temp.data?.filter(i => i.topicType === "GENERAL")!}/>

            <AddTopicComponent/>

            <Outlet />
        </div>
    )
}

export default MainPage;