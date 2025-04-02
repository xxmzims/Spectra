package ru.ugrinovich.Spectra.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.ItemPurchase;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class PurchaseSpecification {
    public static Specification<ItemPurchase> hasDate(Instant date) {
        Instant endDate = date.truncatedTo(ChronoUnit.DAYS);
        Instant startDate = date.plus(1, ChronoUnit.DAYS);
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("purchaseDate"), endDate, startDate));
    }
}
