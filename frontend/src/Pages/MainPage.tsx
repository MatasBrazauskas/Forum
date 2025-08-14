import { Outlet } from "react-router-dom";
import TopBar from "../TopBar/TopBar";
import DropDownComponent from "../Components/DropDownComponent";

function MainPage(){

    return (
        <div>
            <TopBar />
            <DropDownComponent title='Information'/>
            <DropDownComponent title='Topics'/>
            <Outlet />
        </div>
    )
}

export default MainPage;