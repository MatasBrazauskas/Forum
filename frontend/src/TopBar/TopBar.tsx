import Forums from "./Forums";
import Rules from "./Rules";
import Profile from "./Profile";
import Inbox from "./Inbox";
import Search from "./Search";

import { useSelector, useDispatch } from "react-redux";
import { useEffect } from "react";

import type { RootState } from "../Store/store";
import loginRequest from "../APIs/loginRequest";
import { adduserInfo } from "../Store/userState";

import './style.css';
import Login from "../Login/Login";

function TopBar() {

    const userInfo = useSelector((state: RootState) => state.USER_INFO);
    const dispatch = useDispatch();

    useEffect(() => {
        const APIcall = async () => {
            const data = await loginRequest();
            
            if(data !== null){
                dispatch(adduserInfo(data));
            }
        }

        APIcall();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    return (
        <div className = 'top_bar_container'>
            <div>
                <Forums />
                <Rules />
            </div>
            
            {userInfo.role !== 'GUEST' ?
            <div className = 'titles'>
                <Profile />
                <Inbox />
                <Search />
            </div>
            :
            <div className = 'titles'>
                <Login />
            </div>
            }
        </div>
    )
}

export default TopBar;