import { Outlet } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

import TopBar from "./TopBar/TopBar";
import getUsersProfile from "./APIs/getUsersProfile";
import { adduserInfo } from "./Store/userState";

function MainPage(){

    const dispatch = useDispatch();

    const usersInfo = useQuery({
        queryKey: ['usersInfo'],
        queryFn: () => getUsersProfile(),
        staleTime: Infinity,
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