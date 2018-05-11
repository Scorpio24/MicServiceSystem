package com.yyu.readConfigFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadConfigFile {

    public HashMap<String, Object> getServiseConfig() throws IOException{
        HashMap<String, Object> serviseConfig = new HashMap<>();
        List<String> paramFormat = new ArrayList<>();
        List<String> resultFormat = new ArrayList<>();
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

        FileReader fr2 = new FileReader("src/main/java/com/yyu/readConfigFile/ParamConfig.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        String line2;
        while ((line2 = br2.readLine()) != null) {
            paramFormat.add(line2);
        }
        if (!paramFormat.isEmpty()){
            serviseConfig.put("paramFormat", paramFormat);
        }

        FileReader fr3 = new FileReader("src/main/java/com/yyu/readConfigFile/ResultConfig.txt");
        BufferedReader br3 = new BufferedReader(fr3);
        String line3;
        while ((line3 = br3.readLine()) != null) {
            resultFormat.add(line3);
        }
        if (!resultFormat.isEmpty()){
            serviseConfig.put("resultFormat", resultFormat);
        }

        return serviseConfig;
    }

    public List<String> getResultFormat(){
        List<String> resultFormat = new ArrayList<>();

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