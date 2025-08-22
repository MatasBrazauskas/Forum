import { Outlet } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

import TopBar from "./TopBar/TopBar";
import getUsersProfile from "./APIs/getUsersProfile";
import { adduserInfo } from "./Store/userState";
import { USER_INFO_STALE_TIME, USER_INFO_QUERY_KEY } from "./Utils/queryConsts";

function MainPage(){

    const dispatch = useDispatch();

    const usersInfo = useQuery({
        queryKey: [USER_INFO_QUERY_KEY],
        queryFn: () => getUsersProfile(),
        staleTime: USER_INFO_STALE_TIME,
    });

    useEffect(() => {
        if(usersInfo.isSuccess && !!usersInfo){
            dispatch(adduserInfo(usersInfo.data!));
        }
    }, [usersInfo.data]);

    return (
        <div>
            <TopBar />
            <Outlet />
        </div>
    )
}

export default MainPage;