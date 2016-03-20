package bubtjobs.com.jobsite.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bubtjobs.com.jobsite.Others.AlertDialogManager;
import bubtjobs.com.jobsite.Others.CommonFunction;
import bubtjobs.com.jobsite.Others.Common_Url;
import bubtjobs.com.jobsite.JSON.JSONParser;
import bubtjobs.com.jobsite.R;
import bubtjobs.com.jobsite.Others.Variables;

public class Singup extends AppCompatActivity implements View.OnClickListener{
    Button singupBt;
    ImageButton backBt;

    EditText emailEt,passwordEt,retypePasswordEt;
   CommonFunction commonFunction;

    boolean error;
    // progress dialog
    private ProgressDialog pDialog;
    // json parser class
    JSONParser jsonParser=new JSONParser();
    //commonURl
    Common_Url url;
    // variable
    Variables variables;

    // json node names
    private static final  String TAG_SUCCESS="success";

    String s="";

    AlertDialogManager alertDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_singup);
        initial();
    }

    private void initial() {
        commonFunction=new CommonFunction();
        url=new Common_Url();
        variables=new Variables();
        alertDialogManager=new AlertDialogManager();

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
               startActivity(new Intent(Singup.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
                       //commonFunction.toastMessate(this,"Its ok for registration");
                       new registration().execute();
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


    /**
     * Background Async Task to registration
     * */


    class registration extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Singup.this);
            pDialog.setMessage(Variables.LOADING_MESSAGE);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting product details in background thread
         * */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair(Variables.USRE_EMAIL,emailEt.getText().toString()));
                        params.add(new BasicNameValuePair(Variables.USER_PASSWORD,passwordEt.getText().toString()));
                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(url.signup(), "POST", params);

                        // check your log for json response
                        //   Log.d("Single Product Details", json.toString());
                       s =json.toString();
                        // json success tag
                        success = json.getInt(Variables.TAG_SUCCESS);
                        if (success == 1) {
                            emailEt.getText().clear();
                            passwordEt.getText().clear();
                            retypePasswordEt.getText().clear();
                            alertDialogManager.showAlertDialog(Singup.this, "Next Step", json.getString("message"),true);

                        }else{
                            //Toast.makeText(Singup.this,"else",Toast.LENGTH_SHORT).show();
                            alertDialogManager.showAlertDialog(Singup.this,"Error",json.getString("message"),false);

                        }
                    } catch (JSONException e) {
                       // emailEt.setText("ok \n"+s);
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

}
