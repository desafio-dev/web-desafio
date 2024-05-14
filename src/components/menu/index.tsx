import { useState } from 'react';
import type { MenuProps } from 'antd';
import { Menu } from 'antd';
import { items } from './config.menu';

const MenuBar: React.FC = () => {

    const [current, setCurrent] = useState('mail');
    const onClick: MenuProps['onClick'] = (e) => {
        console.log('click ', e);
        setCurrent(e.key);
      };
      

    return (
        <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={items} />
    )
}

export default MenuBar;