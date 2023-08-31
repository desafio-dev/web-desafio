package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upload-file")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping
    public Map<Owner, List<Transactions>> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return uploadFileService.uploadFile(file);
    }
}
