package ir.bigz.spring.graphql.customer.service;

import ir.bigz.spring.graphql.customer.dto.CustomerResponse;
import ir.bigz.spring.graphql.customer.dto.PurchaseTransactionResponse;
import ir.bigz.spring.graphql.customer.entity.CustomerEntity;
import ir.bigz.spring.graphql.customer.repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record CustomerService(CustomerRepo customerRepo) {

    public List<CustomerResponse> getAllCustomers() {
        log.info("Getting all customers ...");
        return customerRepo.findAll().stream().map(CustomerResponse::valueOf).collect(Collectors.toList());
    }

    public CustomerResponse getCustomersById(String customerId) {
        log.info("Getting customer by id...");
        return customerRepo.findById(customerId).map(CustomerResponse::valueOf).orElse(null);
    }

    public List<PurchaseTransactionResponse> getAllCustomerPurchaseTransactions(String customerId) {
        log.info("Getting all customer purchase transactions...");
        return customerRepo.findById(customerId)
                .map(CustomerEntity::getPurchaseTransactions)
                .orElseGet(ArrayList::new)
                .stream()
                .map(PurchaseTransactionResponse::valueOf)
                .collect(Collectors.toList());
    }

    public PurchaseTransactionResponse getCustomerPurchaseTransactionsById(String customerId,
                                                                           String purchaseTransactionId) {
        log.info("Getting customer purchase transaction by id...");
        return customerRepo.findById(customerId)
                .map(CustomerEntity::getPurchaseTransactions)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(purchaseTransaction -> purchaseTransactionId.equals(purchaseTransaction.getId()))
                .map(PurchaseTransactionResponse::valueOf)
                .findFirst()
                .orElse(null);
    }

    public List<CustomerResponse> getAllCustomersWithFilters(String fullName, String phoneNumber,
                                                             LocalDate createdAt, String paymentType) {
        log.info("Getting all customers with filters fullName {}...", fullName, phoneNumber, createdAt, paymentType);
        return customerRepo.findCustomersWithFilters(fullName, phoneNumber, createdAt, paymentType)
                .stream()
                .map(CustomerResponse::valueOf)
                .collect(Collectors.toList());
    }
}
