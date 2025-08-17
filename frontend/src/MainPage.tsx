import { Outlet } from "react-router-dom";
import TopBar from "./TopBar/TopBar";

function MainPage(){
    return (
        <div>
            <TopBar />
            <Outlet />
        </div>
    )
}

export default MainPage;