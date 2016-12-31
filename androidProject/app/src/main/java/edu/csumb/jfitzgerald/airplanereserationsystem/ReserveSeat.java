package edu.csumb.jfitzgerald.airplanereserationsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.RadioButton;
import android.content.Intent;
import java.util.ArrayList;

/**
 * Created by jasonfitzgerald on 5/6/16.
 */
public class ReserveSeat extends ActionBarActivity implements View.OnClickListener{


    Button bReserve;
    Button bSearch;
    Button bBack;

    int seats;

    String arrival = "";
    String departure = "";
    EditText etSeats;
    EditText etFlightNum;
    String flightNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);



        bReserve = (Button) findViewById(R.id.bReserve);
        bReserve.setOnClickListener(this);

        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);

        etFlightNum = (EditText) findViewById(R.id.etFlightNum);



    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.LADeparture:
                if (checked) {
                    departure = "Los Angeles";
                }
                    break;
            case R.id.mDeparture:
                if (checked) {
                    departure = "Monterey";
                }
                    break;
            case R.id.sDeparture:
                if (checked) {
                    departure = "Seattle";
                }
                    break;
            case R.id.LAArrial:
                if (checked) {
                    arrival = "Los Angeles";
                }
                break;
            case R.id.mArrival:
                if (checked) {
                    arrival = "Monterey";
                }
                break;
            case R.id.sArrival:
                if (checked) {
                    arrival = "Seattle";
                }
                break;
            default:
                Toast.makeText(this, "Nothing Clicked!", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bSearch:

                //final Intent mainIntent = new Intent(this, MainActivity.class);

                etSeats = (EditText) findViewById(R.id.etSeats);
                seats = Integer.parseInt(etSeats.getText().toString());
                etSeats.setOnClickListener(this);
                if(seats == 0)
                {
                    Toast.makeText(this, "No seats chosen", Toast.LENGTH_LONG).show();
                    break;
                }



//  changed                etFlightNum = (EditText) findViewById(R.id.etFlightNum);
//  changed              etFlightNum.setOnClickListener(this);
                flightNum = etFlightNum.getText().toString();


                LinearLayout layout = (LinearLayout)findViewById(R.id.flights_display);

                int rows = 0;
                Boolean excites = false;

                for(Flights f : MainActivity.flightArrayList) {
                    rows++;
                }

                final TextView[] myTextViews = new TextView[rows];
                int flights = 0;

                for(Flights f : MainActivity.flightArrayList)
                {

                    int count = 0;
                    if(departure.equals(f.getDeparture()) && arrival.equals(f.getArrival()))
                    {
                        count++;
                    }


                    if(departure.equals(f.getDeparture()) && arrival.equals(f.getArrival()) && seats < f.getSeats() && seats > 0)
                    {



                        if(count < 2) {
                            Toast.makeText(this, "Flight Exists!", Toast.LENGTH_LONG).show();

                            final TextView rowTextView = new TextView(this);
                            rowTextView.setText(f.toString());
                            layout.addView(rowTextView);
                            flights++;
                            excites = true;
                            break;
                        }


                    }


                }

                if(!excites)
                {
                    //Toast.makeText(this, "No Flight Exists or you want to many seats", Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(ReserveSeat.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Sorry, No flight exsits!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "EXIT",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which) {

                                    
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                }
                break;

            case R.id.bReserve:

                etFlightNum = (EditText) findViewById(R.id.etFlightNum);
                //etFlightNum.setOnClickListener(this);
                flightNum = etFlightNum.getText().toString();
                Intent loginIntent = new Intent(this, Login.class);// then confirm
                Bundle extras = new Bundle();
                extras.putString("flightNum",flightNum);
                extras.putString("departure", departure);
                extras.putString("arrival",arrival);
                extras.putInt("seats",seats);

                loginIntent.putExtras(extras);
                startActivity(loginIntent);
                this.finish();
                break;

            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;

            default:
                //for the blue check
                if(etFlightNum.equals("")) {
                    Toast.makeText(this, "so random shit", Toast.LENGTH_LONG).show();
                    etFlightNum = (EditText) findViewById(R.id.etFlightNum);
                    //etFlightNum.setOnClickListener(this);
                    flightNum = etFlightNum.getText().toString();
                    loginIntent = new Intent(this, Login.class);// then confirm
                    extras = new Bundle();
                    extras.putString("flightNum", flightNum);
                    extras.putString("departure", departure);
                    extras.putString("arrival", arrival);
                    extras.putInt("seats", seats);

                    loginIntent.putExtras(extras);
                    startActivity(loginIntent);
                }
//                this.finish();
                break;
        }

    }
}