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

    private Optional<LegalType> findLegalType(PixKey pixKey) {
        List<PixKey> pixKeys = findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());
        return Optional.ofNullable(legalTypeFactory.resolve(pixKeys));
    }

    private void checkExistingKey(PixKey pixKey) {
        PixKey existingPixKey = findPixKeyGateway.findByPixValue(pixKey.getValue());
        if (existingPixKey != null) {
            throw new PixRegistreException("The value " + pixKey.getValue() + " is already associated");
        }
    }

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
                            "add more pix keys, inactivate one of yours, or contact your agency");
                }
            }
        );
    }

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
