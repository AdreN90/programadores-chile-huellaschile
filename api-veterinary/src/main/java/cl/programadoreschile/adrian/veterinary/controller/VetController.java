package cl.programadoreschile.adrian.veterinary.controller;

import cl.programadoreschile.adrian.veterinary.domain.entities.VetDTO;
import cl.programadoreschile.adrian.veterinary.domain.services.VetService;
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
public class VetController {
    private final VetService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vet by Id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get vet by Id",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VetDTO.class))})
    })
    public ResponseEntity<VetDTO> getById(@PathVariable("id") long id) {
        final VetDTO vet = service.findById(id);
        vet.add(linkTo(methodOn(VetController.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(VetController.class)
                        .getByNationalRegister(vet.getNationalRegister()))
                        .withSelfRel(),
                linkTo(methodOn(VetController.class)
                        .save(vet))
                        .withRel("save"),
                linkTo(methodOn(VetController.class)
                        .deleteById(id))
                        .withRel("deleteById"));
        return ResponseEntity.ok(vet);
    }

    @GetMapping("/nationalRegister/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vet by National Register")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get veterinary by National Register",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VetDTO.class))})
    })
    public ResponseEntity<VetDTO> getByNationalRegister(@PathVariable("id") String nationalRegister) {
        final VetDTO vet = service.getByNationalRegister(nationalRegister);
        vet.add(linkTo(methodOn(VetController.class)
                        .getById(vet.getIdVet()))
                        .withSelfRel(),
                linkTo(methodOn(VetController.class)
                        .getByNationalRegister(vet.getNationalRegister()))
                        .withSelfRel(),
                linkTo(methodOn(VetController.class)
                        .save(vet))
                        .withRel("save"),
                linkTo(methodOn(VetController.class)
                        .deleteById(vet.getIdVet()))
                        .withRel("deleteById"));
        return ResponseEntity.ok(vet);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save vet")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Save vet",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VetDTO.class))})
    })
    public ResponseEntity<VetDTO> save(@Valid @RequestBody VetDTO veterinary) {
        final VetDTO vetCreated = service.save(veterinary);
        return new ResponseEntity<>(vetCreated, HttpStatus.CREATED);
    }

    @PostMapping("/addVeterinaryToVet/{idVet}/{idVeterinary}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add veterinary to vet")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Add veterinary to vet",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VetDTO.class))})
    })
    public ResponseEntity<VetDTO> addVeterinaryToVet(@PathVariable("idVet") long idVet, @PathVariable("idVeterinary") long idVeterinary) {
        final VetDTO vet = service.addVeterinaryToVet(idVet, idVeterinary);
        return ResponseEntity.ok(vet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete By Id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Delete By Id",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        service.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
