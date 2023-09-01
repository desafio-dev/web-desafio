package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.exceptions.custom.OwnerNotFoundException;
import br.com.bycoders.desafiodev.bankingservice.exceptions.custom.TransactionNotFoundException;
import br.com.bycoders.desafiodev.bankingservice.services.GetOwnerService;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import br.com.bycoders.desafiodev.bankingservice.validators.ValidMultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/owners")
@Tag(name = "Owner Controller", description = "")
@CrossOrigin(origins = "*")
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
        List<Owner> owners = getOwnerService.getAll();

        if (Objects.isNull(owners) || CollectionUtils.isEmpty(owners)) {
            throw new OwnerNotFoundException("Onwers weren't founded");
        }

        owners.forEach(owner -> owner.add(linkTo(methodOn(OwnerController.class).getOneOwner(owner.getId())).withSelfRel()));

        return owners;
    }

    @GetMapping("/{id}")
    @Operation(summary = "This endpoint are resposible to get all owners in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owner was loaded with successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public Owner getOneOwner(@PathVariable("id") Long id) throws OwnerNotFoundException {
        Optional<Owner> owners = getOwnerService.getOwnerById(id);

        if(!owners.isPresent()) {
            throw new OwnerNotFoundException("Onwers weren't founded");
        } else {
            owners.get().add(linkTo(methodOn(OwnerController.class).getAll()).withSelfRel());
        }

        return owners.get();
    }

    @GetMapping("/transaction/{owner_id}")
    @Operation(summary = "This endpoint list all transaction by owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owner was loaded with successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public List<Transactions> getTransactionsOwner(@PathVariable("owner_id") Long owner_id) {

        List<Transactions> transactionsOwner = getOwnerService.getTransactionsOwner(owner_id);

        if (Objects.isNull(transactionsOwner) || CollectionUtils.isEmpty(transactionsOwner)) {
            throw new TransactionNotFoundException("Transactions weren't founded");
        }

        transactionsOwner.forEach(transaction -> {
            transaction.add(linkTo(methodOn(OwnerController.class).getTransactionsOwnerById(transaction.getId())).withSelfRel());
        });

        return transactionsOwner;
    }

    @GetMapping("/get-transaction-id/{id}")
    @Operation(summary = "This endpoint gets the transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owner was loaded with successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public Transactions getTransactionsOwnerById(@PathVariable("id") Long id) {
        Optional<Transactions> transactionsOwnerById = getOwnerService.getTransactionsOwnerById(id);
        if (!transactionsOwnerById.isPresent()) {
            throw new TransactionNotFoundException("Transactions weren't founded");
        } else {
            transactionsOwnerById.get().add(linkTo(methodOn(OwnerController.class).getTransactionsOwner(transactionsOwnerById.get().getOwner().getId())).withSelfRel());
        }

        return transactionsOwnerById.get();
    }


}
