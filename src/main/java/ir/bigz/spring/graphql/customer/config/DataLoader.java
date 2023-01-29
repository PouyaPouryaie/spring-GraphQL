package ir.bigz.spring.graphql.customer.config;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import ir.bigz.spring.graphql.customer.entity.CustomerEntity;
import ir.bigz.spring.graphql.customer.entity.PurchaseTransactionEntity;
import ir.bigz.spring.graphql.customer.repo.CustomerRepo;
import ir.bigz.spring.graphql.customer.repo.PurchaseTransactionRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
public record DataLoader(CustomerRepo customerRepo, PurchaseTransactionRepo purchaseTransactionRepo) {

    @Bean
    private InitializingBean sendDatabase() {
        Faker faker = new Faker();
        return () -> customerRepo.saveAll(generateCustomerList(faker));
    }

    private List<CustomerEntity> generateCustomerList(Faker faker) {
        return IntStream.range(0, 10)
                .mapToObj(i -> CustomerEntity.builder().createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .fullName(faker.name().fullName())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .address(faker.address().fullAddress())
                        .purchaseTransactions(generatePurchaseTransactionList(faker))
                        .build())
                .collect(toList());
    }

    private List<PurchaseTransactionEntity> generatePurchaseTransactionList(Faker faker) {
        List<PurchaseTransactionEntity> purchaseTransactionEntityList = IntStream.range(0, new Random().nextInt(10))
                .mapToObj(i -> PurchaseTransactionEntity.builder().createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .amount(new BigDecimal(faker.commerce().price().replaceAll(",", ".")))
                        .paymentType(List.of(CreditCardType.values())
                                .get(new Random().nextInt(CreditCardType.values().length)).toString())
                        .build())
                .collect(toList());
        purchaseTransactionRepo.saveAll(purchaseTransactionEntityList);
        return purchaseTransactionEntityList;
    }
}
