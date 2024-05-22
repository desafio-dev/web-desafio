
import {
    UserOutlined,
    HomeOutlined,
    SettingOutlined,
    LogoutOutlined,
  } from "@ant-design/icons";
import HomeContent from "../contents/HomeContent";

export const menuItems = [
    {
      key: "1",
      icon: <HomeOutlined />,
      label: "Home",
      content: <HomeContent />,
    },
    {
      key: "2",
      icon: <UserOutlined />,
      label: "Perfil",
      content: "Conteúdo do Perfil",
    },
    {
      key: "3",
      icon: <SettingOutlined />,
      label: "Configurações",
      content: "Conteúdo das Configurações",
    },
    {
      key: "4",
      icon: <LogoutOutlined />,
      label: "Sair",
      onclick: () => {}
    },
  ];
