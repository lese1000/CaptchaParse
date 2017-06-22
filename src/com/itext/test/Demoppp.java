package com.itext.test;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demoppp {
    public static List<String> getTagTitile_Wenzi(String content)
    {
        String regex ="title="+'"'+"[^=]+"+'"'; 
        Pattern pa = Pattern.compile(regex); 
        Matcher ma = pa.matcher(content); 
        List<String> list = new ArrayList<String>(); 
        while (ma.find()) { 
            list.add(ma.group()); 
        } 
        return list; 
        
        
    }

    public static List<String> getTagA_WenZi(String content)
    {
        
        String regex = "<a.*?</a>"; 
        Pattern pa = Pattern.compile(regex); 
        Matcher ma = pa.matcher(content); 
        List<String> list = new ArrayList<String>(); 
        while (ma.find()) { 
            list.add(ma.group()); 
        } 
        return list; 
        
    }
    
    
    public static void main(String[] args) throws IOException {
        String surl="https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx";
        URL url=new URL(surl);
        HttpURLConnection connectionss=(HttpURLConnection) url.openConnection();
        connectionss.setDoOutput(true);
        OutputStreamWriter out=new OutputStreamWriter(connectionss.getOutputStream(),"utf-8");
        out.write("ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$txtOrignlPgTranNo=EQ268815110IN&ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$txtCaptcha=7965a6");//Post关键所在
        out.flush();
        out.close();
        String cookieVal = connectionss.getHeaderField("Set-Cookie"); 
        String url2="https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx";
        URL url3=new URL(url2);
        HttpURLConnection connectionsf=(HttpURLConnection) url3.openConnection();
        if (cookieVal!=null) {
            connectionsf.setRequestProperty("Cookie", cookieVal);
        }
            
        connectionsf.connect();
        InputStream urlStream=connectionsf.getInputStream();
        BufferedReader bk=new BufferedReader(new InputStreamReader(urlStream,"utf-8"));
        String  ss = null;  
        String total = ""; 
        while ((ss=bk.readLine())!=null) {
            
            total+=ss;
        }
        System.out.println(total);
        
//        List<String> TVALUE=getTagTitile_Wenzi(total);
//        for (int i = 0; i < TVALUE.size(); i++) {
//            String aString=TVALUE.get(i).replaceAll("[\\w]+|="+"|"+'"',"").trim();
//            System.out.println(aString);
//        }
//        
//    System.out.println("a标签的东西");
//    List<String> aTagString=getTagA_WenZi(total);
//    for (int i = 0; i < aTagString.size(); i++) {
//        
//        String aString=aTagString.get(i).replaceAll("<[^>]+>","").trim();
//        
//        System.out.println(aString);
//    
//    }
    }
    
}