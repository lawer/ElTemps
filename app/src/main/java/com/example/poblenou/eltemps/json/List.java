
package com.example.poblenou.eltemps.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class List implements Parcelable {

    public static final Parcelable.Creator<List> CREATOR = new Parcelable.Creator<List>() {
        public List createFromParcel(Parcel source) {
            return new List(source);
        }

        public List[] newArray(int size) {
            return new List[size];
        }
    };
    private Long dt;
    private Temp temp;
    private Double pressure;
    private Long humidity;
    private java.util.List<Weather> weather = new ArrayList<>();
    private Double speed;
    private Long deg;
    private Long clouds;
    private Double rain;

    public List() {
    }

    protected List(Parcel in) {
        this.dt = (Long) in.readValue(Long.class.getClassLoader());
        this.temp = in.readParcelable(Temp.class.getClassLoader());
        this.pressure = (Double) in.readValue(Double.class.getClassLoader());
        this.humidity = (Long) in.readValue(Long.class.getClassLoader());
        this.weather = new ArrayList<>();
        in.readList(this.weather, List.class.getClassLoader());
        this.speed = (Double) in.readValue(Double.class.getClassLoader());
        this.deg = (Long) in.readValue(Long.class.getClassLoader());
        this.clouds = (Long) in.readValue(Long.class.getClassLoader());
        this.rain = (Double) in.readValue(Double.class.getClassLoader());
    }

    /**
     * @return The dt
     */
    public Long getDt() {
        return dt;
    }

    /**
     * @param dt The dt
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     * @return The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * @param temp The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * @return The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The humidity
     */
    public Long getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * @param weather The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * @return The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Long getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(Long deg) {
        this.deg = deg;
    }

    /**
     * @return The clouds
     */
    public Long getClouds() {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    public void setClouds(Long clouds) {
        this.clouds = clouds;
    }

    /**
     * @return The rain
     */
    public Double getRain() {
        return rain;
    }

    /**
     * @param rain The rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.dt);
        dest.writeParcelable(this.temp, flags);
        dest.writeValue(this.pressure);
        dest.writeValue(this.humidity);
        dest.writeList(this.weather);
        dest.writeValue(this.speed);
        dest.writeValue(this.deg);
        dest.writeValue(this.clouds);
        dest.writeValue(this.rain);
    }

    public long getRoundedMinTemp() {
        return roundedTemp(this.getTemp().getMin());
    }

    public long getRoundedMaxTemp() {
        return roundedTemp(this.getTemp().getMax());
    }

    public long roundedTemp(Double temp) {
        return Math.round(temp);
    }

    public String forecastString() {
        String dateString = getDateString();

        String description = this.getWeather().get(0).getDescription();

        Long min = getRoundedMinTemp();
        Long max = getRoundedMaxTemp();

        return String.format("%s - %s - %s/%s",
                dateString, description, min, max
        );

    }

    @NonNull
    public String getDateString() {
        Long dt = this.getDt();
        java.util.Date date = new java.util.Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        return dateFormat.format(date);
    }
}
