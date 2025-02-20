package ru.ugrinovich.Spectra.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Item;
import ru.ugrinovich.Spectra.response.Item.ItemPurchaseStatus;
import ru.ugrinovich.Spectra.response.Item.ItemType;
import ru.ugrinovich.Spectra.response.Item.ItemViewStatus;


@Component
public class ItemSpecification {
    public Specification<Item> hasCategory(ItemType category){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category));
    }

    public Specification<Item> hasStatusView(ItemViewStatus viewStatus){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("view_status"), viewStatus));
    }

    public Specification<Item> hasStatusPurchase(ItemPurchaseStatus itemPurchaseStatus){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("purchase_status"), itemPurchaseStatus));
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
}
