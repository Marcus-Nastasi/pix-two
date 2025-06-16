package com.open.pix.adapters.resources;

import com.open.pix.adapters.input.PixKeyRegistreRequest;
import com.open.pix.adapters.input.PixKeyUpdateRequest;
import com.open.pix.adapters.mappers.PixKeyRequestMapper;
import com.open.pix.adapters.mappers.PixKeyResponseMapper;
import com.open.pix.adapters.output.PixKeyResponse;
import com.open.pix.adapters.output.PixKeyUpdateResponse;
import com.open.pix.application.usecases.*;
import com.open.pix.domain.PixKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pix")
public class PixKeyResources {

    private final FindPixKeysUseCase findPixKeysUseCase;

    private final RegistrePixKeyUseCase registrePixKeyUseCase;

    private final UpdatePixKeyUseCase updatePixKeyUseCase;

    private final InactivatePixKeyUseCase inactivatePixKeyUseCase;

    private final SearchPixKeysUseCase searchPixKeysUseCase;

    @GetMapping
    public ResponseEntity<List<PixKeyResponse>> findAll() {
        return ResponseEntity.ok(findPixKeysUseCase.findAll().stream()
                .map(PixKeyResponseMapper::toResponse)
                .toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PixKeyResponse>> search(@RequestParam(required = false) String keyType,
                                                      @RequestParam(required = false) Integer agencyNumber,
                                                      @RequestParam(required = false) Integer accountNumber,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDateTime creationDate,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                          LocalDateTime inactivationDate) {
        List<PixKeyResponse> pixKeys = searchPixKeysUseCase.search(keyType,
                agencyNumber,
                accountNumber,
                name,
                creationDate,
                inactivationDate).stream().map(PixKeyResponseMapper::toResponse).toList();
        return ResponseEntity.ok(pixKeys);
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> registre(@RequestBody @Valid PixKeyRegistreRequest request) {
        PixKey newPixKey = registrePixKeyUseCase.registre(PixKeyRequestMapper.fromRegistre(request));
        return ResponseEntity.ok(Map.of("id", newPixKey.getId()));
    }

    @PatchMapping
    public ResponseEntity<PixKeyUpdateResponse> update(@RequestBody @Valid PixKeyUpdateRequest request) {
        return ResponseEntity.ok(PixKeyResponseMapper.toUpdateResponse(
                updatePixKeyUseCase.update(PixKeyRequestMapper.fromUpdate(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponse> inactivate(@PathVariable("id") @Valid UUID id) {
        return ResponseEntity.ok(PixKeyResponseMapper.toResponse(inactivatePixKeyUseCase.inactivate(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixKeyResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(PixKeyResponseMapper.toResponse(findPixKeysUseCase.findById(id)));
    }
}
