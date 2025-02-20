package ru.ugrinovich.Spectra.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ItemType category;

    @Column(name = "purchase_status")
    @Enumerated(EnumType.STRING)
    private ItemPurchaseStatus purchaseStatus;

    @Column(name="view_status")
    @Enumerated(EnumType.STRING)
    private ItemViewStatus itemViewStatus;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateAt;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "buyer_id")
    private Buyer buyer;
}
