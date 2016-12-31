package edu.csumb.jfitzgerald.airplanereserationsystem;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
/**
 * Created by jasonfitzgerald on 5/10/16.
 */
public class Login extends ActionBarActivity implements View.OnClickListener{

    Button bLogin;
    Button bBack;
    EditText etUsername, etPassword;
    int trys= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.bLogin:
                trys++;

                if(trys>1)
                {
                    this.finish();
                }
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = new User(username, password);

                Boolean exists = false;
                for(User c : MainActivity.clientsArrayList){
                    if(c.getUN().equalsIgnoreCase(username) && c.getPW().equalsIgnoreCase(password)){
                        exists = true;
                        break;
                    }
                }
                if(!exists)
                {
                    Toast.makeText(this, "Failure Login!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();
                    String flightNum = "";
                    String departure = "";
                    String arrival = "";
                    int seats = 0;


                    Bundle extras = getIntent().getExtras();
                    if(extras != null) {
                        flightNum = extras.getString("flightNum");
                        departure = extras.getString("departure");
                        arrival = extras.getString("arrival");
                        seats = extras.getInt("seats");
                    }


                        Intent confirmIntent = new Intent(this, FlightConfirm.class);

                        Bundle passInfo = new Bundle();
                        passInfo.putString("username", username);
                        passInfo.putString("flightNum", flightNum);
                        passInfo.putString("departure", departure);
                        passInfo.putString("arrival", arrival);
                        passInfo.putInt("seats", seats);

                        confirmIntent.putExtras(passInfo);
                        startActivity(confirmIntent);
                        this.finish();





                }
                break;
            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }
    }



}