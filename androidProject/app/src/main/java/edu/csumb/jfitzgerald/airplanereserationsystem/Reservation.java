package edu.csumb.jfitzgerald.airplanereserationsystem;

/**
 * Created by jasonfitzgerald on 5/11/16.
 */
public class Reservation {

    String  username,
            flightNum,
            departure,
            arrival,
            dTime;
    int     seats;
    String     type;
    double  price;

    public Reservation( String type ,String Username, String flightNum, String departure, String arrival, String dTime, int seats, double price)
    {
        this.type = type;
        this.username = Username;
        this.flightNum = flightNum;
        this.departure = departure;
        this.arrival = arrival;
        this.dTime = dTime;
        this.seats = seats;
        this.price = price;
    }
    public String getType() { return this.type; }
    public String getUser() { return this.username; }
    public String getflightNum(){ return this.flightNum; }
    public String getDeparture(){ return this.departure; }
    public String arrival(){ return this.arrival; }
    public String dTime(){ return this.dTime; }
    public int getSeats(){ return this.seats; }
    public double getPrice(){return this.price;}

    public String toString()
    {
        {
            String messager = (
                    "Type " + type + "\n"+
                    "Username " + username + "\n"+
                    "Flight Number " + flightNum + "\n"+
                    "Departure " + departure+ "\n"+
                    "Arrival " + arrival + "\n"+
                    "Time " + dTime + "\n"+
                    "Seats " + seats + "\n"+
                    "Price " + price);
            return messager;
        }
    }

    public void changeToCancel(String type)
    {
        this.type = type;
    }

}
