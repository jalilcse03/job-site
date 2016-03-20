package bubtjobs.com.jobsite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bubtjobs.com.jobsite.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    public void existingUser(View view){
        startActivity(new Intent(MainActivity.this,Login.class));
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
    public void newUser(View view){
        startActivity(new Intent(MainActivity.this,Singup.class));
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
