package ir.bigz.spring.graphql.customer.dto;

import ir.bigz.spring.graphql.customer.entity.PurchaseTransactionEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PurchaseTransactionResponse {

    private String id;

    private String paymentType;

    private BigDecimal amount;

    private LocalDate createdAt;

    public static PurchaseTransactionResponse valueOf(PurchaseTransactionEntity purchaseTransaction) {
        return builder()
                .id(purchaseTransaction.getId())
                .paymentType(purchaseTransaction.getPaymentType())
                .amount(purchaseTransaction.getAmount())
                .createdAt(purchaseTransaction.getCreatedAt()).build();
    }
}
