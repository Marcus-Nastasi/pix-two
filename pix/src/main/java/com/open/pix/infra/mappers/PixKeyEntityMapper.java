package com.open.pix.infra.mappers;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.infra.entity.PixKeyEntity;

public class PixKeyEntityMapper {

    public static PixKey toDomain(PixKeyEntity pixKey) {
        return PixKey.builder()
                .id(pixKey.getId())
                .pixType(PixTypeFactory.newPixType(pixKey.getPixType(), pixKey.getValue()))
                .value(pixKey.getValue())
                .accountType(new AccountType(pixKey.getAccountType()))
                .agencyNumber(new AgencyNumber(pixKey.getAgencyNumber()))
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
                .agencyNumber(pixKey.getAgencyNumber().value())
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
