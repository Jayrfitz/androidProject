package edu.csumb.jfitzgerald.airplanereserationsystem;

/**
 * Created by jasonfitzgerald on 5/13/16.
 */
public class Log
{
    int ID;
    String username;
    String Flightname, Departure, Arrival, Time;
    int Seats;
    double Price;

    public Log(int ID, String username, String Flightname, String Departure, String Arrival, String Time, int Seats, double Price) {
        this.ID = ID;
        this.username = username;
        this.Flightname = Flightname;
        this.Departure = Departure;
        this.Arrival = Arrival;
        this.Time = Time;
        this.Seats = Seats;
        this.Price = Price;
    }

    public String toString() {
        String message = ("Ticket ID: " + ID + "\n"
                + "Username: " + username + "\n"
                + Flightname + ": "
                + " Departure: " + Departure
                + " Arrival: " + Arrival
                + " Time: " + Time
                + " Seats: " + Seats
                + " Price: $" + Price + "\n");
        return message;
    }

    public int getID(){ return this.ID; }
    public String getUN(){ return this.username; }
    public String getFL(){ return this.Flightname; }
    public String getDp(){ return this.Departure; }
    public String getAr(){ return this.Arrival; }
    public String getTime(){ return this.Time; }
    public int getSt(){ return this.Seats; }
    public double getPr(){ return this.Price; }


}
