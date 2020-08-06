package br.com.deleaolucas.desafiodimed.linhaonibus.repository;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusItinerary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BusItineraryRepository extends ReactiveMongoRepository<BusItinerary, String> {

}
