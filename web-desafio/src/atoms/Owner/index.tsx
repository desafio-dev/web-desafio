import {atom, RecoilState} from 'recoil'
import {LoadOwner, OwnerInterface, Transaction} from "../../interfaces";

export const OwnerTransactionList = atom<Transaction[]>({key: 'ownerTransactionList', default: [{ id:0, type_transaction: 0, card_number: '', value: 0 }]})

export const OwnerList = atom<OwnerInterface[]>({key: 'ownerList', default: [{ id: 0, ownerName: '', cpf: '', storeName: ''}]});
export const LoadOwnerList = atom<LoadOwner>({key: 'ownerList', default: { load: false } } );
