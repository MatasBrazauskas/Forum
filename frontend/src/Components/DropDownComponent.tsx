import { useReducer, useState } from 'react';

import './DropDownStyle.css';
import type { TopicsInfo } from '../APIs/const';

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
                        <div key={i}>
                            <div>{topics.topicsName}</div>
                            <div>{topics.description}</div>
                            <div>{topics.threadCount}</div>
                            <div>{topics.postCount}</div>
                            <div>{topics.created}</div>
                            <div>{topics.username}</div>
                            <div>{topics.topicType}</div>
                        </div>
                    );
                })}
            </div>
            }
        </div>
    )
}

export default DropDownComponent;