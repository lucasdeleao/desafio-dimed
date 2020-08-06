package br.com.deleaolucas.desafiodimed.linhaonibus.service;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusLine;
import br.com.deleaolucas.desafiodimed.linhaonibus.repository.BusLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusLineService {

    private BusLineRepository busLineRepository;

    @Autowired
    public BusLineService(final BusLineRepository busLineRepository) {
        this.busLineRepository = busLineRepository;
    }

    public Flux<BusLine> findAll(){
         return this.busLineRepository.findAll();
    }

    public Mono<BusLine> find(final String id){
        return busLineRepository.findById(id);
    }

    public Mono<Void> delete(final String id){
        return this.busLineRepository.deleteById(id);
    }

    public Flux<BusLine> create(final Flux<BusLine> flux){
        return this.busLineRepository.saveAll(flux);
    }

    public Mono<BusLine> create(final BusLine busLine){
        return busLineRepository.save(busLine);
    }

    public Flux<BusLine> search(final String name){
        return busLineRepository.findByName(name);
    }

}

