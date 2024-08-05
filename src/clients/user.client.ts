import axios from "axios";
import { API_BANK_USER } from "../config/bank.env.config";
import { isUndefined } from "util";

const userClientApi = axios.create({
    baseURL: !isUndefined(API_BANK_USER) && API_BANK_USER
})

export const getCountById = async (id: number) => {
    try {
      const response = await userClientApi.get(`/endpoint/${id}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching data by ID:', error);
      throw error;
    }
  };