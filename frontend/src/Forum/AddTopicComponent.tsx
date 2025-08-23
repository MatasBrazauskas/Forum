import { useRef, useReducer } from "react";

import addNewTopic from "../APIs/addNewTopic";
import { queryClient } from "../main";
import { type AddTopicsDTO } from "../Utils/RequestDTOs";
import { TOPICS_ARRAY_QUERY_KEY } from "../Utils/queryConsts";

import './DropDownStyle.css';

function AddTopicComponent(){

    const topicsName = useRef<HTMLInputElement | null>(null);
    const description = useRef<HTMLInputElement | null>(null);
    const topicsType = useRef<HTMLSelectElement | null>(null);

    const [open, switchState] = useReducer((state: boolean) => {
        return !state;
    }, true);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const data: AddTopicsDTO = {
            topicsName: topicsName?.current?.value!,
            description: description?.current?.value!,
            topicType: topicsType?.current?.value!,
        }

        await addNewTopic(data);
        queryClient.invalidateQueries({ queryKey: [TOPICS_ARRAY_QUERY_KEY]});
    }

    return (
        <div>
            <div className='dropDown' onClick={() => switchState()}>Add New Topic</div>
            {open && 
            <form onSubmit={(e) => handleSubmit(e)} className='addTopic' >
                <input type='text' ref={topicsName} placeholder="Enter new topics name" className='w-90'/>
                <input type='text' ref={description} placeholder="Enter new topics description" className='w-90'/>
                <select ref={topicsType}>
                    <option value="INFORMATION">INFORMATION</option>
                    <option value="GENERAL">GENERAL</option>
                </select>
                <button type='submit'>POST</button>
            </form>
            }
        </div>
    )
}

export default AddTopicComponent;