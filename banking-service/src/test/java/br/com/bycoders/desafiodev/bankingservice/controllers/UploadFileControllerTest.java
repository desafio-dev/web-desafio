package br.com.bycoders.desafiodev.bankingservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UploadFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void should_upload_file_thoughtout_endpoint() throws Exception {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MockMultipartFile file = new MockMultipartFile("file", "CNAB.txt", MediaType.TEXT_PLAIN_VALUE, arquivoCnab.getInputStream());

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload-file").file(file))
                .andExpect(status().isOk());

    }

    @Test
    public void should_return_bad_request_if_dont_send_file() throws Exception {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MockMultipartFile file = new MockMultipartFile("file", "CNAB.txt", MediaType.TEXT_PLAIN_VALUE, arquivoCnab.getInputStream());

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload-file"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The CNAB file are mandatory."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400));

    }

    @Test
    public void should_return_bad_request_if_type_of_file_arent_txt() throws Exception {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.pdf");
        MockMultipartFile file = new MockMultipartFile("file", "CNAB.pdf", MediaType.TEXT_PLAIN_VALUE, arquivoCnab.getInputStream());

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload-file").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid file type. Only '.txt' files are allowed."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(400));

    }
}
