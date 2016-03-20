package bubtjobs.com.jobsite.Others;

/**
 * Created by Murtuza on 3/13/2016.
 */
public class Common_Url {
    private String commonPart="http://www.bubtjobs.com/app/api/";
   // private String commonPart="http://10.0.3.2/app/api/";
    //private String commonPart="http://10.0.2.2/app/api/";
    public void Common_Url(){
    }

    public String testing(){
        return commonPart+"test";
    }

    public String signup(){
        return commonPart+"singup";
    }
}
