package bubtjobs.com.jobsite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener{
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


    // json node names
    private static final  String TAG_SUCCESS="success";
    private static final  String TAG_PRODUCT="product";
    private static final  String TAG_PID="pid";
    private static final  String TAG_NAME="name";
    private static final  String TAG_PRICE="price";
    private static final  String TAG_DESCRIPTION="description";

    String s="";

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
            pDialog = new ProgressDialog(SingupActivity.this);
            pDialog.setMessage("Loading product details. Please wait...");
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
                       // params.add(new BasicNameValuePair("pid", pid));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(url.app_users(), "GET", params);

                        // check your log for json response
                        //   Log.d("Single Product Details", json.toString());
                        s =json.toString();
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // display product data in EditText
                            Toast.makeText(SingupActivity.this,json.getString("message"),Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(SingupActivity.this,"error",Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        emailEt.setText("ok \n"+s);
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
