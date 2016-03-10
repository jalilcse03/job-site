package bubtjobs.com.jobsite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SingupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_singup);
    }
}
