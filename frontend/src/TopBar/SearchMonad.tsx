import { Modal } from "react-bootstrap";

function SearchModal(props: any) {

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
            Seach smth
        </Modal.Body>
        </Modal>
    );
}

export default SearchModal;