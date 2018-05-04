package com.yyu.readConfigFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadConfigFile {
    public List<String> getResultFormat() throws IOException{
        FileReader fr = new FileReader("src/main/java/com/yyu/readConfigFile/ConfigFile.txt");
        BufferedReader br = new BufferedReader(fr);
        List<String> resultFormat = new ArrayList<>();
        String line = null;

        while ((line = br.readLine()) != null){
            resultFormat.add(line);
        }

        return resultFormat;
    }

    public static void main(String[] args) throws IOException{

        FileReader fr = new FileReader("src/main/java/com/yyu/readConfigFile/ConfigFile.txt");
        BufferedReader br = new BufferedReader(fr);
        List<String> outFormat = new ArrayList<>();
        String line = null;

        while ((line = br.readLine()) != null){
                outFormat.add(line);
        }
        System.out.println(outFormat.toString());
    }
}