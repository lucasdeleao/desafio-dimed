package br.com.deleaolucas.desafiodimed.linhaonibus.service;

import br.com.deleaolucas.desafiodimed.linhaonibus.resource.BusLineApiResponse;
import br.com.deleaolucas.desafiodimed.linhaonibus.repository.BusLineRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLineApiService {

    @Value("${BUSLINE_API_URL:${busline.api.url}}")
    private String BUSLINE_API_URL;

    private BusLineRepository busLineRepository;
    private Gson gson;
    private RestTemplate restTemplate;

    @Autowired
    public BusLineApiService(final BusLineRepository busLineRepository, final Gson gson, final RestTemplate restTemplate) {
        this.busLineRepository = busLineRepository;
        this.gson = gson;
        this.restTemplate = restTemplate;
    }

    public List<BusLineApiResponse> search(final String name) throws JSONException {
        String response = restTemplate.getForObject(BUSLINE_API_URL, String.class);
        JSONArray jsonArray = new JSONArray(response);
        Type type = new TypeToken<List<BusLineApiResponse>>(){}.getType();
        List<BusLineApiResponse> lines = gson.fromJson(String.valueOf(jsonArray), type);

        return findLines(lines, name);
    }

    public List<BusLineApiResponse> findLines(final List<BusLineApiResponse> lines, final String name) {
        return lines.stream().filter(k->k.getNome().equals(name)).collect(Collectors.toList());
    }
}
