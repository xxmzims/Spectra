package ru.ugrinovich.Spectra.request.Item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ItemTypeSort {
    DATE_ASC(Sort.by(Sort.Direction.ASC, "createAt")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "createAt")),
    PRICE_DESC(Sort.by(Sort.Direction.DESC, "price")),
    PRICE_ASC(Sort.by(Sort.Direction.ASC, "price")),
    AMOUNT_ASC(Sort.by(Sort.Direction.ASC, "amount")),
    AMOUNT_DESC(Sort.by(Sort.Direction.DESC, "amount"));

    private final Sort sortValue;
}
