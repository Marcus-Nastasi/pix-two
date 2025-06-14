package com.open.pix.adapters.resources;

import com.open.pix.adapters.mappers.PixKeyResponseMapper;
import com.open.pix.adapters.output.PixKeyResponse;
import com.open.pix.application.usecases.FindPixKeysUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        try {
            return ResponseEntity.ok(PixKeyResponseMapper.toResponse(findPixKeysUseCase.findById(id)));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
