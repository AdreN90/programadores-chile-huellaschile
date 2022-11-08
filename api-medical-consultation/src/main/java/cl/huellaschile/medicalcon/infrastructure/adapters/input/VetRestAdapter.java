package cl.huellaschile.medicalcon.infrastructure.adapters.input;

import cl.huellaschile.medicalcon.application.ports.input.CreateVetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteVetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetVetUseCase;
import cl.huellaschile.medicalcon.domain.model.Vet;
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
@RequestMapping("/vet")
@RequiredArgsConstructor
public class VetRestAdapter {

    private final GetVetUseCase getVetUseCase;
    private final CreateVetUseCase createVetUseCase;
    private final DeleteVetUseCase deleteVetUseCase;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vet by id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get vet by id",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Vet.class))})
    })
    public ResponseEntity<Vet> getById(@PathVariable("id") String id) {
        final Vet veterinary = getVetUseCase.getById(id);
        veterinary.add(linkTo(methodOn(VetRestAdapter.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(VetRestAdapter.class)
                        .save(veterinary))
                        .withRel("save"),
                linkTo(methodOn(VetRestAdapter.class)
                        .deleteById(id))
                        .withRel("deleteById"));
        return ResponseEntity.ok(veterinary);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save veterinary")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Vet created",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Vet.class))})
    })
    public ResponseEntity<Vet> save(@Valid @RequestBody Vet vet) {
        final Vet vetCreated = createVetUseCase.save(vet);
        vet.add(linkTo(methodOn(VetRestAdapter.class)
                .getById(vetCreated.getNationalRegister()))
                .withSelfRel());
        return new ResponseEntity<>(vet, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete vet")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Vet deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {
        deleteVetUseCase.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
