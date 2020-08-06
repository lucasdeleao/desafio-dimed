package br.com.deleaolucas.desafiodimed.taxistop.resource;

import br.com.deleaolucas.desafiodimed.taxistop.model.TaxiStopModel;
import br.com.deleaolucas.desafiodimed.taxistop.service.TaxiStopService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/taxi-stops")
public class TaxiStopResource {

    private final TaxiStopService taxiStopService;

    private final static String JSON = "application/json";

    @Autowired
    public TaxiStopResource(final TaxiStopService taxiStopService) {
        this.taxiStopService = taxiStopService;
    }

    @ApiOperation(value = "Creates a new taxi stop", response = TaxiStopResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created."),
            @ApiResponse(code = 409, message = "There is already a taxi stop with the same name."),
            @ApiResponse(code = 500, message = "Internal server error.")
    })
    @PostMapping(consumes = JSON, produces = JSON)
    public ResponseEntity<EntityModel<TaxiStopResponse>> create(final @RequestBody TaxiStopRequest request) {

        final TaxiStopModel model = request.buildModel();
        final boolean save = taxiStopService.save(model);

        if (!save) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        final TaxiStopResponse response = new TaxiStopResponse(model);

        final Link self = linkTo(methodOn(TaxiStopResource.class).find(response.getStop())).withSelfRel().withType("GET");
        final Link taxiStops = linkTo(methodOn(TaxiStopResource.class).find(1, 10)).withRel("find_all").withType("GET");

        return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(response, self, taxiStops));

    }

    @ApiOperation(value = "Returns the taxi stops that are stored in database.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "There is no data.")
    })
    @GetMapping(produces = JSON)
    public ResponseEntity<CollectionModel<TaxiStopResponse>> find(
            @RequestParam(value = "page", defaultValue = "1") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size) {

        final List<TaxiStopModel> result = taxiStopService.get(size, page);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        final List<TaxiStopResponse> response = result
                .stream()
                .map(TaxiStopResponse::new)
                .collect(Collectors.toList());

        final Link self = linkTo(methodOn(TaxiStopResource.class).find(page, size)).withSelfRel().withType("GET");

        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(response, self));

    }

    @ApiOperation(value = "Returns the complete data of a taxi stop.", response = TaxiStopResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Taxi stop was found.")
    })
    @GetMapping(value = "/{stopName}", produces = JSON)
    public ResponseEntity<EntityModel<TaxiStopResponse>> find(@PathVariable("stopName") String stopName) {
        final Optional<TaxiStopModel> model = taxiStopService.get(stopName);
        if (!model.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        final Link self = linkTo(methodOn(TaxiStopResource.class).find(stopName)).withSelfRel().withType("GET");

        final Link taxiStops = linkTo(methodOn(TaxiStopResource.class).find(1, 10)).withRel("find_all").withType("GET");

        final TaxiStopResponse response = model.map(TaxiStopResponse::new).get();

        return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(response, self, taxiStops));

    }
}
