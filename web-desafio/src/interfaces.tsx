
export interface OwnerInterface {
    id: number,
    ownerName: string,
    cpf: string,
    storeName: string
}

export interface Transaction {
    id: number,
    type_transaction: number,
    card_number: string,
    value: number
}

export interface LoadOwner {
    load: boolean
}