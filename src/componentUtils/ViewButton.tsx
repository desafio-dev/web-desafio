import { EyeOutlined } from "@ant-design/icons";
import { Modal, notification, Space, Tooltip } from "antd";
import axios, { AxiosResponse } from "axios";
import { useState } from "react";


interface ViewButtonProps {
    id: number; 
  }

const ViewButton : React.FC<ViewButtonProps> = ({ id }) =>  {
    const [isModalVisible, setIsModalVisible] = useState(false);

    const handleResponseTransactionList = (response: AxiosResponse) => {
        console.log(response.data)
    }

    const handleTransaction = async() => {
        await axios
            .get("http://localhost:7005/owners/transaction/" + id)
            .then(handleResponseTransactionList)
            .catch((reason) => {
                notification.info({
                    message: "Não foi possível carregar transações"
                })
            });

        setIsModalVisible(true)
    }

    const handleOk = () => {
        console.log('OK clicked');
        setIsModalVisible(false);
      };
    
      const handleCancel = () => {
        console.log('Cancel clicked');
        setIsModalVisible(false);
      };

    return (
        <>
        
        
        <Space size="middle">
            <Tooltip title="Visualizar">
                <EyeOutlined onClick={handleTransaction} />
            </Tooltip>
        </Space>


        <Modal
            title="Basic Modal"
            visible={isModalVisible}
            onOk={handleOk}
            onCancel={handleCancel}
        >
        <p>Some contents...</p>
      </Modal>

      </>
    )
}

export default ViewButton;