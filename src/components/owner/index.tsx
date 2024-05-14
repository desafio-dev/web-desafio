import {useRecoilState, useRecoilValue, useSetRecoilState} from "recoil";
import React, {useEffect, useState} from "react";
import axios, {AxiosResponse} from "axios";
import {Alert, Button, Card, List, Modal, Space, Typography} from 'antd';
import {OwnerListAtom, OwnerTransactionList} from '../../atoms/Owner';
import {OwnerInterface} from "../../interfaces";

import {MinusCircleFilled, PlusCircleFilled} from '@ant-design/icons';

import { formatDate } from '../../utils'


interface ShowError {
    isError: boolean,
    message: string
}

export default function Owner() {
    const [transactionList, setTransactionList] = useRecoilState(OwnerTransactionList);
    const setOwnerList = useSetRecoilState(OwnerListAtom);
    const [currentOwner, setCurrentOwner] = useState<OwnerInterface>();
    const ownerList = useRecoilValue(OwnerListAtom);
    const [showError, setShowError] = useState<ShowError>({isError: true, message: ''});
    const [isModalOpen, setIsModalOpen] = useState(false);

    function handleResponseTransactionList(response: AxiosResponse) {
        console.log(response);
        setTransactionList(response.data);
    }

    const showModal = (item: OwnerInterface) => {
        setCurrentOwner(item);
        const {id} = item;
        axios.get("http://localhost:8080/owners/transaction/" + id).then(handleResponseTransactionList).catch((reason) => {
            setShowError({isError: !showError, message: reason.response.data.message})
        });

        setIsModalOpen(true);
    };

    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    useEffect(() => {
        handleOwnerList();
    }, []);

    function handleResponseOwnerList(response: AxiosResponse) {
        setOwnerList(response.data);
    }

    const handleOwnerList = () => {
        axios.get("http://localhost:8080/owners").then(handleResponseOwnerList).catch((reason) => {
            setShowError({isError: !showError, message: reason.response.data.message})
        });
    }


    return (
        <>
            {ownerList.length > 0 && ownerList.map((item) => (
                        <Space key={item.id} direction="horizontal" size={24}>
                            <Card title={item.ownerName}
                                  extra={<Button type="primary" onClick={(event) => {
                                      showModal(item)
                                  }}>Ver Extrato</Button>}
                                  style={{width: 300}}>
                                <Typography>CPF: {item.cpf}</Typography>
                                <Typography>Nome da Loja: {item.storeName}</Typography>
                                <Modal title={currentOwner?.ownerName} open={isModalOpen} onOk={handleOk}
                                       onCancel={handleCancel}>
                                    {transactionList.length > 0 &&
                                        <List
                                            itemLayout="horizontal"
                                            dataSource={transactionList}
                                            renderItem={(item, index) => (
                                                <List.Item>
                                                    <List.Item.Meta
                                                        title={<a href="https://ant.design">{item.descriptionOperation}</a>}
                                                        description={(
                                                            <div style={{ margin: '10px', display: 'flex', flexDirection: 'row', alignItems: 'center' }}>
                                                                { item.type == 1 ?
                                                                    <PlusCircleFilled style={{ fontSize: '20px', fontStyle:'bold', color: 'green' }} />
                                                                    : <MinusCircleFilled style={{ fontSize: '20px', fontStyle:'bold', color: 'orange' }} />}

                                                                <div style={{ margin: '10px', padding: '0px' }}>
                                                                    <p style={{ margin: '3px', color: '#000', fontStyle: 'bold' }}>{ item.cardNumber }</p>
                                                                    <p style={{ margin: '3px', color: 'blue', fontStyle: 'bold' }}>{ item.value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }) }</p>
                                                                    <p style={{ margin: '3px', color: '#000' }}>{ item.date && formatDate(item.date) } </p>
                                                                </div>
                                                            </div>
                                                        )}
                                                    />
                                                </List.Item>
                                            )}
                                        />
                                    }
                                </Modal>
                            </Card>
                        </Space>
                    ))
                }

            {!showError.isError && <Alert type="error" message={showError.message} closable/>}
        </>

    )
}
