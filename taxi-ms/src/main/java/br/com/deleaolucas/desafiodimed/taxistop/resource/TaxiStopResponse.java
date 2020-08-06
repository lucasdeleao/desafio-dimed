package br.com.deleaolucas.desafiodimed.taxistop.resource;

import br.com.deleaolucas.desafiodimed.taxistop.model.TaxiStopModel;

import java.time.LocalDateTime;

public class TaxiStopResponse {

    private String stop;

    private float latitude;

    private float longitude;

    private LocalDateTime insertDate;

    public TaxiStopResponse(){}

    public TaxiStopResponse(final TaxiStopModel model){
        stop       = model.getStop();
        latitude   = model.getLatitude();
        longitude  = model.getLongitude();
        insertDate = model.getInsertDate();
    }


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

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

}
