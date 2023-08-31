package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import br.com.bycoders.desafiodev.bankingservice.validators.ValidMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upload-file")
@Validated
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping
    public Map<Owner, List<Transactions>> uploadFile(@ValidMultipartFile @RequestBody MultipartFile file) throws IOException {
        return uploadFileService.uploadFile(file);
    }
}
