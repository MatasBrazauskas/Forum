import GoogleOAuth from "./GoogleOAuth";
import FacebookOAuth from "./FacebookOAuth";
import TwitterOAuth from "./TwitterOAuth";

import { Modal } from "react-bootstrap";
import './loginModelStyle.css';

function LogInMonad(props: any) {

    return (
        <Modal
        {...props}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
        >
        <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">Log in</Modal.Title>
        </Modal.Header>
        <Modal.Body className='modalBody'>
            <GoogleOAuth />
            <FacebookOAuth />
            <TwitterOAuth />
        </Modal.Body>
        </Modal>
    );
}

export default LogInMonad;