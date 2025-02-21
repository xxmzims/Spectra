package ru.ugrinovich.Spectra.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.request.Item.ItemFilterRequest;
import ru.ugrinovich.Spectra.request.Item.ItemType;

@Component
public class ItemSpecification {
    public Specification<Item> hasCategory(ItemType category){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category));
    }

    public Specification<Item> hasMinAmount(Integer amount){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), amount));
    }

    public Specification<Item> hasStartPrice(Double startPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), startPrice));
    }
    public Specification<Item> hasEndPrice(Double endPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), endPrice));
    }
    public Specification<Item> hasEndPriceAndStartPrice(Double endPrice, Double startPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), endPrice, startPrice));
    }

    public Specification<Item> toSpecForAllItems(ItemFilterRequest filter){
        Specification<Item> spec = Specification.where(null);
        if (filter.getCategory() != null)
            spec = spec.and(hasCategory(filter.getCategory()));
        if (filter.getAmount() != null)
            spec = spec.and(hasMinAmount(filter.getAmount()));
        if(filter.getStartPrice() != null && filter.getEndPrice() == null)
            spec = spec.and(hasStartPrice(filter.getStartPrice()));
        if(filter.getEndPrice() != null && filter.getStartPrice() == null)
            spec = spec.and(hasEndPrice(filter.getEndPrice()));
        if (filter.getStartPrice() != null && filter.getEndPrice() != null)
            spec = spec.and(hasEndPriceAndStartPrice(filter.getStartPrice(), filter.getEndPrice()));

        return spec;
    }
}
