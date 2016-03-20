package bubtjobs.com.jobsite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import bubtjobs.com.jobsite.Others.CommonFunction;
import bubtjobs.com.jobsite.R;

public class Login extends AppCompatActivity implements View.OnClickListener{
    Button loginBt;
    ImageButton backBt;
    EditText emailEt,passwordEt;
    TextView forgotPaasswordTv,singInSocailTv;
    CommonFunction commonFunction;
    boolean error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        initial();
    }
    public void  initial(){
        commonFunction=new CommonFunction();

        loginBt=(Button)findViewById(R.id.loginBt);
        backBt=(ImageButton)findViewById(R.id.backBt);

        emailEt=(EditText)findViewById(R.id.emailEt);
        passwordEt=(EditText)findViewById(R.id.passwordEt);

        forgotPaasswordTv=(TextView)findViewById(R.id.forgotPassword);
        singInSocailTv=(TextView)findViewById(R.id.singInSocail);

        loginBt.setOnClickListener(this);
        backBt.setOnClickListener(this);
        forgotPaasswordTv.setOnClickListener(this);
        singInSocailTv.setOnClickListener(this);

    }
    public  void forgotPassword(View view){

        startActivity(new Intent(Login.this, ForgotPasswor.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.backBt:
                startActivity(new Intent(Login.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                break;
            case R.id.loginBt:
                error=true;
                if(!commonFunction.isValidEmail(emailEt))
                {
                    errorMessage(emailEt,"Please enter valid email");
                }
                if(!commonFunction.isEmpty(passwordEt))
                {
                    errorMessage(passwordEt, "Please enter your password");
                }
                if(!commonFunction.isEmpty(emailEt))
                {
                    errorMessage(emailEt, "Please enter your email");
                }
                if(error)
                {
                    Toast.makeText(Login.this, "Ok", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.forgotPassword:
                break;
            case R.id.singInSocail:
                Intent intent=new Intent(this,SocailMediaLogin.class);
                startActivity(intent);
                break;
        }
    }

    private void errorMessage(EditText editText,String message)
    {
       editText.setError(message);
        editText.requestFocus();
        error=false;
    }
}
