import {atom, RecoilState} from 'recoil'
import {LoadOwner, Owner} from "../../interfaces";

export const OwnerTransactionList = atom({key: 'ownerTransactionList', default: []})

export const OwnerList = atom<Owner[]>({key: 'ownerList', default: [{ id: 0, ownerName: '', cpf: '', storeName: ''}]});
export const LoadOwnerList = atom<LoadOwner>({key: 'ownerList', default: { load: false } } );
