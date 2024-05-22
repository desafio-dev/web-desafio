import { useState } from "react";
import { Layout, Menu, Avatar, Typography } from "antd";
import {
  UserOutlined
} from "@ant-design/icons";
import { menuItems } from "./menu.config";

const { Header, Sider, Content } = Layout;
const { Title, Text } = Typography;

const Container = () => {
  const [collapsed, setCollapsed] = useState(false);

  const toggleCollapse = () => {
    setCollapsed(!collapsed);
  };

  

  const [selectedKey, setSelectedKey] = useState("1");

  return (
    <Layout style={{ minHeight: '100vh', margin: 0, padding: 0 }}>
      <Sider collapsible collapsed={collapsed} onCollapse={toggleCollapse}>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            padding: "16px",
          }}
        >
          <Avatar size={64} icon={<UserOutlined />} />
          {!collapsed && (
            <>
              <Title level={4} style={{ color: "white", margin: "16px 0 0 0" }}>
                Nome do Usuário
              </Title>
              <Text style={{ color: "white" }}>usuário@exemplo.com</Text>
            </>
          )}
        </div>
        <Menu
          theme="dark"
          defaultSelectedKeys={["1"]}
          mode="inline"
          onClick={(e) => setSelectedKey(e.key)}
        >
          {menuItems.map((item) => (
            <Menu.Item key={item?.key} icon={item.icon}>
              {item?.label}
            </Menu.Item>
          ))}
        </Menu>
      </Sider>
      <Layout>
        <Header style={{ background: "#fff", padding: 0 }}>
          <Title level={3} style={{ margin: "16px" }}>
            Banking
          </Title>
        </Header>
        <Content style={{ margin: "16px" }}>
          {menuItems.find((item) => item.key === selectedKey)?.content}
        </Content>
      </Layout>
    </Layout>
  );
};

export default Container;
