import { useSelector, } from "react-redux";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import LogInMonad from './Login/LogInMonad';
import ProfileModal from '../Profile/ProfileModal';
import SearchModal from "./SearchMonad";

import type { RootState } from "../Store/store";

import './style.css';

function TopBar() {

    const userInfo = useSelector((state: RootState) => state.USER_INFO);
    const navigator = useNavigate();

    const [profile, setProfile] = useState(false);
    const [login, setLogin] = useState(false);
    const [search, setSearch] = useState(false);

    return (
        <div className = 'top_bar_container'>
            <div className = 'left titles'>
                <div onClick={() => navigator('/')}>Forum</div>
                <div onClick={() => navigator('threads/rules')}>Rules</div>
            </div>
            <div className = 'right'> 
                {userInfo.role !== 'GUEST' ?
                <div className = 'titles'>
                    <div onClick={() => setProfile(true)}>{userInfo.username}</div>
                    <ProfileModal show={profile} onHide={() => setProfile(false)}/>
                </div>
                :

                <div className = 'titles'>
                    <div onClick={() => setLogin(true)}>Login</div>
                    <LogInMonad show={login} onHide={() => setLogin(false)} />
                </div>
                }


                <div onClick={() => setSearch(true)}>Search</div>
                <SearchModal
                    show={search}
                    onHide={() => setSearch(false)}
                />
            </div>
        </div>
    )
}

export default TopBar;