package cl.programadoreschile.adrian.veterinary.controller;

import cl.programadoreschile.adrian.veterinary.domain.services.VeterinaryService;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @PostMapping("/save")
    @ApiOperation(value = "Create veterinary")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not Found", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = String.class)})
    public ResponseEntity<VeterinaryDAO> save(@Valid @RequestBody VeterinaryDAO veterinary) {
        return new ResponseEntity<>(service.createVeterinary(veterinary), HttpStatus.CREATED);
    }
}
