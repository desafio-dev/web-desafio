import { useState } from 'react'
import { useForm } from "react-hook-form";
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import fs from 'fs'
import axios from 'axios'

import './styles.css'
import Owner from '../owner';


export default function UploadFile() {

  const [isDragging, setIsDragging] = useState(false);
  const [selectedFile, setSelectedFile] = useState<File>();

  const { register, handleSubmit  } = useForm();

  function onSubmit() {
    if(selectedFile) {
      const formData = new FormData();
      formData.append('file', selectedFile);

      axios({
        method: 'post',
        url: 'http://localhost:8080/upload-file',
        data: formData,
        headers: {
          'Content-Type': `multipart/form-data;`,
        },
      })
        .then((response) => {
          console.log('Resposta:', response.data);
        })
        .catch((error) => {
          console.error('Erro:', error);
        });
    }
  }

  const handleDragEnter = (e: any) => {
    e.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = () => {
    setIsDragging(false);
  };

  const handleDrop = (e: any) => {
    e.preventDefault();
    setIsDragging(false);
    const file = e.dataTransfer.files[0];
    handleFile(file);
  };

  const handleFileInput = (e: any) => {
    const file = e.target.files[0];
    handleFile(file);
  };

  const handleFile = (file: any) => {
    console.log(file);
    setSelectedFile(file);
  };
  return (
    <>
    
    <div
    className={`drag-drop-container ${isDragging ? 'dragging' : ''}`}
      onDragEnter={handleDragEnter}
      onDragOver={handleDragEnter}
      onDragLeave={handleDragLeave}
      onDrop={handleDrop}
    >
      {selectedFile ? (
        <div className="selected-file-info">
          <p>{ selectedFile.name }</p>
          <button onClick={onSubmit} className="blue-gradient-button">upload</button>
        </div>
      ) : (
        <>
          <label htmlFor="file-input" className="file-label">
            {isDragging ? 'Solte o Arquivo Aqui' : 'Arraste e Solte para Selecionar um Arquivo'}
          </label> 
        </>
      )}
    </div>

    <Owner/>

    </>
  );
}