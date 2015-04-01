package mainapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class ValidateAge {
  public static boolean main(String argv) {
 
      String sPhoneNumber = "60";
      //String sPhoneNumber = "605-88899991";
      //String sPhoneNumber = "605-888999A";
 
      Pattern pattern = Pattern.compile("\\d{2}");
      Matcher matcher = pattern.matcher(argv);
 
      if (matcher.matches()) {
    	  System.out.println("Phone Number Valid");
    	  return true;
      }
      else
      {
    	  System.out.println("Phone Number must be in the form XXX-XXXXXXX");
    	  return false;
      }
 }
}