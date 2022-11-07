package cl.programadoreschile.adrian.veterinary.controller;

import cl.programadoreschile.adrian.veterinary.domain.entities.VeterinaryDTO;
import cl.programadoreschile.adrian.veterinary.domain.services.VeterinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VeterinaryDTO.class))})
    })
    public ResponseEntity<VeterinaryDTO> getById(@PathVariable("id") String id) {
        final VeterinaryDTO veterinary = service.findByProfessionalLicenseNumber(id);
        veterinary.add(linkTo(methodOn(VeterinaryController.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(VeterinaryController.class)
                        .save(veterinary))
                        .withRel("save"),
                linkTo(methodOn(VeterinaryController.class)
                        .delete(id))
                        .withRel("delete"));
        return ResponseEntity.ok(veterinary);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Save veterinary",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VeterinaryDTO.class))})
    })
    public ResponseEntity<VeterinaryDTO> save(@Valid @RequestBody VeterinaryDTO veterinary) {
        final VeterinaryDTO veterinaryCreated = service.createVeterinary(veterinary);
        veterinary.add(linkTo(methodOn(VeterinaryController.class)
                .getById(veterinaryCreated.getProfessionalLicenseNumber()))
                .withSelfRel());
        return new ResponseEntity<>(veterinaryCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Veterinary deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = VeterinaryDTO.class))})
    })
    public ResponseEntity<VeterinaryDTO> delete(@PathVariable("id") String id) {
        final VeterinaryDTO veterinaryDeleted = service.delete(id);
        veterinaryDeleted.add(linkTo(methodOn(VeterinaryController.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(VeterinaryController.class)
                        .save(veterinaryDeleted))
                        .withRel("save"),
                linkTo(methodOn(VeterinaryController.class)
                        .delete(id))
                        .withRel("delete"));
        return ResponseEntity.ok(veterinaryDeleted);
    }
}
