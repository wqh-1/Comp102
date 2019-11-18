// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 9
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.text.*;

/**
 * AirPollution contains information about the pollution level and weather in a
 * city at a particular hour and date.
 */

public class AirPollution {

    private String city;
    private String date;
    private int hour;
    private double pm;       // Particulate matter
    private double dewp;     // Dew Point
    private double humidity;
    private double pressure;
    private double temp;     // temperature
    private String cbwd;     // Combined wind direction
    private double cws;      // wind speed
    private double prec;     // hourly precipitation
    private double cprec;    // Accumulated precipitation

    /** Construct a new AirPollution object */
    public AirPollution(String city, String date, int hour, double pm,
                        double dewp, double humidity, double pressure,
                        double temp, String cbwd, double cws,
                        double prec, double cprec) {
        this.city = city;
        this.date = date;
        this.hour = hour;
        this.pm = pm;
        this.dewp = dewp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.cbwd = cbwd;
        this.cws = cws;
        this.prec = prec;
        this.cprec = cprec;
    }

    /**
     * PM concentration difference between two pollution records
     */
    public double diffPM(AirPollution other) {
        return (Math.abs(other.getPM() - this.getPM()));
    }
    
    /** Get the city */
    public String getCity() {
        return this.city;
    }

    /** Get the date */
    public String getDate() {
        return this.date;
    }

    /** Get the hour */
    public int getTime() {
        return this.hour;
    }

    /** Get the PM concentration */
    public double getPM() {
        return this.pm;
    }

    /** Get the dew point temperature */
    public double getDewPoint() {
        return this.dewp;
    }

    /** Get the temperature */
    public double getTemperature() {
        return this.temp;
    }

    /** Get the humidity */
    public double getHumidity() {
        return this.humidity;
    }

    /** Get the pressure */
    public double getPressure() {
        return this.pressure;
    }

    /** Get the wind direction */
    public String getWindDir() {
        return this.cbwd;
    }

    /** Get the wind speed */
    public double getWindSpeed() {
        return this.cws;
    }

    /** Get the precipitation */
    public double getPrecipitation() {
        return this.prec;
    }

    /** Get the accumulated precipitation */
    public double getCprec() {
        return this.cprec;
    }

    /**
     * Returns a nicely formatted String describing the pollution info.
     */
    public String toString() {
        return this.city + " at " + this.hour + " on " + this.date + " temperature: "
            + this.temp + " Humidity: " + this.humidity + " Wind Direction: " + this.cbwd + " Wind Speed: "
            + this.cws + " PM: " + this.pm;
    }

}
