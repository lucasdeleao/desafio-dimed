package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusLine;
import br.com.deleaolucas.desafiodimed.linhaonibus.service.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bus-lines")
public class BusLineResource {

    private BusLineService busLineService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    public BusLineResource(BusLineService busLineService) {
        this.busLineService = busLineService;
    }

    @GetMapping(value = "/{name}", produces = JSON)
    public ResponseEntity<List<BusLineResponse>> search(@PathVariable(value = "name", required = true) final String name) {
        return busLineService.search(name).map(BusLineResponse::new).collectList().defaultIfEmpty(new ArrayList<>(0))
                .map(k->ResponseEntity
                        .status(HttpStatus.OK)
                        .body(k)
                ).defaultIfEmpty(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                )
                .block();
    }

    @PostMapping(value = "", consumes = JSON, produces = JSON)
    public ResponseEntity<BusLineResponse> create(@RequestBody BusLineRequest request){
        final BusLine busLine = request.create();
        final BusLineResponse response =  busLineService.create(busLine).map(BusLineResponse::new).block();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{id}", produces = JSON)
    public ResponseEntity<BusLineResponse> find(@PathVariable(value = "id") final String id){
        return busLineService.find(id)
                .map(BusLineResponse::new)
                .map(k->ResponseEntity
                        .status(HttpStatus.OK)
                        .body(k)
                ).defaultIfEmpty(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build()
        )
                .block();
    }

    @GetMapping(value = "", produces = JSON)
    public ResponseEntity<List<BusLineResponse>> findAll() {
        return busLineService.findAll().map(BusLineResponse::new).collectList().defaultIfEmpty(new ArrayList<>(0))
                .map(k->ResponseEntity
                        .status(HttpStatus.OK)
                        .body(k)
                ).defaultIfEmpty(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                )
                .block();
    }
}
