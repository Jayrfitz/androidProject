package edu.csumb.jfitzgerald.airplanereserationsystem;

/**
 * Created by jasonfitzgerald on 5/10/16.
 */
public class Flights {

    String  flightNum,
            departure,
            arrival,
            dTime;
    int     seats;
    double  price;

    public Flights( String flightNum, String departure, String arrival, String dTime, int seats, double price)
    {
        this.flightNum = flightNum;
        this.departure = departure;
        this.arrival = arrival;
        this.dTime = dTime;
        this.seats = seats;
        this.price = price;
    }

    public String getFlightNum(){ return this.flightNum; }
    public String getDeparture(){ return this.departure; }
    public String getArrival(){ return this.arrival; }
    public String getdTime(){ return this.dTime; }
    public int getSeats(){ return this.seats; }
    public double getPrice(){return this.price;}


    public String toString()
    {
        String message = (  " F: " + flightNum+
                            " D: " + departure+
                            " A: " + arrival+
                            "\n S: " + seats+
                            " P: " + price+
                            " T: " + dTime);
        return message;
    }

    public void minus(int seat)
    {
        this.seats = this.seats - seat;
    }
    public void freeUpSeat(int seat)
    {
        this.seats = this.seats + seat;
    }

}
