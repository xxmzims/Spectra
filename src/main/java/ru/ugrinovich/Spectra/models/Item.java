package ru.ugrinovich.Spectra.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    private int id;

    private String name;

    private String serialNumber;

    private String description;

    private ItemCategories category;

}
