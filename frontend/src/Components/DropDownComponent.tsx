import { useReducer, useState } from 'react';

import './DropDownStyle.css';
import type { TopicsInfo } from '../APIs/const';

import '../tailwind.css';

function DropDownComponent({ title, topicsArray } : { title: string, topicsArray: TopicsInfo[]} ) {
    const [showThreads, setShowThreads] = useReducer((state: boolean) => {
        console.log(!state);
        return !state;
    }, true);

    return (
        <div>
            <div onClick={() => {setShowThreads(), console.log(topicsArray)}} className='dropDown'>{title}</div>

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

    return (
        <div className='dropDownItem' key={i}>
            <div>
                <div className='text-xl font-black'>{topics.topicsName}</div>
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