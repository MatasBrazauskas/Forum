import GoogleOAuth from "./GoogleOAuth";
import FacebookOAuth from "./FacebookOAuth";
import TwitterOAuth from "./TwitterOAuth";

import { Modal } from "react-bootstrap";

function LogInMonad(props: any) {

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
            <GoogleOAuth />
            <FacebookOAuth />
            <TwitterOAuth />
        </Modal.Body>
        </Modal>
    );
}

export default LogInMonad;