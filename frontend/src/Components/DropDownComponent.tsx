import { useReducer, useState } from 'react';

import './DropDownStyle.css';

function DropDownComponent({ title } : { title: string} ) {
    const [showThreads, setShowThreads] = useReducer((state: boolean) => {
        return !state;
    }, false);

    return (
        <div>
            <div onClick={() => setShowThreads()} className='dropDown'>{title}</div>
        </div>
    )
}

export default DropDownComponent;