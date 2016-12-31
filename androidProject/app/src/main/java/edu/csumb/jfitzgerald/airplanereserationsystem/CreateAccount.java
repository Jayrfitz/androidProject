package edu.csumb.jfitzgerald.airplanereserationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import java.io.*;
/**
 * Created by jasonfitzgerald on 5/6/16.
 */
public class CreateAccount extends ActionBarActivity implements View.OnClickListener{

    EditText etUsername;
    EditText etPassword;
    Button bBack;
    Button bRegister;
    int trys;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
        trys = 0;

    }
    private void close()
    {
        this.finish();
    }






        @Override
    public void onClick(View v)
    {
        switch (v.getId()) {


            case R.id.bRegister:

                trys++;

                if(trys > 1)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(CreateAccount.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("press Ok to go to main menu!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which) {


                                    dialog.dismiss();
                                    close();





                                }
                            });
                    alertDialog.show();


                }
                EditText iUN = (EditText) findViewById(R.id.etUsername);
                EditText iPW = (EditText) findViewById(R.id.etPassword);
                String username = iUN.getText().toString();
                String password = iPW.getText().toString();


                Boolean upper = false;
                Boolean lower = false;
                Boolean number = false;
                Boolean special = false;
                Boolean exists = false;
                Boolean userok = true;
                Boolean passok = true;

                final String checks = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$";
                Pattern pattern = Pattern.compile(checks);

                userok = pattern.matcher(username).matches();
                if (!userok)
                {
                    Toast.makeText(this, "Username wrong need special char, uppercase, lowercase, and number", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (userok)
                {
                    for(User c : MainActivity.clientsArrayList)
                    {
                        if (c.getUN().equalsIgnoreCase(username)) {
                            //Toast.makeText(this, "Username exists!", Toast.LENGTH_LONG).show();
                            //alert
                            AlertDialog alertDialog = new AlertDialog.Builder(CreateAccount.this).create();
                            alertDialog.setTitle("Error");
                            alertDialog.setMessage("Sorry, Username exsits!");
                            exists = true;
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "EXIT",
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog, int which) {
                                            trys=0;

                                            dialog.dismiss();
//                                            close();



                                        }
                                    });
                            alertDialog.show();




                        }
                    }
                }

                passok = pattern.matcher(password).matches();
                if (!passok) {
                    //Toast.makeText(this, "Username length too short. Must be 5 characters or more", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Password wrong need special char, uppercase, lowercase, and number", Toast.LENGTH_LONG).show();
                    break;

                }
                if (!exists && userok && passok) {
                    User temp = new User(username, password);
                    MainActivity.clientsArrayList.add(temp);


                    Toast.makeText(this, "Successfully created account!", Toast.LENGTH_LONG).show();
                    this.finish();
                }


            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;

            default:
                break;
        }
    }
}
