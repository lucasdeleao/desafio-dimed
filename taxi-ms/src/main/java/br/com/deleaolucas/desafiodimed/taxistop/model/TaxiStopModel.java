package br.com.deleaolucas.desafiodimed.taxistop.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaxiStopModel {

    private final String stop;
    private final float latitude;
    private final float longitude;
    private final LocalDateTime insertDate;

    public TaxiStopModel(final String stop, final float latitude, final float longitude, final LocalDateTime insertDate) {
        this.stop = stop;
        this.latitude = latitude;
        this.longitude = longitude;
        this.insertDate = insertDate;
    }

    public TaxiStopModel(final String stop, final float latitude, final float longitude) {
        this(stop, latitude, longitude, LocalDateTime.now());
    }

    public String getStop() {
        return stop;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public String serialize() {
        return String.format("%s#%f#%f#%s", stop, latitude, longitude, insertDate.toString());
    }

    public static TaxiStopModel deserialize(final String string) {
        final String content[] = string.split("\\#");
        final String stop = content[0];
        final float latitude = Float.parseFloat(content[1].replace(",", "."));
        final float longitude = Float.parseFloat(content[2].replace(",", "."));
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        final String dateTime = content[3].replace("T", " ").substring(0, 23);
        final LocalDateTime insertDate = LocalDateTime.parse(dateTime, formatter);
        return new TaxiStopModel(stop, latitude, longitude, insertDate);
    }
}
