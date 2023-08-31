package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.GetOwnerService;
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
@RequestMapping("/owners")
@Tag(name = "Owner Controller", description = "")
public class OwnerController {

    @Autowired
    private GetOwnerService getOwnerService;

    @GetMapping
    @Operation(summary = "This endpoint are resposible to get all owners in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owner was loaded with successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public List<Owner> getAll() {
        return getOwnerService.getAll();
    }

    @GetMapping("/{owner_id}")
    @Operation(summary = "This endpoint list all transaction by owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owner was loaded with successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public List<Transactions> getTransactionsOwner(@PathVariable("owner_id") Long id) {
        return getOwnerService.getTransactionsOwner(id);
    }
}
