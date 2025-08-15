import { useRef } from "react";

import { type AddTopicsDTO } from "../APIs/const";
import addTopic from "../APIs/addTopic";

function AddTopicComponent(){

    const topicsName = useRef<HTMLInputElement | null>(null);
    const description = useRef<HTMLInputElement | null>(null);
    const topicsType = useRef<HTMLSelectElement | null>(null);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const data: AddTopicsDTO = {
            topicsName: topicsName?.current?.value!,
            description: description?.current?.value!,
            topicType: 'GENERAL',
        }

        const response = await addTopic(data);
        console.log(response);
    }

    return (
        <form onSubmit={(e) => handleSubmit(e)}>
            <input type='text' ref={topicsName} placeholder="Enter new topics name"/>
            <input type='text' ref={description} placeholder="Enter new topics description"/>
            <select ref={topicsType}>
                <option value="INFORMATION">INFORMATION</option>
                <option value="GENERAL">GENERAL</option>
            </select>
            <button type='submit'>POST</button>
        </form>
    )
}

export default AddTopicComponent;