package ir.bigz.spring.graphql.customer.repo;

import ir.bigz.spring.graphql.customer.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepo extends JpaRepository<PurchaseTransactionEntity, String> {
}
