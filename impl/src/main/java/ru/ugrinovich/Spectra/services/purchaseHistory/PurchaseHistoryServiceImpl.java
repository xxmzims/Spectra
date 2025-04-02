package ru.ugrinovich.Spectra.services.purchaseHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ugrinovich.Spectra.entities.ItemPurchase;
import ru.ugrinovich.Spectra.mappers.PurchaseHistoryMapper;
import ru.ugrinovich.Spectra.repositories.jpa.PurchaseHistoryJpa;
import ru.ugrinovich.Spectra.specification.PurchaseSpecification;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService{

    private final PurchaseHistoryJpa purchaseHistoryJpa;
    private final PurchaseHistoryMapper purchaseHistoryMapper;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    @Async
    @Transactional
    public void loadReport() {
        Specification<ItemPurchase> spec = PurchaseSpecification.hasDate(Instant.now());
        List<ItemPurchase> purchases = purchaseHistoryJpa.findAll(spec);
        if (!purchases.isEmpty()){
            System.out.println(purchaseHistoryMapper.toResponseList(purchases));
        }
    }
}
