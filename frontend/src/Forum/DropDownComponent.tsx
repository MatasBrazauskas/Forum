import { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';

import { type GetTopicsDTO } from '../Utils/ResponseDTOs';

import './DropDownStyle.css';
import '../tailwind.css';

function DropDownComponent({ title, topicsArray } : { title: string, topicsArray:GetTopicsDTO[]} ) {

    const [showThreads, setShowThreads] = useReducer((state: boolean) => {return !state;}, true);
    const filteredThreadsArray = topicsArray.filter(i => i.topicsName.toLowerCase() === title.toLowerCase());

    return (
        <div>
            <div onClick={() => setShowThreads()} className='dropDown'>{title}</div>

            {showThreads && 
            <div>
                {filteredThreadsArray.map((topics, i) => {
                    return (
                        <DropDownItem topics={topics} i={i} />
                    )
                })}
            </div>}
        </div>
    )
}

function DropDownItem({topics, i}: {topics: GetTopicsDTO, i: number}){

    const navigation = useNavigate();

    const swtichPages = (topicsName: string) => {
        navigation(`threads/${topicsName}`);
    }
    return (
        <div className='dropDownItem' key={i}>
            <div className='w-150'>
                <div className='text-xl font-black' onClick={() => swtichPages(topics.topicsName)}>{topics.topicsName}</div>
                <div className='text-sm'>{topics.description}</div>
            </div>

            <div>
                <div className='text-sm'>Threads</div>
                <div className='text-sm'>{topics.threadCount}</div>
            </div>

            <div>
                <div className='text-sm'>Posts</div>
                <div className='text-sm'>{topics.postCount}</div>
            </div>

            <div>
                <div className='text-sm'>{topics.created}</div>
                <div className='text-sm'>{topics.username}</div>
            </div>
        </div>
    )
}

export default DropDownComponent;