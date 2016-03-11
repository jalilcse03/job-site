package bubtjobs.com.jobsite;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Murtuza on 3/11/2016.
 */
public class CommonFunction {
    // Email Validation Check
    public  boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    // Is Empty Check Edittext
    public boolean isEmpty(EditText etText)
    {
        if(etText.getText().toString().trim().length()>0)
            return true;
        return false;
    }
    // check internet connection
    public boolean isNetworkAvailable(Context context){
        ConnectivityManager cManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cManager.getActiveNetworkInfo();

        if(networkInfo!=null)
        {
            if(networkInfo.isAvailable() && networkInfo.isConnected())
            {
                return true;
            }
        }

        return false;
    }
}
