import {useRecoilState, useRecoilValue, useSetRecoilState} from "recoil";
import {useEffect, useState} from "react";
import axios, {AxiosResponse} from "axios";
import {Alert, Card, Space, Button, Modal, Typography} from 'antd';
import {LoadOwnerList, OwnerList, OwnerTransactionList} from '../../atoms/Owner';
import {OwnerInterface} from "../../interfaces";

interface ShowError {
    isError: boolean,
    message: string
}


export default function Owner() {
    const [transactionList, setTransactionList] = useRecoilState(OwnerTransactionList);
    const setOwnerList = useSetRecoilState(OwnerList);
    const [currentOwner, setCurrentOwner] = useState<OwnerInterface>();
    const ownerList = useRecoilValue(OwnerList);
    const loadOwnerList = useRecoilValue(LoadOwnerList);
    const [showError, setShowError] = useState<ShowError>({isError: true, message: ''});
    const [isModalOpen, setIsModalOpen] = useState(false);

    function handleResponseTransactionList(response: AxiosResponse) {
        setTransactionList(response.data);
    }

    const showModal = (item: OwnerInterface) => {
        setCurrentOwner(item);
        const { id } = item;
        axios.get("http://localhost:8080/owners/transaction/"+id).then(handleResponseTransactionList).catch((reason) => {
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
    }, [loadOwnerList]);

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
            {ownerList.length > 0 && (
                <Space direction="horizontal" size={24}>
                    {ownerList.map((item) => (
                        <>
                            <Card key={item.id} title={item.ownerName}
                                  extra={<Button type="primary" onClick={(event) => { showModal(item) }} >Ver Extrato</Button>}
                                  style={{width: 300}}>
                                <Typography>CPF: {item.cpf}</Typography>
                                <Typography>Nome da Loja: {item.storeName}</Typography>
                                <Modal title={currentOwner?.ownerName} open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                                    {transactionList.length > 0 && transactionList.map((transaction) => <p key={transaction.id}>{transaction.value}</p>)}
                                </Modal>
                            </Card>

                        </>
                    ))}
                </Space>
            )}

            {!showError.isError && <Alert type="error" message={showError.message} closable/>}
        </>

    )
}

interface Record {
    name: string
}
