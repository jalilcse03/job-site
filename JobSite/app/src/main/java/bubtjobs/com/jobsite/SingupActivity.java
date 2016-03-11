package bubtjobs.com.jobsite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener{
    Button singupBt;
    ImageButton backBt;

    EditText emailEt,passwordEt,retypePasswordEt;
    CommonFunction commonFunction;

    boolean error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_singup);
        initial();
    }

    private void initial() {
        commonFunction=new CommonFunction();

        singupBt=(Button)findViewById(R.id.singupBt);
        backBt=(ImageButton)findViewById(R.id.backBt);

        emailEt=(EditText)findViewById(R.id.emailEt);
        passwordEt=(EditText)findViewById(R.id.passwordEt);
        retypePasswordEt=(EditText)findViewById(R.id.retypePasswordEt);

        singupBt.setOnClickListener(this);
        backBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.backBt:
               startActivity(new Intent(SingupActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
               overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
               break;
           case R.id.singupBt:
               error=true;
               if(!commonFunction.isValidEmail(emailEt))
               {
                   errorMessage(emailEt,"Please enter valid email");
               }
               if(!commonFunction.isEmpty(retypePasswordEt))
               {
                   errorMessage(retypePasswordEt, "Please enter your password");
               }
               if(!commonFunction.isEmpty(passwordEt))
               {
                   errorMessage(passwordEt, "Please enter your password");
               }
               if(!commonFunction.isEmpty(emailEt))
               {
                   errorMessage(emailEt, "Please enter your email");
               }

               if(error){
                   if(passwordEt.getText().toString().equals(retypePasswordEt.getText().toString()))
                   {
                       commonFunction.toastMessate(this,"Its ok for registration");
                   }
                   else
                       commonFunction.toastMessate(this,"Its not ok");
               }
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
