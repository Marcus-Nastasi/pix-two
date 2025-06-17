package com.open.pix.domain;

import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.types.AgencyNumber;
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

    /**
     * Method responsible to generate a new instance of {@link PixKey}
     */
    public static PixKey registerNew(PixType type,
                                     String value,
                                     AccountType accountType,
                                     AgencyNumber agency,
                                     AccountNumber accountNumber,
                                     String firstName,
                                     String lastName) {
        if (firstName == null || firstName.isEmpty() || firstName.isBlank()) {
            throw new PixKeyException("First name must not be null, empty or blank");
        }
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

    /**
     * Method responsible to update safely the pix key.
     * @return the {@link PixKey} updated.
     */
    public PixKey update(AccountType accountType,
                         AgencyNumber agency,
                         AccountNumber accountNumber,
                         String firstName,
                         String lastName) {
        if (firstName == null || firstName.isEmpty() || firstName.isBlank()) {
            throw new PixKeyException("First name must not be null, empty or blank");
        }
        if (!active) {
            throw new PixKeyException("It's not allowed to update inactive keys");
        }
        setAccountType(accountType);
        setAgencyNumber(agency);
        setAccountNumber(accountNumber);
        setFirstName(firstName);
        if (lastName != null) {
            setLastName(lastName);
        }
        setUpdateDateTime(LocalDateTime.now());
        return this;
    }

    /**
     * Method responsible to inactivate the pix key.
     */
    public void inactivate() {
        if (!active) throw new PixKeyException("Already inactivated");
        LocalDateTime now = LocalDateTime.now();
        setActive(false);
        setUpdateDateTime(now);
        setInactivationDateTime(now);
    }
}
