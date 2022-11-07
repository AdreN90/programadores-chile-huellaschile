package cl.programadoreschile.adrian.veterinary.controller;

import cl.programadoreschile.adrian.veterinary.domain.services.VeterinaryService;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/veterinary")
@RequiredArgsConstructor
public class VeterinaryController {

    private final VeterinaryService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get veterinary by Professional License Number")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get veterinary by Professional License Number",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VeterinaryDAO.class))})
    })
    public ResponseEntity<VeterinaryDAO> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.findByProfessionalLicenseNumber(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Save veterinary",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VeterinaryDAO.class))})
    })
    public ResponseEntity<VeterinaryDAO> save(@Valid @RequestBody VeterinaryDAO veterinary) {
        return new ResponseEntity<>(service.createVeterinary(veterinary), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Veterinary deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }
}
