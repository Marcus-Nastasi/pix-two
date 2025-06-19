package com.open.pix.infra.mappers;

import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.factory.AccountTypeFactory;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.factory.PixTypeFactory;
import com.open.pix.infra.entity.PixKeyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PixKeyEntityMapper {

    private final PixTypeFactory pixTypeFactory;

    private final AccountTypeFactory accountTypeFactory;

    public PixKey toDomain(PixKeyEntity pixKey) {
        return PixKey.builder()
                .id(pixKey.getId())
                .pixType(pixTypeFactory.newPixType(pixKey.getPixType(), pixKey.getValue()))
                .value(pixKey.getValue())
                .accountType(accountTypeFactory.resolve(pixKey.getAccountType()))
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
                .accountType(pixKey.getAccountType().value())
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
