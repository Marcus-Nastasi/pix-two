package com.open.pix.infra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "pix_key", indexes = {
    @Index(name = "idx_pix_type", columnList = "type"),
    @Index(name = "idx_account", columnList = "agency, account_number"),
    @Index(name = "idx_name", columnList = "first_name"),
    @Index(name = "idx_creation_datetime", columnList = "creation_datetime"),
    @Index(name = "idx_inactivation_datetime", columnList = "inactivation_datetime")
})
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @NotBlank
    @Size(max = 9)
    @Column(name = "type", length = 9, nullable = false)
    private String pixType;

    @NotBlank
    @Size(max = 77)
    @Column(name = "value", length = 77, nullable = false, unique = true)
    private String value;

    @NotBlank
    @Size(max = 10)
    @Column(name = "account_type", length = 10, nullable = false)
    private String accountType;

    @NotNull
    @Min(0)
    @Max(9999)
    @Digits(integer = 4, fraction = 0)
    @Column(name = "agency", nullable = false)
    private Integer agencyNumber;

    @NotNull
    @Min(0)
    @Max(99999999)
    @Digits(integer = 8, fraction = 0)
    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    @NotBlank
    @Column(name = "first_name", length = 30, nullable = false)
    @Size(min = 1, max = 30)
    private String firstName;

    @Column(name = "last_name", length = 45)
    @Size(max = 45)
    private String lastName;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @CreationTimestamp
    @Column(name = "creation_datetime", nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updateDateTime;

    @Column(name = "inactivation_datetime")
    private LocalDateTime inactivationDateTime;
}
