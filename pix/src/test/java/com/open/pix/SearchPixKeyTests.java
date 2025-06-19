package com.open.pix;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.gateway.SearchPixKeyGateway;
import com.open.pix.application.usecases.SearchPixKeysUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchPixKeyTests {

    @Spy
    @InjectMocks
    private SearchPixKeysUseCase useCase;

    @Mock
    private SearchPixKeyGateway gateway;

    @Test
    void mustThrowNotFoundExceptionWhenNoResults() {
        when(gateway.search(any(), any(), any(), any(), any(), any(), anyInt(), anyInt()))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> {
            useCase.search(null, null, null, null, null, null, 0, 10);
        });

        verify(gateway, times(1)).search(null, null, null, null, null, null, 0, 10);
    }
}
