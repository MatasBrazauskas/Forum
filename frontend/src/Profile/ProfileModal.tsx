import { Modal } from "react-bootstrap";
import { useSelector } from "react-redux";
import type { RootState } from "../Store/store";

function ProfileModal(props: any) {

    const usersInfo = useSelector((state: RootState) => state.USER_INFO);

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
        </Modal.Body>
        </Modal>
    );
}

export default ProfileModal;