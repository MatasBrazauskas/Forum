import { Modal } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";

import type { RootState } from "../Store/store";
import logOut from "../APIs/logOut";
import { addUserInfo} from "../Store/userState";
import { type UserInformation } from "../Store/utils";

function ProfileModal(props: any) {

    const usersInfo = useSelector((state: RootState) => state.USER_INFO);
    const dispatch = useDispatch();

    const handleLogOut = async () => {
        const data: UserInformation = await logOut();
        dispatch(addUserInfo(data!));
    }

    return (
        <Modal
        {...props}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
        >
        <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">
                Users Profile
            </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div>{usersInfo.username}</div>
            <div>{usersInfo.role}</div>
            <div>{usersInfo?.dateOfCreation}</div>
            <div>{usersInfo?.lastOnline}</div>
            <div>{usersInfo?.postCount}</div>
            <div>{usersInfo?.reputation}</div>
            <button type='button' onClick={() => handleLogOut()}>Log Out</button>
        </Modal.Body>
        </Modal>
    );
}

export default ProfileModal;