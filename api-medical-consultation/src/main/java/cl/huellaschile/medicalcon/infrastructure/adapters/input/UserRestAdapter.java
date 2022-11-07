package cl.huellaschile.medicalcon.infrastructure.adapters.input;

import cl.huellaschile.medicalcon.application.ports.input.CreateUserUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteUserUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetUserUseCase;
import cl.huellaschile.medicalcon.domain.model.User;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestAdapter {

    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get user by id",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    })
    public ResponseEntity<User> getById(@PathVariable("id") String id) {
        final User user = getUserUseCase.getById(id);
        user.add(linkTo(methodOn(UserRestAdapter.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(UserRestAdapter.class)
                        .create(user))
                        .withRel("create"),
                linkTo(methodOn(UserRestAdapter.class)
                        .delete(id))
                        .withRel("delete"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "User created",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    })
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        final User userCreated = createUserUseCase.save(user);
        user.add(linkTo(methodOn(UserRestAdapter.class)
                .getById(userCreated.getIdUser()))
                .withSelfRel());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "User deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        deleteUserUseCase.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
