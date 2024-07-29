import React from 'react';
import { EyeOutlined } from "@ant-design/icons";
import { Space, Tooltip } from "antd";
import { OwnerInterface } from "../../interfaces";
import ViewButton from '../../componentUtils/ViewButton';


export const columns = [
    {
        title: 'Nome',
        dataIndex: 'ownerName',
        key: 'ownerName',
      },
      {
        title: 'Cpf',
        dataIndex: 'cpf',
        key: 'cpf',
      },
      {
        title: 'Nome da Loja',
        dataIndex: 'storeName',
        key: 'storeName',
      },
      {
        title: '',
        key: 'action',
        render: (_: any, record: OwnerInterface) => <ViewButton id={record.id} />
        ,
      },
]