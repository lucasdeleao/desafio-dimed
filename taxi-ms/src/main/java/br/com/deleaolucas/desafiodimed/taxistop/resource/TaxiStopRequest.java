package br.com.deleaolucas.desafiodimed.taxistop.resource;

import br.com.deleaolucas.desafiodimed.taxistop.model.TaxiStopModel;

public class TaxiStopRequest {

    private String stop;
    private float latitude;
    private float longitude;

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public TaxiStopModel buildModel() {
        return new TaxiStopModel(stop, latitude, longitude);
    }

}
