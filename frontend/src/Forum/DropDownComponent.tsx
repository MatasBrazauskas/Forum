import { useReducer } from 'react';

import './DropDownStyle.css';
import type { TopicsInfo } from '../APIs/const';

import '../tailwind.css';
import { useNavigate } from 'react-router-dom';

function DropDownComponent({ title, topicsArray } : { title: string, topicsArray: TopicsInfo[]} ) {

    const [showThreads, setShowThreads] = useReducer((state: boolean) => {
        return !state;
    }, true);

    return (
        <div>
            <div onClick={() => setShowThreads()} className='dropDown'>{title}</div>

            {showThreads && 
            <div>
                {topicsArray?.map((topics, i) => {
                    return (
                        <DropDownItem topics={topics} i={i}/>
                    );
                })}
            </div>
            }
        </div>
    )
}

function DropDownItem({topics, i}: {topics: TopicsInfo, i: number}){

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