package bubtjobs.com.jobsite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);
    }
    public  void forgotPassword(View view){

        startActivity(new Intent(LoginActivity.this,ForgotPassworActivity.class));
    }
}
