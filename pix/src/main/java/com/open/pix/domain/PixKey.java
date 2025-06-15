package com.open.pix.domain;

import com.open.pix.domain.enums.AccountNumber;
import com.open.pix.domain.enums.AccountType;
import com.open.pix.domain.enums.AgencyNumber;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.interfaces.PixType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Setter
@Builder
@NoArgsConstructor
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

    public PixKey update(AccountType accountType,
                         AgencyNumber agency,
                         AccountNumber accountNumber,
                         String firstName,
                         String lastName) {
        setAccountType(accountType);
        setAgencyNumber(agency);
        setAccountNumber(accountNumber);
        setFirstName(firstName);
        if (!lastName.isEmpty() || !lastName.isBlank()) {
            setLastName(lastName);
        }
        return this;
    }

    public void inactivate() {
        if (!active) throw new PixKeyException("Already inactivated");
        LocalDateTime now = LocalDateTime.now();
        setActive(false);
        setUpdateDateTime(now);
        setInactivationDateTime(now);
    }
}
