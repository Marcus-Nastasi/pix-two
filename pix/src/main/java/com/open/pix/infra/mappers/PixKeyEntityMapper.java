package com.open.pix.infra.mappers;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.enums.AccountNumber;
import com.open.pix.domain.enums.AccountType;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.infra.entity.PixKeyEntity;

public class PixKeyEntityMapper {

    public static PixKey toDomain(PixKeyEntity pixKey) {
        return PixKey.builder()
                .id(pixKey.getId())
                .pixType(PixTypeFactory.newPixType(pixKey.getPixType(), pixKey.getValue()))
                .value(pixKey.getValue())
                .accountType(new AccountType(pixKey.getAccountType()))
                .accountNumber(new AccountNumber(pixKey.getAccountNumber()))
                .firstName(pixKey.getFirstName())
                .lastName(pixKey.getLastName())
                .active(pixKey.isActive())
                .creationDateTime(pixKey.getCreationDateTime())
                .updateDateTime(pixKey.getUpdateDateTime())
                .inactivationDateTime(pixKey.getInactivationDateTime())
                .build();
    }

    public static PixKeyEntity toEntity(PixKey pixKey) {
        return PixKeyEntity.builder()
                .id(pixKey.getId())
                .pixType(pixKey.getPixType().type())
                .value(pixKey.getValue())
                .accountType(pixKey.getAccountType().type())
                .accountNumber(pixKey.getAccountNumber().value())
                .firstName(pixKey.getFirstName())
                .lastName(pixKey.getLastName())
                .active(pixKey.isActive())
                .creationDateTime(pixKey.getCreationDateTime())
                .updateDateTime(pixKey.getUpdateDateTime())
                .inactivationDateTime(pixKey.getInactivationDateTime())
                .build();
    }
}
