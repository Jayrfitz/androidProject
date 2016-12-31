package edu.csumb.jfitzgerald.airplanereserationsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jasonfitzgerald on 5/13/16.
 */
public class CancelLogin extends ActionBarActivity implements View.OnClickListener{
    private static final String TAG = "cancelLoginLog";
    Button bLogin;
    Button bBack;
    EditText etUsername, etPassword;
    int trys= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincancel);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
    }
    private void close()
    {
        this.finish();
    }


    @Override
    public void onClick(View v)
    {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        User user = new User(username, password);
        switch(v.getId()){
            case R.id.bLogin:
                trys++;

                if(trys>1)
                {

                    this.finish();
                }


                Boolean exists = false;
                for(User c : MainActivity.clientsArrayList)
                {
                    if(c.getUN().equalsIgnoreCase(username) && c.getPW().equalsIgnoreCase(password))
                    {
                        exists = true;
                        break;
                    }
                }
                if(!exists)
                {
                    //Toast.makeText(this, "Failure Login! No such Username", Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(CancelLogin.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Sorry, no reservation!");

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "EXIT",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which) {

                                    close();
                                    dialog.dismiss();



                                }
                            });
                    alertDialog.show();
                    break;
                }
                else
                {
                    Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();

                    Intent cancelIntent = new Intent(this, CancelReservation.class);

                    Bundle extraInto = new Bundle();
                    extraInto.putString("username", username);

                    Log.d(TAG, "Info passed in: " + username);

                    cancelIntent.putExtras(extraInto);
                    startActivity(cancelIntent);
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