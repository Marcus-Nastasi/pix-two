package com.open.pix.adapters.mappers;

import com.open.pix.adapters.input.PixKeyRegistreRequest;
import com.open.pix.adapters.input.PixKeyUpdateRequest;
import com.open.pix.domain.PixKey;
import com.open.pix.domain.types.AccountNumber;
import com.open.pix.domain.types.AccountType;
import com.open.pix.domain.types.AgencyNumber;
import com.open.pix.domain.factory.PixTypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PixKeyRequestMapper {

    private final PixTypeFactory pixTypeFactory;

    public PixKey fromRegistre(PixKeyRegistreRequest pixKey) {
        return PixKey.builder()
                .pixType(pixTypeFactory.newPixType(pixKey.pixType(), pixKey.value()))
                .value(pixKey.value())
                .accountType(new AccountType(pixKey.accountType()))
                .agencyNumber(new AgencyNumber(pixKey.agencyNumber()))
                .accountNumber(new AccountNumber(pixKey.accountNumber()))
                .firstName(pixKey.firstName())
                .lastName(pixKey.lastName())
                .build();
    }

    public static PixKey fromUpdate(PixKeyUpdateRequest pixKey) {
        return PixKey.builder()
                .id(pixKey.id())
                .accountType(new AccountType(pixKey.accountType()))
                .agencyNumber(new AgencyNumber(pixKey.agencyNumber()))
                .accountNumber(new AccountNumber(pixKey.accountNumber()))
                .firstName(pixKey.firstName())
                .lastName(pixKey.lastName())
                .build();
    }
}
