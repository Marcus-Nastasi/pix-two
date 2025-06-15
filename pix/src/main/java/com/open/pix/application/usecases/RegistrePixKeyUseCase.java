package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.CountPixKeysGateway;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.domain.PixKey;

import java.util.List;
import java.util.Optional;

public class RegistrePixKeyUseCase {

    private final RegistrePixKey registrePixKey;

    private final FindPixKeyGateway findPixKeyGateway;

    private final CountPixKeysGateway countPixKeysGateway;

    private static final String PJ = "PJ";

    private static final String PF = "PF";

    public RegistrePixKeyUseCase(RegistrePixKey registrePixKey,
                                 FindPixKeyGateway findPixKeyGateway,
                                 CountPixKeysGateway countPixKeysGateway) {
        this.registrePixKey = registrePixKey;
        this.findPixKeyGateway = findPixKeyGateway;
        this.countPixKeysGateway = countPixKeysGateway;
    }

    // TODO: create account type resolver (factory strategy) to resolve this types
    private String findPfOrPjAccount(PixKey pixKey) {
        List<PixKey> pixKeys = findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());
        Optional<String> type = pixKeys.stream()
                .filter(k -> k.getPixType().type().equalsIgnoreCase("cpf")
                        || k.getPixType().type().equalsIgnoreCase("cnpj"))
                .map(k -> k.getPixType().type())
                .findFirst();
        return switch (type.orElse("")) {
            case "cpf" -> PF;
            case "cnpj" -> PJ;
            default -> "";
        };
    }

    private void checkExistingKey(PixKey pixKey) {
        PixKey existingPixKey = findPixKeyGateway.findByPixValue(pixKey.getValue());
        if (existingPixKey != null) {
            throw new PixRegistreException("The value " + pixKey.getValue() + " is already associated");
        }
    }

    private void checkPfOrPj(PixKey pixKey) {
        String accountType = findPfOrPjAccount(pixKey);
        int count = countPixKeysGateway.countByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());

        // Case pessoa física:
        if (accountType.isEmpty() || PF.equals(accountType)) {
            if (count == 5) {
                throw new PixRegistreException("Limit of 5 keys reached for individual account," +
                        " need to delete an existing key to add more.");
            }
            if ("cnpj".equals(pixKey.getPixType().type())) {
                throw new PixRegistreException("Your account is type PF, cannot registre a CNPJ as pix key");
            }
        }

        // Case pessoa jurídica:
        if (PJ.equals(accountType)) {
            if (count == 20) {
                throw new PixRegistreException("Limit of 20 keys reached for legal entity account," +
                        " need to delete an existing key to add more.");
            }
            if ("cpf".equals(pixKey.getPixType().type())) {
                throw new PixRegistreException("Your account is type PJ, cannot registre a CPF as pix key");
            }
        }
    }

    public PixKey registre(PixKey pixKey) {
        checkExistingKey(pixKey);
        checkPfOrPj(pixKey);
        return registrePixKey.registre(pixKey);
    }
}
