package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.CountPixKeysGateway;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.SavePixKeyGateway;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.factory.LegalTypeFactory;
import com.open.pix.domain.interfaces.LegalType;

import java.util.List;
import java.util.Optional;

public class RegistrePixKeyUseCase {

    private final SavePixKeyGateway savePixKeyGateway;

    private final FindPixKeyGateway findPixKeyGateway;

    private final CountPixKeysGateway countPixKeysGateway;

    private final LegalTypeFactory legalTypeFactory;

    private final int defaultLimit = 5;

    public RegistrePixKeyUseCase(SavePixKeyGateway savePixKeyGateway,
                                 FindPixKeyGateway findPixKeyGateway,
                                 CountPixKeysGateway countPixKeysGateway,
                                 LegalTypeFactory legalTypeFactory) {
        this.savePixKeyGateway = savePixKeyGateway;
        this.findPixKeyGateway = findPixKeyGateway;
        this.countPixKeysGateway = countPixKeysGateway;
        this.legalTypeFactory = legalTypeFactory;
    }

    /**
     * Method checks if the pix key already exists on database.
     * @param pixKey the {@link PixKey} to be registered.
     * @throws PixRegistreException if the pix key is already registered.
     */
    private void checkExistingKey(PixKey pixKey) {
        Optional.ofNullable(findPixKeyGateway.findByPixValue(pixKey.getValue()))
            .ifPresentOrElse(
                v -> {
                    throw new PixRegistreException("The value " + pixKey.getValue() + " is already associated");
                },
                () -> {}
            );
    }

    /**
     * Method to collect the legal type based on business rules.
     * @param pixKey receives the pix key.
     * @return the {@link Optional} of {@link LegalType}.
     */
    private Optional<LegalType> findLegalType(PixKey pixKey) {
        List<PixKey> pixKeys = findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());
        return Optional.ofNullable(legalTypeFactory.resolve(pixKeys));
    }

    /**
     * Method to check the legal type and apply business rules.
     * @param pixKey receives the pix key object.
     * @throws PixRegistreException if business rules are violated.
     */
    private void checkLegalType(PixKey pixKey) {
        Optional<LegalType> legalType = findLegalType(pixKey);
        int count = countPixKeysGateway.countByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());
        legalType.ifPresentOrElse(
            type -> {
                if (count == type.limit()) {
                    throw new PixRegistreException(type.getLimitErrorMessage());
                }
                if (type.blocks().contains(pixKey.getPixType().type())) {
                    throw new PixRegistreException(type.getBlockErrorMessage());
                }
            },
            () -> {
                if (count == defaultLimit) {
                    throw new PixRegistreException("You're on default pix keys limit of 5, if you want to" +
                            "add more pix keys, inactivate one of yours or contact your agency");
                }
            }
        );
    }

    /**
     * Method registres the new pix key.
     * @param pixKey pix key to be registered.
     * @return the {@link PixKey} saved on database.
     */
    public PixKey registre(PixKey pixKey) {
        checkExistingKey(pixKey);
        checkLegalType(pixKey);
        return savePixKeyGateway.save(PixKey.registerNew(pixKey.getPixType(),
                                                        pixKey.getValue(),
                                                        pixKey.getAccountType(),
                                                        pixKey.getAgencyNumber(),
                                                        pixKey.getAccountNumber(),
                                                        pixKey.getFirstName(),
                                                        pixKey.getLastName()));
    }
}
