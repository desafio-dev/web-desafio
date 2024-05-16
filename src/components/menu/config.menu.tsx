import { MenuProps } from "antd";
import { MailOutlined } from "@ant-design/icons";

type MenuItem = Required<MenuProps>["items"][number];

export const items: MenuItem[] = [
  {
    label: "Backoffice - Processamento Remessa",
    key: "mail",
    icon: <MailOutlined />,
    disabled: true,
  },
  {
    label: "Processar Remessa",
    key: "mail",
    icon: <MailOutlined />,
  },
];
