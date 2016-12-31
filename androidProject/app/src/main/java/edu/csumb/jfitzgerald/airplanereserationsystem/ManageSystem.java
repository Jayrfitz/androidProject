package edu.csumb.jfitzgerald.airplanereserationsystem;

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
 * Created by jasonfitzgerald on 5/6/16.
 */
public class ManageSystem extends ActionBarActivity implements View.OnClickListener
{
    Button bAdd;
    Button bBack;
    String  flightNum,
            departure,
            arrival,
            dTime;
    int     seats;
    int count = 1;
    double  price;
    EditText etFlightNum;
    EditText etDeparture;
    EditText etArrival;
    EditText etTime;
    EditText etSeats;
    EditText etPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        bAdd = (Button) findViewById(R.id.bAdd);
        bAdd.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);

        etFlightNum = (EditText) findViewById(R.id.etFlightNum);
        etDeparture = (EditText) findViewById(R.id.etDeparture);
        etArrival = (EditText) findViewById(R.id.etArrival);
        etTime = (EditText) findViewById(R.id.etTime);
        etSeats = (EditText) findViewById(R.id.etSeats);
        etPrice = (EditText) findViewById(R.id.etPrice);

       /* LinearLayout layouts = (LinearLayout) findViewById(R.id.user_display);

        int row = 0;
        for (User U : MainActivity.clientsArrayList) {
            row++;
        }
        final TextView[] myTextViews = new TextView[row];
        int users = 0;

        for (User U : MainActivity.clientsArrayList) {
                final TextView rowTextView = new TextView(this);
                rowTextView.setText(U.toString());
                layouts.addView(rowTextView);
                users++;
        }*/

        LinearLayout layout = (LinearLayout) findViewById(R.id.reservation_display);

        int rower = 0;
        for (Reservation R : MainActivity.reservationArrayList) {
            rower++;
        }
        final TextView[] myTextView = new TextView[rower];
        int rez = 0;

        for (Reservation R : MainActivity.reservationArrayList) {


            final TextView rowTextView = new TextView(this);
            rowTextView.setText(R.toString());
            layout.addView(rowTextView);
            rez++;




        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAdd:
                flightNum = etFlightNum.getText().toString();
                departure = etDeparture.getText().toString();
                arrival = etArrival.getText().toString();
                dTime = etTime.getText().toString();
                seats = Integer.parseInt(etSeats.getText().toString());
                price = Double.parseDouble(etPrice.getText().toString());

                for(Flights f : MainActivity.flightArrayList)
                {
                    if(f.getFlightNum().equalsIgnoreCase(flightNum))
                    {
                        Toast.makeText(this, "Flight Exists!", Toast.LENGTH_LONG).show();
                        break;
                    }
                }


                MainActivity.flightArrayList.add(new Flights(flightNum,departure,arrival,dTime,seats,price));

                Toast.makeText(this, "Flight Added!", Toast.LENGTH_LONG).show();
                this.finish();


                break;
            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }

    }
}
