package cl.huellaschile.medicalcon.infrastructure.adapters.input;

import cl.huellaschile.medicalcon.application.ports.input.CreatePetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeletePetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetPetUseCase;
import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetID;
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
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetRestAdapter {
    private final GetPetUseCase getPetUseCase;
    private final CreatePetUseCase createPetUseCase;
    private final DeletePetUseCase deletePetUseCase;

    @GetMapping("/{idUser}/{namePet}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get pet by id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get pet by id",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))})
    })
    public ResponseEntity<Pet> getById(
            @PathVariable("idUser") String idUser,
            @PathVariable("namePet") String namePet) {
        final Pet pet = getPetUseCase.getById(new PetID().setIdUser(idUser).setNamePet(namePet));
        pet.add(linkTo(methodOn(PetRestAdapter.class)
                        .getById(idUser, namePet))
                        .withSelfRel(),
                linkTo(methodOn(PetRestAdapter.class)
                        .getByIdUser(idUser))
                        .withSelfRel(),
                linkTo(methodOn(PetRestAdapter.class)
                        .getByName(namePet))
                        .withSelfRel(),
                linkTo(methodOn(PetRestAdapter.class)
                        .create(pet))
                        .withRel("create"),
                linkTo(methodOn(PetRestAdapter.class)
                        .delete(idUser, namePet))
                        .withRel("delete"));
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get pet by id user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get pet by id user",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))})
    })
    public ResponseEntity<List<Pet>> getByIdUser(@PathVariable("idUser") String idUser) {
        return new ResponseEntity<>(getPetUseCase.getByIdUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/race/{race}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get pet by race")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get pet by id race",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))})
    })
    public ResponseEntity<List<Pet>> getByRace(@PathVariable("race") String race) {
        return new ResponseEntity<>(getPetUseCase.getByRace(race), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get pet by name")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get pet by id name",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))})
    })
    public ResponseEntity<List<Pet>> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(getPetUseCase.getByName(name), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save pet")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Pet created",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))})
    })
    public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(createPetUseCase.save(pet), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idUser}/{namePet}")
    @Operation(summary = "Delete pet")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Pet deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> delete(
            @PathVariable("idUser") String idUser,
            @PathVariable("namePet") String namePet) {
        deletePetUseCase.deleteById(new PetID()
                .setIdUser(idUser)
                .setNamePet(namePet));
        return ResponseEntity.ok(true);
    }
}
