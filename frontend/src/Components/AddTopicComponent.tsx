import { useRef } from "react";

import { type TOPICS } from "../APIs/const";

function AddTopicComponent(){

    const topicsName = useRef<HTMLInputElement | null>(null);
    const description = useRef<HTMLInputElement | null>(null);
    const topicsType = useRef<HTMLInputElement | null>(null);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
    }

    return (
        <form onSubmit={(e) => handleSubmit(e)}>
            <input type='text' ref={topicsName} placeholder="Enter new topics name"/>
            <input type='text' ref={description} placeholder="Enter new topics description"/>
            <select ref={topicsType}>
                <option>INFORMATION</option>
                <option>GENERAL</option>
            </select>
            <button type='submit'>POST</button>
        </form>
    )
}

export default AddTopicComponent;