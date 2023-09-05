
export interface OwnerInterface {
    id: number,
    ownerName: string,
    cpf: string,
    storeName: string
}

export interface Transaction {
    id: number,
    typeOperation: number,
    cardNumber: string,
    descriptionOperation: string,
    date: string,
    type: number,
    value: number
}

export interface LoadOwner {
    load: boolean
}