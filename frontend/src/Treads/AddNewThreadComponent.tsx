import { useReducer, useRef } from "react";

import { queryClient } from "../main";
import '../Forum/DropDownStyle.css';
import { type AddThreadDTO } from "../Utils/RequestDTOs";
import addNewThread from "../APIs/addNewThread";
import { THREADS_QUERY_KEY, TOPICS_ARRAY_QUERY_KEY } from "../Utils/queryConsts";
import { THREADS_TITLE_LENGTH, THREADS_CONTENT_LENGTH } from "../Utils/inputLengths";

function AddNewTheadComponent({topicsName}: {topicsName: string}){
    const [open, switchStates] = useReducer((state: boolean) => {return !state;}, false);

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
        queryClient.invalidateQueries({ queryKey: [THREADS_QUERY_KEY, TOPICS_ARRAY_QUERY_KEY]});
    }

    return (
        <div>
            <div onClick={() => switchStates()} className='dropDown'>Add New Thread</div>

            {open && <form onSubmit={(e) => handleSubmit(e)}>
                <input type='text'maxLength={THREADS_TITLE_LENGTH} ref={title} placeholder="Enter Threads Title"/>
                <input type='text'maxLength={THREADS_CONTENT_LENGTH} ref={content} placeholder="Enter Content"/>
                <button type='submit'>Post</button>
            </form>}
        </div>
    )
};

export default AddNewTheadComponent;