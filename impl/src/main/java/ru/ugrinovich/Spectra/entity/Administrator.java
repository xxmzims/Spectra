package ru.ugrinovich.Spectra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Administrator")
public class Administrator {

    @Id
    @Column(name="admin_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;

    @Column(name = "name")
    private String name;
}
