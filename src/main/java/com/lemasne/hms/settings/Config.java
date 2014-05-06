package com.lemasne.hms.settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {

    private final Properties settings;
    private static Config instance = null;

    private Config() {
        this.settings = this.loadConfigProperties(Constants.CONFIG_FILE);
        
        for (Object s : settings.keySet()) {
            System.out.println(s);
        }
    }

    public synchronized static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String get(String name) {
        if (this.settings.containsKey(name)) {
            return this.settings.getProperty(name);
        }
        return null;
    }

//    private void display(InputStream is) throws IOException {
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//            }
//        }
//    }

    private Properties loadConfigProperties(String fileName) {
        Properties props = new Properties();

        try {
            InputStream input = Config.class.getResourceAsStream(fileName);
//            this.display(input);
            if (input == null) {
                throw new IOException("Cannot find the file " + fileName);
            }
            props.load(input);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return props;
    }
}
