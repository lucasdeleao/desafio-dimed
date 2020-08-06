package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import java.math.BigDecimal;

public class BusItineraryRequest {

    private Long busLineId;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Long getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(Long busLineId) {
        this.busLineId = busLineId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude.setScale(12);
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude.setScale(12);
    }


}