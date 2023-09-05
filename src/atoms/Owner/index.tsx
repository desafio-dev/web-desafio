import {atom, RecoilState} from 'recoil'
import {LoadOwner, OwnerInterface, Transaction} from "../../interfaces";

export const OwnerTransactionList = atom<Transaction[]>({
    key: 'ownerTransactionList',
    default: [
        {
            id:0,
            typeOperation: 0,
            cardNumber: '',
            descriptionOperation: '',
            date: '',
            type: 0,
            value: 0
        }
    ]
})

export const OwnerListAtom = atom<OwnerInterface[]>({key: 'ownerList', default: [{ id: 0, ownerName: '', cpf: '', storeName: ''}]});
export const LoadOwnerList = atom<LoadOwner>({key: 'ownerList', default: { load: false } } );
