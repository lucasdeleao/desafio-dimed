package br.com.deleaolucas.desafiodimed.taxistop.service;

import br.com.deleaolucas.desafiodimed.taxistop.model.TaxiStopModel;
import br.com.deleaolucas.desafiodimed.taxistop.util.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiStopService {

    private final FileProcessor fileProcessor;

    @Value("${filepath}")
    private String filepath;

    @Autowired
    public TaxiStopService(final FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public boolean save(final TaxiStopModel model) {

        final Optional<TaxiStopModel> actual = get(model.getStop());
        if (actual.isPresent()) {
            return false;
        }

        final String string = model.serialize();
        fileProcessor.appendToFile(filepath, string);
        return true;
    }

    public List<TaxiStopModel> get(final int size, final int page) {

        final int initLine = (page - 1) * size;
        final int lastLine = initLine + size;

        final String content = fileProcessor.fileReader(filepath, initLine, lastLine);
        final String[] lines = content.split("\n");

        final List<TaxiStopModel> response = new ArrayList<TaxiStopModel>(lines.length);

        Arrays.stream(lines).forEach(k->response.add(TaxiStopModel.deserialize(k)));

        return response;
    }

    public Optional<TaxiStopModel> get(final String taxiStop) {
        final List<String> lines = fileProcessor.getLinesThatHasString(filepath, taxiStop);
        return lines
                .stream()
                .map(TaxiStopModel::deserialize)
                .filter(taxi -> taxi.getStop().equals(taxiStop))
                .findFirst();
    }

}
