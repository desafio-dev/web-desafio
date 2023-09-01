import React, { useState, useEffect } from 'react';
import axios from 'axios'

import "./styles.css"

interface Owner {
  id: number,
  ownerName: string,
  cpf: string,
  storeName: string
}

export default function Owner() {
  const [owner, setOwner] = useState<Owner[]>([])
  const [isModalOpen, setIsModalOpen] = useState(false);
  useEffect(() => {
    getOwner()
  },[]);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  function getOwner() {
    
      axios({
        method: 'get',
        url: 'http://localhost:8080/owners',
        headers: {
          'Content-Type': `application/json;`,
        },
      })
        .then((response) => {
          console.log('Resposta:', response.data);
          setOwner(response.data);
        })
        .catch((error) => {
          console.error('Erro:', error);
        });
    
  }

    function handlerCardsByOwner() {
      return owner.map(ow => (
        <>
          <div key={ow.id} className="card">
          <h2>{ ow.ownerName }</h2>
          <p>Nome da loja: { ow.storeName }</p>
          <p>CPF: { ow.cpf }</p>
          <button className="orange-gradient-button" onClick={openModal}>ver extrato</button>
        </div>
        </>
      ))
    }

    return (
        <div className="container">
      <div className="card-container">


        {handlerCardsByOwner()}
        
        
      </div>
      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closeModal}>
              &times;
            </span>
            
            <div className="financial-container">
      <div className="header">
        <h1>Financial Transactions</h1>
        <p>Owner: John Doe</p>
      </div>
      <div className="transactions">
        <div className="transaction-card withdraw">
          <h2>Withdrawal</h2>
          <p>Date: 2023-09-01</p>
          <p>Amount: -$500.00</p>
        </div>
        <div className="transaction-card payment">
          <h2>Payment</h2>
          <p>Date: 2023-09-02</p>
          <p>Amount: -$200.00</p>
        </div>
        <div className="transaction-card bill">
          <h2>Bill</h2>
          <p>Date: 2023-09-03</p>
          <p>Amount: -$300.00</p>
        </div>
        <div className="transaction-card earn">
          <h2>Earn</h2>
          <p>Date: 2023-09-04</p>
          <p>Amount: +$1000.00</p>
        </div>
      </div>
    </div>


          </div>
        </div>
      )}
    </div>
    );
}