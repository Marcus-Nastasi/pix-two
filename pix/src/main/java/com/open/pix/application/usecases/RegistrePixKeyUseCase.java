package com.open.pix.application.usecases;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.gateway.CountPixKeys;
import com.open.pix.application.gateway.FindPixKeyGateway;
import com.open.pix.application.gateway.RegistrePixKey;
import com.open.pix.domain.PixKey;

import java.util.Comparator;
import java.util.List;

public class RegistrePixKeyUseCase {

    private final RegistrePixKey registrePixKey;

    private final FindPixKeyGateway findPixKeyGateway;

    private final CountPixKeys countPixKeys;

    public RegistrePixKeyUseCase(RegistrePixKey registrePixKey,
                                 FindPixKeyGateway findPixKeyGateway,
                                 CountPixKeys countPixKeys) {
        this.registrePixKey = registrePixKey;
        this.findPixKeyGateway = findPixKeyGateway;
        this.countPixKeys = countPixKeys;
    }

    // TODO: refatorar para permimtir apenas cadastro OU de cpf OU de cnpj.
    private String findPfOrPjAccount(PixKey pixKey) {
        List<PixKey> pixKeys = findPixKeyGateway.findAllByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());
        return pixKeys.stream()
            .filter(k ->
                k.getPixType().type().equalsIgnoreCase("cpf") ||
                k.getPixType().type().equalsIgnoreCase("cnpj")
            )
            .min(Comparator.comparing(PixKey::getCreationDateTime))
            .map(k -> k.getPixType().type().equalsIgnoreCase("cpf") ? "PF" : "PJ")
            .orElse("");
    }

    private void validate(PixKey pixKey) {
        PixKey existingPixKey = findPixKeyGateway.findByPixValue(pixKey.getValue());
        if (existingPixKey != null) {
            throw new PixRegistreException("The value " + pixKey.getValue() + " is already associated");
        }

        String accountType = findPfOrPjAccount(pixKey);
        int count = countPixKeys.countByAccountNumberAndAgencyNumber(
                pixKey.getAccountNumber().value(),
                pixKey.getAgencyNumber().value());

        if (accountType.isEmpty()) return;

        if (accountType.equals("PF") && count == 5) {
            throw new PixRegistreException("Limit of 5 keys reached for individual account," +
                    " need to delete an existing key to add more.");
        }

        if (accountType.equals("PJ") && count == 20) {
            throw new PixRegistreException("Limit of 20 keys reached for legal entity account," +
                    " need to delete an existing key to add more.");
        }
    }

    public PixKey registre(PixKey pixKey) {
        validate(pixKey);
        return registrePixKey.registre(pixKey);
    }
}
