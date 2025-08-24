import { useRef, useReducer } from "react";

import addNewTopic from "../APIs/addNewTopic";
import { queryClient } from "../main";
import { type AddTopicsDTO } from "../Utils/RequestDTOs";
import { TOPICS_ARRAY_QUERY_KEY } from "../Utils/queryConsts";
import { TOPICS_NAME_LENGTH, TOPICS_DESCRIPTION_LENGTH } from "../Utils/inputLengths";

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

        e.currentTarget.reset();
    }

    return (
        <div>
            <div onClick={() => switchState()} className='dropDown'>Add New Topic</div>
            {open && 
            <form onSubmit={(e) => handleSubmit(e)} className='addTopic' >
                <input type='text' maxLength={TOPICS_NAME_LENGTH} ref={topicsName} placeholder="Enter new topics name" className='w-99'/>
                <input type='text' maxLength={TOPICS_DESCRIPTION_LENGTH} ref={description} placeholder="Enter new topics description" className='w-99'/>
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