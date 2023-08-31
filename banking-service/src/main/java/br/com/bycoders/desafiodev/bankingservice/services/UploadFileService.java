package br.com.bycoders.desafiodev.bankingservice.services;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UploadFileService {
    Map<Owner, List<Transactions>> uploadFile(MultipartFile file) throws IOException;
}
