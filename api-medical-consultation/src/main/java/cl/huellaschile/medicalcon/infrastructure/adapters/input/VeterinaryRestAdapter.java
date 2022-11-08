package cl.huellaschile.medicalcon.infrastructure.adapters.input;

import cl.huellaschile.medicalcon.application.ports.input.CreateVeterinaryUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteVeterinaryUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetVeterinaryUseCase;
import cl.huellaschile.medicalcon.domain.model.Veterinary;
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
public class VeterinaryRestAdapter {

    private final GetVeterinaryUseCase getVeterinaryUseCase;
    private final CreateVeterinaryUseCase createVeterinaryUseCase;
    private final DeleteVeterinaryUseCase deleteVeterinaryUseCase;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get veterinary by id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get veterinary by id",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Veterinary.class))})
    })
    public ResponseEntity<Veterinary> getById(@PathVariable("id") String id) {
        final Veterinary veterinary = getVeterinaryUseCase.getById(id);
        veterinary.add(linkTo(methodOn(VeterinaryRestAdapter.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(VeterinaryRestAdapter.class)
                        .save(veterinary))
                        .withRel("save"),
                linkTo(methodOn(VeterinaryRestAdapter.class)
                        .deleteById(id))
                        .withRel("deleteById"));
        return ResponseEntity.ok(veterinary);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Veterinary created",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Veterinary.class))})
    })
    public ResponseEntity<Veterinary> save(@Valid @RequestBody Veterinary veterinary) {
        final Veterinary veterinaryCreated = createVeterinaryUseCase.save(veterinary);
        veterinary.add(linkTo(methodOn(VeterinaryRestAdapter.class)
                .getById(veterinaryCreated.getProfessionalLicenseNumber()))
                .withSelfRel());
        return new ResponseEntity<>(veterinary, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Veterinary deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {
        deleteVeterinaryUseCase.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
