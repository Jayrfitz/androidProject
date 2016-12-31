package edu.csumb.jfitzgerald.airplanereserationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

    Button  bCreate,
            bCancel,
            bManage,
            bReserve;


    public static ArrayList<User> clientsArrayList = new ArrayList<User>(100);
    public static ArrayList<Flights> flightArrayList = new ArrayList<Flights>(100);
    public static ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCreate = (Button) findViewById(R.id.bCreate);
        bCreate.setOnClickListener(this);

        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);

        bManage = (Button) findViewById(R.id.bManage);
        bManage.setOnClickListener(this);

        bReserve = (Button) findViewById(R.id.bReserve);
        bReserve.setOnClickListener(this);

        String  username,
                password;

        String  flightNum,
                departure,
                arrival,
                dTime;

        int maxseats;

        double price;

        clientsArrayList.add(new User("A@lice5","@cSit100"));
        clientsArrayList.add(new User("$BriAn7","123aBc##"));
        clientsArrayList.add(new User("!chriS12!","CHrIS12!!"));
        clientsArrayList.add(new User("!admiM2","!admiM2"));
        clientsArrayList.add(new User("1","1"));

        flightArrayList.add(new Flights("Otter101","Monterey","Los Angeles","10:30(AM)",10,150));
        flightArrayList.add(new Flights("Otter102","Los Angeles","Monterey","1:00(PM)",10,150));
        flightArrayList.add(new Flights("Otter201","Monterey","Seattle","11:00(AM)",5,200.5));
        flightArrayList.add(new Flights("Otter205","Monterey","Seattle","3:45(PM)",15,150));
        flightArrayList.add(new Flights("Otter202","Seattle","Monterey","2:10(AM)",5,200.5));
    }


    @Override
    public void onClick(View v) {

        Intent loginIntent = new Intent(this, Login.class);

        switch (v.getId()) {

            case R.id.bCreate:
                Intent createIntent = new Intent(this, CreateAccount.class);// then create
                startActivity(createIntent);
                break;
            case R.id.bCancel:
                Intent loginCancelIntent = new Intent(this, CancelLogin.class);// then cancel
                startActivity(loginCancelIntent);                            // then login
                break;
            case R.id.bManage:
                Intent loginAdminIntent = new Intent(this, LoginAdmin.class);// then Manage
                startActivity(loginAdminIntent);
                break;
            case R.id.bReserve:
                Intent reserveIntent = new Intent(this, ReserveSeat.class);// then reserve
                startActivity(reserveIntent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
//Intent loginIntent = new Intent(this, Login.class);// then cancel
//startActivity(loginIntent);