import { useReducer, useRef } from "react";

import { queryClient } from "../main";
import '../Forum/DropDownStyle.css';
import { type AddThreadDTO } from "../APIs/const";
import addNewThread from "../APIs/addNewThread";

function AddNewTheadComponent({topicsName}: {topicsName: string}){
    const [open, switchStates] = useReducer((state: boolean) => {
        return !state;
    }, false);

    const title = useRef<HTMLInputElement>(null);
    const content = useRef<HTMLInputElement>(null);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const data: AddThreadDTO = {
            topicsName: topicsName,
            title: title?.current?.value!,
            content: content?.current?.value!,
        }

        addNewThread(data);
        queryClient.invalidateQueries({ queryKey: ['threads']});
    }

    return (
        <div>
            <div onClick={() => switchStates()} className='dropDown'>Add New Thread</div>

            {open && <form onSubmit={(e) => handleSubmit(e)}>
                <input type='text' ref={title} placeholder="Enter Threads Title"/>
                <input type='text' ref={content} placeholder="Enter Content"/>
                <button type='submit'>Post</button>
            </form>}
        </div>
    )
};

export default AddNewTheadComponent;