package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormats {

  public static Date parse(String dateString) {
    try{
      SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
      return sdf.parse(dateString);
    }catch(Exception e){
      System.out.println("Error al parsear fecha");
      return null;
    }
  }

}
