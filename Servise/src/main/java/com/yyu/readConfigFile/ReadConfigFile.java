package com.yyu.readConfigFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadConfigFile {

    public HashMap<String, String> getServiseConfig() throws IOException{
        HashMap<String, String> serviseConfig = new HashMap<>();
        String serviseName;
        String run;
        String ipAdress;
        String port;

        FileReader fr = new FileReader("src/main/java/com/yyu/readConfigFile/ServiseConfig.txt");
        BufferedReader br = new BufferedReader(fr);

        if ((serviseName = br.readLine()) != null){
            serviseConfig.put("name", serviseName);
        }
        if ((run = br.readLine()) != null){
            serviseConfig.put("run", run);
        }
        if ((ipAdress = br.readLine()) != null){
            serviseConfig.put("ip", ipAdress);
        }
        if ((port = br.readLine()) != null){
            serviseConfig.put("port", port);
        }

        return serviseConfig;
    }

    public List<String> getResultFormat(){
        List<String> resultFormat = new ArrayList<>();
        HashMap<String, String> serviseConfig = new HashMap<>();
        String serviseName = null;
        String ipAdress = null;
        String port = null;

        String line;
        FileReader fr;

        System.out.println("readconfigfile");
        try
        {
            fr = new FileReader("src/main/java/com/yyu/readConfigFile/ResultConfig.txt");
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
            return resultFormat;
        }
        BufferedReader br = new BufferedReader(fr);

        try {
            while ((line = br.readLine()) != null) {
                resultFormat.add(line);
            }
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
        }
        System.out.println(resultFormat);
        return resultFormat;
    }
}