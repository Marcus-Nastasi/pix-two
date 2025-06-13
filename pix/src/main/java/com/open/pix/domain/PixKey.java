package com.open.pix.domain;

import com.open.pix.domain.enums.AccountNumber;
import com.open.pix.domain.enums.AccountType;
import com.open.pix.domain.enums.AgencyNumber;
import com.open.pix.domain.interfaces.PixType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Setter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PixKey {

    private UUID id;

    private PixType pixType;

    private String value;

    private AccountType accountType;

    private AgencyNumber agencyNumber;

    private AccountNumber accountNumber;

    private String firstName;

    private String lastName;

    private boolean active;

    private LocalDateTime creationDateTime;

    private LocalDateTime updateDateTime;

    private LocalDateTime inactivationDateTime;

    public static PixKey registerNew(PixType type,
                                 String value,
                                 AccountType accountType,
                                 AgencyNumber agency,
                                 AccountNumber accountNumber,
                                 String firstName,
                                 String lastName) {
        LocalDateTime now = LocalDateTime.now();
        return new PixKey(null,
                type,
                value,
                accountType,
                agency,
                accountNumber,
                firstName,
                lastName,
                true,
                now,
                now,
                null);
    }

    public PixKey inactivate() {
        if (!active) throw new RuntimeException("Já inativa");
        LocalDateTime now = LocalDateTime.now();
        return new PixKey(id,
                        pixType,
                        value,
                        accountType,
                        agencyNumber,
                        accountNumber,
                        firstName,
                        lastName,
                        false,
                        creationDateTime,
                        now,
                        now);
    }
}
