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
@Table(name = "Administrator")
public class Administrator {

    @Id
    @Column(name="admin_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updateAt;
}
