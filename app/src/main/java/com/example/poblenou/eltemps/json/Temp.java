
package com.example.poblenou.eltemps.json;


import android.os.Parcel;
import android.os.Parcelable;

public class Temp implements Parcelable {

    public static final Parcelable.Creator<Temp> CREATOR = new Parcelable.Creator<Temp>() {
        public Temp createFromParcel(Parcel source) {
            return new Temp(source);
        }

        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };
    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double eve;
    private Double morn;

    public Temp() {
    }

    protected Temp(Parcel in) {
        this.day = (Double) in.readValue(Double.class.getClassLoader());
        this.min = (Double) in.readValue(Double.class.getClassLoader());
        this.max = (Double) in.readValue(Double.class.getClassLoader());
        this.night = (Double) in.readValue(Double.class.getClassLoader());
        this.eve = (Double) in.readValue(Double.class.getClassLoader());
        this.morn = (Double) in.readValue(Double.class.getClassLoader());
    }

    /**
     * @return The day
     */
    public Double getDay() {
        return day;
    }

    /**
     * @param day The day
     */
    public void setDay(Double day) {
        this.day = day;
    }

    /**
     * @return The min
     */
    public Double getMin() {
        return min;
    }

    /**
     * @param min The min
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * @return The max
     */
    public Double getMax() {
        return max;
    }

    /**
     * @param max The max
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * @return The night
     */
    public Double getNight() {
        return night;
    }

    /**
     * @param night The night
     */
    public void setNight(Double night) {
        this.night = night;
    }

    /**
     * @return The eve
     */
    public Double getEve() {
        return eve;
    }

    /**
     * @param eve The eve
     */
    public void setEve(Double eve) {
        this.eve = eve;
    }

    /**
     * @return The morn
     */
    public Double getMorn() {
        return morn;
    }

    /**
     * @param morn The morn
     */
    public void setMorn(Double morn) {
        this.morn = morn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.day);
        dest.writeValue(this.min);
        dest.writeValue(this.max);
        dest.writeValue(this.night);
        dest.writeValue(this.eve);
        dest.writeValue(this.morn);
    }
}
