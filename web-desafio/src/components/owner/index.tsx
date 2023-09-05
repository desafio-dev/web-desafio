import {useRecoilValue, useSetRecoilState} from "recoil";
import {useEffect, useState} from "react";
import axios, {AxiosResponse} from "axios";
import {Alert, Card, Space, Button, Modal, Typography} from 'antd';

import {LoadOwnerList, OwnerList} from '../../atoms/Owner';

interface ShowError {
    isError: boolean,
    message: string
}


export default function Owner() {
    const setOwnerList = useSetRecoilState(OwnerList);
    const ownerList = useRecoilValue(OwnerList);
    const loadOwnerList = useRecoilValue(LoadOwnerList);
    const [showError, setShowError] = useState<ShowError>({isError: true, message: ''});
    const [isModalOpen, setIsModalOpen] = useState(false);

    const showModal = () => {
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
        console.log(response);
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
                            <Card title={item.ownerName}
                                  extra={<Button type="primary" onClick={showModal}> Ver Extrato</Button>}
                                  style={{width: 300}}>
                                <Typography>CPF: {item.cpf}</Typography>
                                <Typography>Nome da Loja: {item.storeName}</Typography>
                            </Card>
                            <Modal title="Basic Modal" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                                <p>Some contents...</p>
                                <p>Some contents...</p>
                                <p>Some contents...</p>
                            </Modal>
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
