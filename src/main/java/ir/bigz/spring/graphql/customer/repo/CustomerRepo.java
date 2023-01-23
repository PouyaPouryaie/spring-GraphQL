package ir.bigz.spring.graphql.customer.repo;

import ir.bigz.spring.graphql.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerEntity, String> {

    @Query("SELECT "
            + "DISTINCT (customer) from CustomerEntity customer "
            + "JOIN FETCH "
            + "customer.purchaseTransactions purchaseTransaction "
            + "WHERE "
            + "(:fullName IS NULL OR customer.fullName = :fullName) AND "
            + "(:phoneNumber IS NULL OR customer.phoneNumber = :phoneNumber) AND "
            + "(:createdAt IS NULL OR customer.createdAt = :createdAt) AND"
            + "(:paymentType IS NULL OR purchaseTransaction.paymentType = :paymentType)")
    List<CustomerEntity> findCustomersWithFilters(String fullName, String phoneNumber, LocalDate createdAt,
                                                  String paymentType);
}
