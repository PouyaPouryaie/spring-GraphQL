package ir.bigz.spring.graphql.customer.controller;

import ir.bigz.spring.graphql.customer.dto.CustomerResponse;
import ir.bigz.spring.graphql.customer.dto.PurchaseTransactionResponse;
import ir.bigz.spring.graphql.customer.service.CustomerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public record CustomerGraphQLController(CustomerService customerService) {

    @QueryMapping(name = "customers")
    public List<CustomerResponse> customers(
            @Argument String fullName,
            @Argument String phoneNumber,
            @Argument @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt,
            @Argument String paymentType) {
        return customerService.getAllCustomersWithFilters(fullName, phoneNumber, createdAt, paymentType);
    }

    @QueryMapping(name = "customer")
    public CustomerResponse customer(@Argument @NotNull String customerId) {
        return customerService.getCustomersById(customerId);
    }

    @QueryMapping(name = "customerTransactions")
    public List<PurchaseTransactionResponse> customerTransactions(@Argument @NotNull String customerId) {
        return customerService.getAllCustomerPurchaseTransactions(customerId);
    }

    @SchemaMapping(typeName = "Query", value = "customerTransaction")
    public PurchaseTransactionResponse customerTransaction(@Argument @NotNull String customerId,
                                                           @Argument @NotNull String purchaseTransactionId) {
        return customerService.getCustomerPurchaseTransactionsById(customerId, purchaseTransactionId);
    }
}
