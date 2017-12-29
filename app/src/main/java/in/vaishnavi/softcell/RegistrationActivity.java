package in.vaishnavi.softcell;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by vaishnavi on 29/12/17.
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEdtUsername,mEdtPassword;
    private Button mBtnRegister;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mDBHelper=new DBHelper(RegistrationActivity.this,"UserDatabase.db",null,1);

        initViews();

    }

    private void initViews() {

        mEdtUsername= (EditText) findViewById(R.id.edtTxtUsername);
        mEdtPassword= (EditText) findViewById(R.id.edtTxtPassword);
        mBtnRegister= (Button) findViewById(R.id.buttonRegister);


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=mEdtUsername.getText().toString();
                String password=mEdtPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()){

                    Toast.makeText(RegistrationActivity.this,"Username or Password field is empty",Toast.LENGTH_LONG).show();


                }else
                {
                    User user=new User(username,password);
                    mDBHelper.insertData(user);
                    emptyText();
                    finish();


                }

            }
        });
    }

    private void emptyText() {
        mEdtUsername.setText(null);
        mEdtPassword.setText(null);
    }


}
