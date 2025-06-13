package com.open.pix.infra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pix_key")
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @NotBlank
    @Column(name = "type", length = 9, nullable = false)
    @Size(max = 9)
    private String pixType;

    @NotBlank
    @Column(name = "value", length = 77, nullable = false)
    @Size(max = 77)
    private String value;

    @NotBlank
    @Column(name = "account_type", length = 10, nullable = false)
    @Size(max = 10)
    private String accountType;

    @Column(name = "agency", nullable = false, columnDefinition = "NUMERIC(4)")
    @Min(0)
    @Max(9999)
    private Integer agencyNumber;

    @Column(name = "account_number", nullable = false, columnDefinition = "NUMERIC(8)")
    @Min(0)
    @Max(99999999)
    private Integer accountNumber;

    @NotBlank
    @Column(name = "first_name", length = 30, nullable = false)
    @Size(min = 1, max = 30)
    private String firstName;

    @Column(name = "last_name", length = 45)
    @Size(max = 45)
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
