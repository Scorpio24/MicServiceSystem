package com.yyu.execute;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Execute {
    public HashMap<String, String> exeProcess(String cmd) throws Exception{
        Process process = Runtime.getRuntime().exec(cmd);

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        HashMap<String, String> result = new HashMap<String, String>();
        String[] outFormat = {"line1","line2","line3","line4","line5","line6","line7","line8","line9","line10"};

        for (String lineName : outFormat){
            if ((line = br.readLine()) != null){
                result.put(lineName, line);
            }
        }

        for (String key : result.keySet()) {
            System.out.println("key= "+ key + " and value= " + result.get(key));
        }

        isr.close();
        br.close();
        is.close();

        return result;
    }

    public static void main(String[] args) throws Exception{
        String cmd = "python D:\\test.py decs";
        Process process = Runtime.getRuntime().exec(cmd);

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        HashMap<String, String> result = new HashMap<String, String>();
        String[] outFormat = {"line1","line2","line3","line4","line5","line6","line7","line8","line9","line10"};

        for (String lineName : outFormat){
            if ((line = br.readLine()) != null){
                result.put(lineName, line);
            }
        }

        for (String key : result.keySet()) {
            System.out.println("key= "+ key + " and value= " + result.get(key));
        }

        isr.close();
        br.close();
        is.close();
    }
}
