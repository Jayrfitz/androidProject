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
public class LoginAdmin extends ActionBarActivity implements View.OnClickListener{

    Button bLogin;
    Button bBack;
    int trys= 0;

    EditText etUsername, etPassword;
    //TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
//        tvRegisterLink= (TextView) findViewbyId(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        //tvRegisterLink.setOnClickListener(this);
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
                    break;
                }
                else if(username.equals("!admiM2")&& password.equals("!admiM2"))
                {
                    Toast.makeText(this, "Admin Logged in!", Toast.LENGTH_LONG).show();
                    Intent manageIntent = new Intent(this, ManageSystem.class);// then cancel
                    startActivity(manageIntent);
                    this.finish();
                    break;
                }
                else
                {
                    Toast.makeText(this, "You are not Admin!", Toast.LENGTH_LONG).show();
                    break;
                }

            case R.id.bBack:
                Toast.makeText(this, "back button", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }
    }
}