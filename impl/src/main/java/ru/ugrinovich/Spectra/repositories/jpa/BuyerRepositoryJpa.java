package ru.ugrinovich.Spectra.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ugrinovich.Spectra.entities.Buyer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerRepositoryJpa extends JpaRepository<Buyer, UUID> {
    Optional<Buyer> findBuyerByEmail(String email);

    @Query("select b from Buyer b left join fetch b.purchases pur left join fetch pur.item")
    Optional<List<Buyer>> findAllBuyersWithItems();
}
