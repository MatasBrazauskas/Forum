import Dropdown from 'react-bootstrap/Dropdown';

import './DropDownStyle.css';

function DropDownComponent({ title } : { title: string} ) {

    return (
        <Dropdown>
            <Dropdown.Toggle id="dropdown-basic" className='dropDown'>{title}</Dropdown.Toggle>
            <Dropdown.Menu>
                <Dropdown.Item className='dropDown'>TEMP1</Dropdown.Item>   
                <Dropdown.Item className='dropDown'>TEMP1</Dropdown.Item>   
                <Dropdown.Item className='dropDown'>TEMP1</Dropdown.Item>   
            </Dropdown.Menu>
        </Dropdown>
    )
}

export default DropDownComponent;