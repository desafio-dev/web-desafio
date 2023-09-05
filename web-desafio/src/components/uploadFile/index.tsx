import React from 'react';
import {Button, message, Upload} from 'antd';
import {UploadOutlined} from '@ant-design/icons';
import axios from 'axios';
import {useRecoilState} from "recoil";
import {LoadOwnerList} from "../../atoms/Owner";

export default function UploadFile() {

    const [loadOwnerList, setLoadOwnerList] = useRecoilState(LoadOwnerList);

    const handleFileChange = (info: any) => {
        if (info.file.status === 'done') {
            message.success('O arquivo foi enviado com sucesso.');
        } else if (info.file.status === 'error') {
            message.error('Erro ao enviar o arquivo.');
        }
    };

    const customRequest = async ({file, onSuccess, onError}: any) => {
        try {
            const formData = new FormData();
            formData.append('file', file);
            const response = await axios.post('http://localhost:8080/upload-file', formData);
            onSuccess(response, file);
            setLoadOwnerList({ load: true });
        } catch (error) {
            setLoadOwnerList({ load: false });
            onError('Erro ao enviar o arquivo', error);
        }
    };

    return (
        <div>
            <Upload
                customRequest={customRequest}
                onChange={handleFileChange}
                accept=".txt"
            >
                <Button icon={<UploadOutlined/>}>Carregar Documento</Button>
            </Upload>
        </div>
    );
}