package com.open.pix.adapters.resources;

import com.open.pix.adapters.input.PixKeyRegistreRequest;
import com.open.pix.adapters.mappers.PixKeyRequestMapper;
import com.open.pix.adapters.mappers.PixKeyResponseMapper;
import com.open.pix.adapters.output.PixKeyResponse;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import com.open.pix.application.usecases.RegistrePixKeyUseCase;
import com.open.pix.domain.PixKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pix")
public class PixKeyResources {

    private final FindPixKeysUseCase findPixKeysUseCase;

    private final RegistrePixKeyUseCase registrePixKeyUseCase;

    @PostMapping
    public ResponseEntity<PixKeyResponse> registre(@RequestBody @Valid PixKeyRegistreRequest request) {
        PixKey newPixKey = registrePixKeyUseCase.registre(PixKeyRequestMapper.fromRegistre(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(PixKeyResponseMapper.toResponse(newPixKey));
    }

    @GetMapping
    public ResponseEntity<Set<PixKeyResponse>> findAll() {
        Set<PixKeyResponse> pixKeyResponses = findPixKeysUseCase.findAll().stream()
                .map(PixKeyResponseMapper::toResponse)
                .collect(Collectors.toSet());
        if (pixKeyResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pixKeyResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixKeyResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(PixKeyResponseMapper.toResponse(findPixKeysUseCase.findById(id)));
    }
}
