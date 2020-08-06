package br.com.deleaolucas.desafiodimed.linhaonibus.resource;

import br.com.deleaolucas.desafiodimed.linhaonibus.model.BusItinerary;

import java.math.BigDecimal;

public class BusItineraryResponse {

        private String busRouteId;
        private Long busLineId;
        private BigDecimal latitude;
        private BigDecimal longitude;

        public BusItineraryResponse (final BusItinerary busItinerary){
            busRouteId = busItinerary.getId();
            busLineId = busItinerary.getBusLineId();
            latitude = busItinerary.getLatitude();
            longitude = busItinerary.getLongitude();
        }

        public String getBusRouteId() {
            return busRouteId;
        }

        public void setBusRouteId(String busRouteId) {
            this.busRouteId = busRouteId;
        }

        public BigDecimal getLatitude() {
            return latitude;
        }

        public void setLatitude(BigDecimal latitude) {
            this.latitude = latitude;
        }

        public BigDecimal getLongitude() {
            return longitude;
        }

        public void setLongitude(BigDecimal longitude) {
            this.longitude = longitude;
        }

        public Long getBusLineId() {
            return busLineId;
        }

        public void setBusLineId(Long busLineId) {
            this.busLineId = busLineId;
    }

}

