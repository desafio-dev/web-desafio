import React, { useState } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Form, Input, notification } from 'antd';
import axios from 'axios'

import { useNavigate } from 'react-router-dom'

const Login: React.FC = () => {

  const navigate = useNavigate();

  const [loading, setLoading] = useState(false)

  const onFinish = async (values: any) => {
    

    setLoading(true)
    await axios.post("http://localhost:7005/login", {
      username: values.username,
      password: values.password
    }).then((response) => {
      const { accessToken } = response.data
      localStorage.setItem("token", accessToken)

      notification.success({
        message: 'Usuário logado com sucesso!',
      });

      setLoading(false)
      navigate("/home")
    })
    .catch((err) => {
      setLoading(false)
      notification.error({
        message: 'Usuário ou senha inválido',
      });
    })
  };

const removeToken = () => localStorage.removeItem("token") 

  return (
    <div style={{ margin: '0px auto', marginTop:'20vh', width: '20rem' }}>

    <Form
      name="normal_login"
      className="login-form"
      initialValues={{ remember: true }}
      onFinish={onFinish}
      >
      <Form.Item
        name="username"
        rules={[{ required: true, message: 'Please input your Username!' }]}
      >
        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[{ required: true, message: 'Please input your Password!' }]}
      >
        <Input
          prefix={<LockOutlined className="site-form-item-icon" />}
          type="password"
          placeholder="Password"
          />
      </Form.Item>
      <Form.Item>
        <Button loading={loading} style={{width: '100%'}} type="primary" htmlType="submit" className="login-form-button">
          Entrar
        </Button>
      </Form.Item>

      <Button type='primary' onClick={removeToken}>Remover token</Button>
    </Form>
          </div>
  );
};

export default Login; 