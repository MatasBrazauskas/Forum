import { Outlet } from "react-router-dom";
import TopBar from "../TopBar/TopBar";
import DropDownComponent from "../Components/DropDownComponent";

//TEMP
import { TOPICS_REQUEST } from "../APIs/const";

function MainPage(){

    const unprotectedAPI = async () => {
        const response = await fetch(TOPICS_REQUEST, {
            method: 'GET',
            credentials: 'include'
        });

        const data = await response.json();
        console.log(`Unprotected data ${data}`);
    }

    const protectedAPI = async () => {
        const response = await fetch(TOPICS_REQUEST + '/admin', {
            method: 'GET',
            credentials: 'include'
        })

        const data = await response.json();
        console.log(`Protected data ${data}`);
    }



    return (
        <div>
            <TopBar />
            <DropDownComponent title='Information'/>
            <DropDownComponent title='Topics'/>

            <button onClick={() => unprotectedAPI()}>Call unprotected API</button>
            <button onClick={() => protectedAPI()}>Call protected API</button>

            <Outlet />
        </div>
    )
}

export default MainPage;