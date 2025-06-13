package com.open.pix.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pix_key")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "type", length = 9, nullable = false)
    private String pixType;

    @Column(name = "value", length = 77, nullable = false)
    private String value;

    @Column(name = "account_type", length = 10, nullable = false)
    private String accountType;

    @Column(name = "agency", nullable = false)
    private Integer agencyNumber;

    @Column(name = "account_number", nullable = false)
    private Integer accountNumber;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "creation_datetime", nullable = false)
    private LocalDateTime creationDateTime;

    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updateDateTime;

    @Column(name = "inactivation_datetime")
    private LocalDateTime inactivationDateTime;
}
