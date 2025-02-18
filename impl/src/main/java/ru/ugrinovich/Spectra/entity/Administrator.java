package ru.ugrinovich.Spectra.entity;

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
@Table(name = "administrator")
public class Administrator {

    @NotNull
    @Id
    @Column(name="admin_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;

    @NotNull
    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateAt;
}
