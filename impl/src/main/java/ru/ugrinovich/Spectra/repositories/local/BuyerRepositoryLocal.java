package ru.ugrinovich.Spectra.repositories.local;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.entities.Buyer;
import ru.ugrinovich.Spectra.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BuyerRepositoryLocal {
    private final List<Buyer> buyers = new ArrayList<>();

    public void save(Buyer buyer){
        buyers.add(buyer);
    }
    public List<Buyer> findAll(){
        return buyers;
    }
    public void deleteById(UUID id){
         Buyer buyer = findById(id).get();
        buyers.remove(buyer);
    }
    public void updateById(UUID id, Buyer updatedBuyer){
        buyers.stream().filter(buyer -> buyer.getId().equals(id)).findFirst().map(buyer -> updatedBuyer);
    }
    public Optional<Buyer> findById(UUID id){
        return buyers.stream().filter(buyer -> buyer.getId().equals(id)).findFirst();
    }

}
