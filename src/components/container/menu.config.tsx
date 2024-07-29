
import {
    HomeOutlined,
    LogoutOutlined,
  } from "@ant-design/icons";
import HomeContent from "../contents/HomeContent";
import { Navigate } from "react-router-dom";

export const menuItems = [
    {
      key: "1",
      icon: <HomeOutlined />,
      label: "Transações",
      content: <HomeContent />,
    }
  ];
