package ru.ugrinovich.Spectra.models;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Buyer {

    private int id;

    private String firstName;

    private String secondName;

    private int age;

    private String email;

    private List<Item> Items;
}
