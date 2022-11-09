package cl.huellaschile.medicalcon.infrastructure.adapters.input;

import cl.huellaschile.medicalcon.application.ports.input.CreateConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.UpdateConsultationUseCase;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationPaymentRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationGetResponse;
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
@RequestMapping("/consultation")
@RequiredArgsConstructor
public class ConsultationRestAdapter {

    private final GetConsultationUseCase consultationUseCase;
    private final CreateConsultationUseCase createConsultationUseCase;
    private final UpdateConsultationUseCase updateConsultationUseCase;
    private final DeleteConsultationUseCase deleteConsultationUseCase;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get consultation by id")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Get consultation by id",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsultationGetResponse.class))})
    })
    public ResponseEntity<ConsultationGetResponse> getById(@PathVariable("id") String id) {
        final ConsultationGetResponse consultation = consultationUseCase.findById(id);
        consultation.add(linkTo(methodOn(ConsultationRestAdapter.class)
                        .getById(id))
                        .withSelfRel(),
                linkTo(methodOn(ConsultationRestAdapter.class)
                        .setStatusClose(id))
                        .withSelfRel(),
                linkTo(methodOn(ConsultationRestAdapter.class)
                        .setStatusTreatment(id))
                        .withSelfRel(),
                linkTo(methodOn(ConsultationRestAdapter.class)
                        .delete(id))
                        .withRel("delete"));
        return ResponseEntity.ok(consultation);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save consultation")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "201",
            description = "Consultation created",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsultationCreateResponse.class))})
    })
    public ResponseEntity<ConsultationCreateResponse> save(@Valid @RequestBody ConsultationCreateRequest consultation) {
        final ConsultationCreateResponse consultationCreated = createConsultationUseCase.save(consultation);
        return new ResponseEntity<>(consultationCreated, HttpStatus.CREATED);
    }

    @PutMapping("/setStatusClose{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Set status close to consultation")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Set status close to consultation",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsultationCreateResponse.class))})
    })
    public ResponseEntity<ConsultationCreateResponse> setStatusClose(@PathVariable("id") String id) {
        final ConsultationCreateResponse consultation = updateConsultationUseCase.closeMedicalConsultation(id);
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

    @PutMapping("/setStatusTreatment{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Set status treatment to consultation")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Set status treatment to consultation",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsultationCreateResponse.class))})
    })
    public ResponseEntity<ConsultationCreateResponse> setStatusTreatment(@PathVariable("id") String id) {
        final ConsultationCreateResponse consultation = updateConsultationUseCase.consultationInTreatment(id);
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

    @PutMapping("/payment{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Payment consultation")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Payment consultation",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ConsultationCreateResponse.class))})
    })
    public ResponseEntity<ConsultationCreateResponse> payment(@Valid @RequestBody ConsultationPaymentRequest request, @PathVariable("id") String id) {
        final ConsultationCreateResponse consultation = updateConsultationUseCase.payment(id, request);
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Consultation deleted",
            content = {@Content(mediaType = "String", schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        deleteConsultationUseCase.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
