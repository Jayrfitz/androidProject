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
 * Created by jasonfitzgerald on 5/6/16.
 */
public class CancelReservation extends ActionBarActivity implements View.OnClickListener{
    private static final String TAG = "cancelReservationLog";
    Button bBack;
    Button bCancel;
    String username = "";
    int seats = 0;
    String flightNum;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);

        Bundle extraInto = getIntent().getExtras();
        username = extraInto.getString("username");
        Log.d(TAG, "Info passed in: " + username);
        LinearLayout layout = (LinearLayout)findViewById(R.id.reservation_display);
        int rower = 0;
        for(Reservation R : MainActivity.reservationArrayList){
            rower++;
        }
        final TextView[] myTextView = new TextView[rower];
        int users = 0;

        for(Reservation R : MainActivity.reservationArrayList) {
            seats = R.getSeats();
            flightNum = R.getflightNum();
            Log.d(TAG, "In this loop : " + R.getUser());
            if(username.equals(R.getUser())){
                Log.d(TAG, "Info found in loop: " + username + " against " + R.getUser());
                final TextView rowTextView = new TextView(this);
                rowTextView.setText(R.toString());
                layout.addView(rowTextView);
                users++;
            }
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.bCancel:
                Bundle extraInto = getIntent().getExtras();
                username = extraInto.getString("username");
                String type= "cancelled";
                String flightNum  = "";
                String departure = "";
                String arrival = "";
                String dTime = "";
                double price = 0;
                for(Reservation R : MainActivity.reservationArrayList) {
                    flightNum = R.getflightNum();
                    departure = R.getDeparture();
                    arrival = R.arrival();
                    dTime = R.dTime();
                    seats = R.getSeats();

                    Log.d(TAG, "In this loop : " + R.getUser());
                    if (username.equals(R.getUser())) {
//                        R.changeToCancel(type);
                        Reservation temp = new Reservation(type, username, flightNum, departure, arrival, dTime, seats, price);
                        MainActivity.reservationArrayList.add(temp);
                        break;
                    }
                }
                for(Flights f : MainActivity.flightArrayList) {
                    if(flightNum.equalsIgnoreCase(f.getFlightNum())){
                        Toast.makeText(this, "Fight cancelled", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Initial seats for " + f.getFlightNum() + " is : " + f.getSeats());
                        MainActivity.flightArrayList.remove(f);
                        f.freeUpSeat(seats);
                        Log.d(TAG, "New seats for " + f.getFlightNum() + " is : " + f.getSeats());
                        break;
                    }
                }
                this.finish();
                break;
            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }
    }
}
