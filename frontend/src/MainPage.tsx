import { Outlet } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useQuery } from "@tanstack/react-query";

import TopBar from "./TopBar/TopBar";
import getUsersProfile from "./APIs/getUsersProfile";
import { addUserInfo } from "./Store/userState";
import ErrorsComponent from "./Errors/ErrorsComponent";
import { USER_INFO_QUERY_KEY, USER_INFO_STALE_TIME } from "./Utils/queryConsts";
import type { UserInformation } from "./Store/utils";

function MainPage(){

    const dispatch = useDispatch();

    useQuery({
        queryKey: [USER_INFO_QUERY_KEY],
        queryFn: async () => {
            const data: UserInformation = await getUsersProfile()
            dispatch(addUserInfo(data));
            return data;
        },
        staleTime: USER_INFO_STALE_TIME,
    });

    return (
        <div>
            <TopBar />
            <ErrorsComponent />
            <Outlet />
        </div>
    )
}

export default MainPage;