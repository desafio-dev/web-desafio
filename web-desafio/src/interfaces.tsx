
export interface Owner {
    id: number,
    ownerName: string,
    cpf: string,
    storeName: string
}

export interface Transaction {
    type_transaction: number,
    card_number: number,
    value: number
}

export interface LoadOwner {
    load: boolean
}