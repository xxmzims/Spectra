package ru.ugrinovich.Spectra.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.request.Item.ItemType;

import java.util.Optional;

@Component
public class ItemSpecification {

    public Specification<Item> toSpecForAllItems(ItemFilterRequest filter) {

        return Optional.ofNullable(filter)
                .map(ItemSpecification::categorySpec)
                .map(spec -> spec.and(getFilterCategory(filter)))
                .map(spec -> spec.and(getFilterAmount(filter)))
                .map(spec -> spec.and(getFilterStartPrice(filter)))
                .map(spec -> spec.and(getFilterEndPrice(filter)))
                .orElse(categorySpec(filter));

    }

    private static Specification<Item> categorySpec(ItemFilterRequest filterRequest) {
        return Specification.where(null);
    }

    private static Specification<Item> hasCategory(ItemType category) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category));
    }

    private static Specification<Item> hasMinAmount(Integer amount) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), amount));
    }

    private static Specification<Item> hasStartPrice(Double startPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), startPrice));
    }

    private static Specification<Item> hasEndPrice(Double endPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), endPrice));
    }

    private static Specification<Item> getFilterCategory(ItemFilterRequest filter) {
        return Optional.ofNullable(filter)
                .map(ItemFilterRequest::getCategory)
                .map(ItemSpecification::hasCategory)
                .orElse(null);
    }

    private static Specification<Item> getFilterAmount(ItemFilterRequest filter) {
        return Optional.ofNullable(filter)
                .map(ItemFilterRequest::getAmount)
                .map(ItemSpecification::hasMinAmount)
                .orElse(null);
    }

    private static Specification<Item> getFilterStartPrice(ItemFilterRequest filter) {
        return Optional.ofNullable(filter)
                .map(ItemFilterRequest::getStartPrice)
                .map(ItemSpecification::hasStartPrice)
                .orElse(null);
    }

    private static Specification<Item> getFilterEndPrice(ItemFilterRequest filter) {
        return Optional.ofNullable(filter)
                .map(ItemFilterRequest::getEndPrice)
                .map(ItemSpecification::hasEndPrice)
                .orElse(null);
    }
}
