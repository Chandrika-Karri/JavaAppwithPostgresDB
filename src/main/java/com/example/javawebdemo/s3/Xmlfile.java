package com.example.javawebdemo.s3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class Xmlfile {

    @Autowired
    private BucketCreation bucketCreation;

    public void createLoginXml(String email) {

        LocalDateTime now = LocalDateTime.now();
        String currentDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));



        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<LogIn-Details>\n" +
            "<Date>" + currentDate + "</Date>\n" +
            "<Time>" + currentTime + "</Time>\n" +
            "<Email>" + email + "</Email>\n" +
            "</LogIn-Details>\n";


        try {
            stringToDom(xml);
            bucketCreation.createBucketIfNotExists();
            bucketCreation.uploadFileToS3("logindetails.xml", "logindetails.xml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    public static void stringToDom(String xmlSource) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter("logindetails.xml");
        fw.write(xmlSource);
        fw.close();
    }

}

