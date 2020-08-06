package br.com.deleaolucas.desafiodimed.linhaonibus.service;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusItinerary;
import br.com.deleaolucas.desafiodimed.linhaonibus.repository.BusItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BusItineraryService {
    private BusItineraryRepository repository;

    @Autowired
    public BusItineraryService(final BusItineraryRepository repository){
        this.repository = repository;
    }

    public Flux<BusItinerary> findAll(){
        return repository.findAll();
    }
}
