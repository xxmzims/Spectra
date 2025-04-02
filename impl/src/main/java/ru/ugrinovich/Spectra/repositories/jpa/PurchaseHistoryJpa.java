package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.ItemPurchase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseHistoryJpa extends JpaRepository<ItemPurchase, UUID>, JpaSpecificationExecutor<ItemPurchase> {
    Optional<List<ItemPurchase>> findByBuyer(Buyer buyer);
}
