import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { useEffect, useState } from "react";
import axios, { AxiosResponse } from "axios";
import { Space, Table, Tag } from "antd";
import { OwnerListAtom, OwnerTransactionList } from "../../atoms/Owner";
import { OwnerInterface } from "../../interfaces";

import type { TableProps } from 'antd';
import { columns } from './table.config'
import { formatDate } from "../../utils";


interface ShowError {
  isError: boolean;
  message: string;
}

axios.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

interface DataType {
  key: string;
  name: string;
  age: number;
  address: string;
  tags: string[];
}

const data: DataType[] = [
  {
    key: '1',
    name: 'John Brown',
    age: 32,
    address: 'New York No. 1 Lake Park',
    tags: ['nice', 'developer'],
  },
  {
    key: '2',
    name: 'Jim Green',
    age: 42,
    address: 'London No. 1 Lake Park',
    tags: ['loser'],
  },
  {
    key: '3',
    name: 'Joe Black',
    age: 32,
    address: 'Sydney No. 1 Lake Park',
    tags: ['cool', 'teacher'],
  },
];

export default function Owner() {
  const [listOwner, setListOwner] = useState<OwnerInterface[]>([]);
  const [loading, setLoading] = useState(false)
  const [showError, setShowError] = useState<ShowError>({
    isError: true,
    message: "",
  });

  useEffect(() => {
    setLoading(!loading);
    handleOwnerList();
  }, []);

  function handleResponseOwnerList(response: AxiosResponse) {
    console.log(response)
    setListOwner(response.data);
    setLoading(false);
  }

  const handleOwnerList = async() => {
    await axios
      .get("http://localhost:7005/owners")
      .then(handleResponseOwnerList)
      .catch((reason) => {
        setShowError({
          isError: !showError,
          message: reason.response.data.message,
        });
      });
  };

  return (
    <>
      <Table columns={columns} dataSource={listOwner} loading={loading} />
    </>
  );
}
