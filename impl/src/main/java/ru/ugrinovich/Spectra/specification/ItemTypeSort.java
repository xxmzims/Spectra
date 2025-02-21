package ru.ugrinovich.Spectra.specification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ItemTypeSort {
    DATE_ASC(Sort.by(Sort.Direction.ASC, "create_at")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "create_at")),
    PRICE_DESC(Sort.by(Sort.Direction.DESC, "price")),
    PRICE_ASC(Sort.by(Sort.Direction.ASC, "price")),
    AMOUNT_ASC(Sort.by(Sort.Direction.ASC, "amount")),
    AMOUNT_DESC(Sort.by(Sort.Direction.DESC, "amount"));

    private final Sort sortValue;
}
