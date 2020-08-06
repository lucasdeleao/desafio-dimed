package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.service.BusItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/bus-itinerary")
public class BusItineraryResource{

    private static final String JSON_STREAM = MediaType.APPLICATION_STREAM_JSON_VALUE;

    private BusItineraryService service;

    @Autowired
    public BusItineraryResource(final BusItineraryService service){
            this.service = service;
    }

    @GetMapping(value = "/stream", produces = JSON_STREAM)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<BusItineraryResponse> findAll(){
        return service.findAll().map(BusItineraryResponse::new);
    }
}
