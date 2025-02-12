package ru.ugrinovich.Spectra.repositories;

import org.springframework.stereotype.Component;
import ru.ugrinovich.Spectra.models.Buyer;
import ru.ugrinovich.Spectra.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuyerRepository {
    private int id = 0;
    private final List<Buyer> buyers = new ArrayList<>();

    public void save(Buyer buyer){
        buyer.setId(++id);
        buyers.add(buyer);
    }
    public List<Buyer> findAll(){
        return buyers;
    }
    public void deleteById(int id){
         Buyer buyer = findById(id).get();
        buyers.remove(buyer);
    }
    public void updateById(int id, Buyer buyer){
        Buyer buyerUpdated = findById(id).get();
        buyerUpdated.setAge(buyer.getAge());
        buyerUpdated.setEmail(buyer.getEmail());
        buyerUpdated.setFirstName(buyer.getFirstName());
        buyerUpdated.setSecondName(buyer.getSecondName());
        buyerUpdated.setItems(buyer.getItems());
    }
    public Optional<Buyer> findById(int id){
        return buyers.stream().filter(x -> x.getId() == id).findAny();
    }

    public void assignItemByBuyerId(int id, Item item){
        Buyer buyer = findById(id).get();

        if(buyer.getItems() == null){
            buyer.setItems(new ArrayList<>());
        }
        buyer.getItems().add(item);
    }
}
