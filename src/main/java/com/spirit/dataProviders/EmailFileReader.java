package com.spirit.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailFileReader {


    private Properties properties;
    private final String EmailFilePath = "/src/main/resources/configs/Email.properties";

    public EmailFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + EmailFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Email.properties not found at " + EmailFilePath);
        }
    }



    public String getOutLookURL(){
        String outLookURL = properties.getProperty("outlookURL");
        if(outLookURL != null){
            return outLookURL;
        }else{
            throw new RuntimeException("outlookURL not specified in the Email.properties file");
        }
    }

    public String getOutLookUserName(){
        String outLookUserName = properties.getProperty("outlookUserName");
        if(outLookUserName != null){
            return outLookUserName;
        }else{
            throw new RuntimeException("outlookUserName not specified in the Email.properties file");
        }
    }

    public String getOutLookPassword(){
        String outLookPassword = properties.getProperty("outlookPassword");
        if(outLookPassword != null){
            return outLookPassword;
        }else{
            throw new RuntimeException("outlookPassword not specified in the Email.properties file");
        }
    }

    public String getEmailHost(){
        String emailHost = properties.getProperty("emailHost");
        if(emailHost != null) {
            return emailHost;
        }else {
            throw new RuntimeException("emailHost not specified in the Email.properties file");
        }
    }


    public boolean getEmailAuthentication(){
        String emailAuthentication = properties.getProperty("emailAuthentication");
        if(emailAuthentication != null) {
            return Boolean.valueOf(emailAuthentication);
        }else {
            throw new RuntimeException("emailAuthentication not specified in the Email.properties file");
        }
    }

    public String getEmailPort(){
        String emailPort = properties.getProperty("emailPort");
        if(emailPort != null) {
            return emailPort;
        }else {
            throw new RuntimeException("emailPort not specified in the Configuration.properties file");
        }
    }

    public String getRecipientType_TO(){
        String recipientType_TO = "";
        String recipientType_TO1 = properties.getProperty("RecipientType_TO1");
        String recipientType_TO2 = properties.getProperty("RecipientType_TO2");
        String recipientType_TO3 = properties.getProperty("RecipientType_TO3");

        if(recipientType_TO1 != null) {
            if(!recipientType_TO1.equalsIgnoreCase("null")){
                recipientType_TO = recipientType_TO1;
            }
        }else {
            throw new RuntimeException("RecipientType_TO1 not specified in the Configuration.properties file");
        }

        if(recipientType_TO2 != null) {
            if(!recipientType_TO2.equalsIgnoreCase("null")){
                recipientType_TO = recipientType_TO + "," + recipientType_TO2;
            }
        }else {
            throw new RuntimeException("RecipientType_TO2 not specified in the Configuration.properties file");
        }

        if(recipientType_TO3 != null) {
            if(!recipientType_TO3.equalsIgnoreCase("null")){
                recipientType_TO = recipientType_TO + "," + recipientType_TO3;
            }
        }else {
            throw new RuntimeException("RecipientType_TO2 not specified in the Configuration.properties file");
        }

        return recipientType_TO;
    }
}
