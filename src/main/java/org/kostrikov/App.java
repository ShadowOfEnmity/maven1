package org.kostrikov;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        var properties = new Properties();
        try (InputStream resourceStream = ClassLoader.getSystemResourceAsStream("configuration.properties")) {
            properties.load(resourceStream);
            String dataLocation = properties.getProperty("dataLocation");
            if (!dataLocation.isEmpty()){
                System.out.println(MyJakarta.readFromJson(dataLocation));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
