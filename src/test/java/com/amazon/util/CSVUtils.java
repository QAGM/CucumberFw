package com.amazon.util;

import com.opencsv.CSVReader;
import org.openqa.selenium.WebElement;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;

public class CSVUtils {

    static String filePath = "/Users/gafarfarajov/IdeaProjects/EcommerceTest/src/main/resources/testdata.csv";


    public static Object[][] readCSV(Method method)
    {
        Object[][] dataObject = null;
        ArrayList<Map<String,String>> data = new ArrayList<>();
        HashMap<String,String> row = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] records;
            records = csvReader.readNext(); // read teh header
            String key1 = records[1]; // second column is first key
            String key2 = records[2]; // third column in second key
            while((records= csvReader.readNext())!=null)
            {
                if(records[0].equals(method.getName())) {
                    row = new HashMap<>();
                    row.put(key1, records[1]);
                    row.put(key2, records[2]);
                    data.add(row);
                }
            }
        }
        catch (Exception e)
        {

        }
        dataObject = new Object[data.size()][1];
        for(int i=0;i<data.size();i++){
            dataObject[i][0] = data.get(i);
        }


        return dataObject;
    }
    public static Object[][] readCSV1()
    {
        List<Object> data1 = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] records;
            while((records= csvReader.readNext())!=null)
            {
                data1.add(records[0]);
            }
        }
        catch (Exception e)
        {

        }
        System.out.println(data1.toString());
        Object[][] objectData1= new Object[data1.size()][1];
        for(int i= 0;i<data1.size();i++) {

            objectData1[i][0]= data1.get(i);

        }
        return objectData1;
    }



}
