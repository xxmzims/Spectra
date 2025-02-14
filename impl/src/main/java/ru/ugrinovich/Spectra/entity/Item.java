package ru.ugrinovich.Spectra.entity;


import jakarta.persistence.*;
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
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private ItemType category;

    @CreationTimestamp
    @Column(name = "create_at")
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
