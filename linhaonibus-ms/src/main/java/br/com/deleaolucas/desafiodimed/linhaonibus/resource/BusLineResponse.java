package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusLine;

public class BusLineResponse {

    private String id;
    private String name;
    private String code;

    public BusLineResponse(final BusLine busLine){
        id   = busLine.getId();
        name = busLine.getName();
        code = busLine.getCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
