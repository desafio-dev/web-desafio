package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import br.com.bycoders.desafiodev.bankingservice.validators.ValidMultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Upload File CNAB", description = "")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping
    @Operation(summary = "This endpoint is resposible to receive the file thoughtout the client and persiste her into database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public Map<Owner, List<Transactions>> uploadFile(@ValidMultipartFile @RequestBody MultipartFile file) throws IOException {
        return uploadFileService.uploadFile(file);
    }
}
