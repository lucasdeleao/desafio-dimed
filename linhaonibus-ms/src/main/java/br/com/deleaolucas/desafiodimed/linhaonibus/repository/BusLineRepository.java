package br.com.deleaolucas.desafiodimed.linhaonibus.repository;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusLine;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BusLineRepository extends ReactiveMongoRepository<BusLine, String> {
    @Query("{ 'name': ?0}")
    Flux<BusLine> findByName(String name);
}
