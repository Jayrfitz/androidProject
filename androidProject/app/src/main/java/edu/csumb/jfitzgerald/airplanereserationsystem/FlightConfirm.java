package edu.csumb.jfitzgerald.airplanereserationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jasonfitzgerald on 5/12/16.
 */
public class FlightConfirm extends ActionBarActivity implements View.OnClickListener{
    private static final String TAG = "flightConfirmLog";
    Button bConfirm, bBack;
    String username = "";
    String flightNum = "";
    String departure = "";
    String arrival = "";
    int seats = 0;



    int seatsAll = 0;
    String dTime = "";
    double price = 0;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flightconfirm);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);

        bConfirm = (Button) findViewById(R.id.bConfirm);
        bConfirm.setOnClickListener(this);



        Bundle passInfo = getIntent().getExtras();

        username = passInfo.getString("username");
        flightNum = passInfo.getString("flightNum");
        departure = passInfo.getString("departure");
        arrival = passInfo.getString("arrival");
        seats = passInfo.getInt("seats");







        LinearLayout layouts = (LinearLayout)findViewById(R.id.user_display);
        int row = 0;
        for(User u    : MainActivity.clientsArrayList)
        {
            row++;
        }
        final TextView[] myTextView = new TextView[row];
        int users = 0;
        for(User u    : MainActivity.clientsArrayList)
        {
            if(username.equals(u.getUN()))
            {
                final TextView rowTextViews = new TextView(this);
                rowTextViews.setText(u.toString());
                layouts.addView(rowTextViews);
                users++;
                break;

            }

        }

        LinearLayout layout = (LinearLayout)findViewById(R.id.flights_display);
        int rows = 0;
        final TextView[] myTextViews = new TextView[rows];
        int flights = 0;

        for(Flights f : MainActivity.flightArrayList) {
            rows++;
        }



        for(Flights f : MainActivity.flightArrayList)
        {
            if(departure.equals(f.getDeparture()) && arrival.equals(f.getArrival()) && seats <= f.getSeats())
            {

                dTime = f.getdTime();
                price = f.getPrice();


                final TextView rowTextView = new TextView(this);
                rowTextView.setText(f.toString());
                layout.addView(rowTextView);
                flights++;
                break;

            }

        }



    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bConfirm:

                Bundle passInfo = getIntent().getExtras();

                String username = passInfo.getString("username");
                String flightNum = passInfo.getString("flightNum");
                String departure = passInfo.getString("departure");
                String arrival = passInfo.getString("arrival");
                int seats = passInfo.getInt("seats");
                String type = "Booked";


                Log.d(TAG, "Info passed in: " + username + " flightNum " + flightNum + " seats: " + seats);

                Reservation temp = new Reservation( type, username, flightNum, departure, arrival, dTime, seats, price);
                MainActivity.reservationArrayList.add(temp);

                for(Flights f : MainActivity.flightArrayList)
                {
                    if(flightNum.equalsIgnoreCase(f.getFlightNum()))
                    {
                        Log.d(TAG, "Initial seats for " + f.getFlightNum() + " is : " + f.getSeats());
                        f.minus(seats);
                        Log.d(TAG, "New seats for " + f.getFlightNum() + " is : " + f.getSeats());
                        break;
                    }
                }

                //User temp = new Reservation(username, flightNum, Departure, Arrial, dTime, maxseats, );
                //MainActivity.clientsArrayList.add(temp);
                Toast.makeText(this, "added!", Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                this.finish();
                break;
            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;

            default:
                break;
        }

    }


}


