import { useReducer, useRef } from "react";

import '../Forum/DropDownStyle.css';

function AddNewTheadComponent(){
    const [open, switchStates] = useReducer((state: boolean) => {
        return !state;
    }, false);

    const title = useRef<HTMLInputElement>(null);
    const content = useRef<HTMLInputElement>(null);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        
    }

    return (
        <div>
            <div onClick={() => switchStates()} className='dropDown'>Add New Thread</div>

            {open && <form onSubmit={(e) => handleSubmit(e)}>

            </form>}
        </div>
    )
};

export default AddNewTheadComponent;