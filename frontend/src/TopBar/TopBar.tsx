/*import Forums from "./Forums";
import Rules from "./Rules";
import Profile from "./Profile";*/

import { useSelector } from "react-redux";
import { useEffect } from "react";

import type { RootState } from "../Store/store";
import getCookies from "../APIs/getCookies";

function TopBar() {

    const tempObj = useSelector((state: RootState) => state.USER_INFO);

    useEffect(() => {
        const APIcall = async () => {
            getCookies();
        }

        APIcall();
    }, []);

    return (
        <div>
            <div>{tempObj?.username}</div>
            {/*}
            <Forums />
            <Rules />
            <Profile />*/}
        </div>
    )
}

export default TopBar;