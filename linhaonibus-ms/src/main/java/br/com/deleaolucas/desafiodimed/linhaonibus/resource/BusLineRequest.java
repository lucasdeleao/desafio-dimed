package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusLine;

public class BusLineRequest {

    private String name;
    private String code;

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

    public BusLine create() {
        final BusLine entity = new BusLine();
        entity.setName(name);
        entity.setCode(code);
        entity.setId(null);

        return entity;
    }
}
