package in.vaishnavi.softcell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vaishnavi on 29/12/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mEdtUsername,mEdtPassword;
    private Button mBtnSignIn;
    private TextView mtxtSignUp;
    private DBHelper mDBHelper;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDBHelper=new DBHelper(LoginActivity.this,"UserDatabase.db",null,1);

        initViews();

    }

    private void initViews() {

        mEdtUsername= (EditText) findViewById(R.id.edtUsername);
        mEdtPassword= (EditText) findViewById(R.id.edtPassword);
        mBtnSignIn= (Button) findViewById(R.id.buttonSignIn);
        mtxtSignUp= (TextView) findViewById(R.id.textViewSignUp);

        mtxtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }



    private void login() {

        String username=mEdtUsername.getText().toString();
        String password=mEdtPassword.getText().toString();


        if (username.isEmpty()){
            mEdtUsername.setError("Invalid Username");
        }
        else if (password.isEmpty()){

            mEdtPassword.setError("Invalid Password");
        }
        else if (!mDBHelper.validateData(username,password)){
            Toast.makeText(LoginActivity.this,"Username or Password is Incorrect",Toast.LENGTH_LONG).show();



        }else {
            final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();


            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    emptyText();

                }
            },2000);


        }


    }
    private void emptyText() {
        mEdtUsername.setText(null);
        mEdtPassword.setText(null);
    }



}
