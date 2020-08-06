package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.service.BusLineApiService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stpoa-buslines")
public class BusLineApiResource {

    private BusLineApiService busLineApiService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    public BusLineApiResource(BusLineApiService busLineApiService) {
        this.busLineApiService = busLineApiService;
    }

    @GetMapping(value="/{name}", produces = JSON)
    public ResponseEntity<List<BusLineApiResponse>> sarch(@PathVariable(value = "name") final String name) throws JSONException {
        final List<BusLineApiResponse> response = busLineApiService.search(name);
        if(response.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
