package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.GetOwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private GetOwnerService getOwnerService;

    private List<Owner> ownerList;

    @BeforeEach
    public void setup() {
        ownerList = new ArrayList<>();
        ownerList.add(Owner.builder()
                .cpf("11111111111")
                .storeName("devexpress store")
                .ownerName("salomao")
                .transactionsList(List.of(Transactions.builder().id(1L).date(ZonedDateTime.now()).typeOperation("5").value(BigDecimal.valueOf(100)).cardNumber("4753****3153").build()))
                .build());
    }

    @Test
    public void should_find_all_owners() throws Exception {

        when(getOwnerService.getAll()).thenReturn(ownerList);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_a_badrequest_if_not_found_owners() throws Exception {

        when(getOwnerService.getAll()).thenReturn(null);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Onwers weren't founded"));
    }

    @Test
    public void should_find_owner_by_id() throws Exception {

        when(getOwnerService.getOwnerById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(Owner.builder()
                .id(2L)
                .cpf("11111111111")
                .storeName("devexpress store")
                .ownerName("salomao").build()));

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_a_badrequest_if_not_found_owner_by_id() throws Exception {

        when(getOwnerService.getAll()).thenReturn(null);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Onwers weren't founded"));
    }

    @Test
    public void should_find_all_transactions_by_owner() throws Exception {

        when(getOwnerService.getTransactionsOwner(ArgumentMatchers.any(Long.class)))
                .thenReturn(
                        List.of(
                                Transactions.builder()
                                        .id(1L)
                                        .date(ZonedDateTime.now())
                                        .typeOperation("5")
                                        .value(BigDecimal.valueOf(100))
                                        .cardNumber("4753****3153")
                                        .owner(Owner.builder()
                                                .id(2L)
                                                .cpf("11111111111")
                                                .storeName("devexpress store")
                                                .ownerName("salomao").build())
                                        .build()));

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/transaction/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_a_badrequest_if_not_found_transactions_by_owner() throws Exception {

        when(getOwnerService.getAll()).thenReturn(null);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/transaction/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Transactions weren't founded"));
    }

    @Test
    public void should_find_transactions_by_id() throws Exception {

        when(getOwnerService.getTransactionsOwnerById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.ofNullable(Transactions.builder()
                        .id(1L)
                        .date(ZonedDateTime.now())
                        .typeOperation("5")
                        .value(BigDecimal.valueOf(100))
                        .cardNumber("4753****3153")
                        .owner(Owner.builder()
                                .id(2L)
                                .cpf("11111111111")
                                .storeName("devexpress store")
                                .ownerName("salomao").build())
                        .build()));

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/get-transaction-id/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_a_badrequest_if_not_found_transactions_by_id() throws Exception {

        when(getOwnerService.getAll()).thenReturn(null);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/owners/get-transaction-id/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Transactions weren't founded"));
    }

}
