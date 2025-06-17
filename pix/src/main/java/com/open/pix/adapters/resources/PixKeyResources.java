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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    private final PixKeyRequestMapper pixKeyRequestMapper;

    @GetMapping
    @Cacheable("pix_key")
    public List<PixKeyResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return findPixKeysUseCase.findAll(page, size).stream()
                .map(PixKeyResponseMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @Cacheable("pix_key")
    public PixKeyResponse findById(@PathVariable("id") UUID id) {
        return PixKeyResponseMapper.toResponse(findPixKeysUseCase.findById(id));
    }

    @GetMapping("/search")
    @Cacheable("pix_key")
    public List<PixKeyResponse> search(@RequestParam(required = false) String keyType,
                                                      @RequestParam(required = false) Integer agencyNumber,
                                                      @RequestParam(required = false) Integer accountNumber,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                           LocalDateTime creationDate,
                                                      @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                          LocalDateTime inactivationDate,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return searchPixKeysUseCase.search(keyType,
                agencyNumber,
                accountNumber,
                name,
                creationDate,
                inactivationDate,
                page,
                size).stream().map(PixKeyResponseMapper::toResponse).toList();
    }

    @PostMapping
    @CacheEvict(value = "pix_key", allEntries = true)
    public ResponseEntity<Map<String, UUID>> registre(@RequestBody @Valid PixKeyRegistreRequest request) {
        PixKey newPixKey = registrePixKeyUseCase.registre(pixKeyRequestMapper.fromRegistre(request));
        return ResponseEntity.ok(Map.of("id", newPixKey.getId()));
    }

    @PatchMapping
    @CacheEvict(value = "pix_key", allEntries = true)
    public ResponseEntity<PixKeyUpdateResponse> update(@RequestBody @Valid PixKeyUpdateRequest request) {
        return ResponseEntity.ok(PixKeyResponseMapper.toUpdateResponse(
                updatePixKeyUseCase.update(PixKeyRequestMapper.fromUpdate(request))));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "pix_key", allEntries = true)
    public ResponseEntity<PixKeyResponse> inactivate(@PathVariable("id") @Valid UUID id) {
        return ResponseEntity.ok(PixKeyResponseMapper.toResponse(inactivatePixKeyUseCase.inactivate(id)));
    }
}
