package ir.bigz.spring.graphql.customer.dto;

import ir.bigz.spring.graphql.customer.entity.CustomerEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CustomerResponse {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private LocalDate createdAt;

    private List<PurchaseTransactionResponse> purchaseTransactions;

    public static CustomerResponse valueOf(CustomerEntity customer) {
        List<PurchaseTransactionResponse> purchaseTransactionResponses = customer.getPurchaseTransactions().stream()
                .map(PurchaseTransactionResponse::valueOf).collect(Collectors.toList());
        return builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .purchaseTransactions(purchaseTransactionResponses)
                .build();
    }
}
