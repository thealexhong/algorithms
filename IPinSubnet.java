import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Integer;
import java.lang.NumberFormatException;


  

public class IPinSubnet {

  private static final String PATTERN = 
    "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

  public static boolean validate(final String ip) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();             
  }

  public static long ipToLong (String ipAddress) {
    String[] ipAddressArray = ipAddress.split("\\.");
    long result = 0;
    for (int i = 0; i < ipAddressArray.length; i++) {
      int power = 3 - i;
      try {
        int ip = Integer.parseInt(ipAddressArray[i]);
        result += ip * Math.pow(256, power);
      }
      catch (NumberFormatException e) {}
    }
    return result;
  }
  
  public static void main (String[] args) throws IOException{
    Scanner sc = null;
    PrintWriter pw = null;
    
    try {
      sc = new Scanner(new BufferedReader(new FileReader ("DATA2.txt")));
      pw = new PrintWriter(new FileWriter("OUT2.txt"));
      
      while (sc.hasNext())
      {
        //String[] ip = sc.next().split(" ");
        String[] ip = new String [3];
        ip[0] = sc.next();
        ip[1] = sc.next();
        ip[2] = sc.next();
        
        if (!validate(ip[2])) {
          System.out.println("InValid");
        }
        else if (ipToLong(ip[2]) < ipToLong(ip[0]) ||
            ipToLong(ip[2]) > ipToLong(ip[1]))
          System.out.println("OutRange");
        else
          System.out.println("InRange");
      }
      
    }
    finally
    {
      if (sc != null)
        sc.close();
      if (pw != null)
        pw.close();
    }
  } 
}