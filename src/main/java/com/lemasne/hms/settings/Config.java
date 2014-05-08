package com.lemasne.hms.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private final Properties settings;
    private static Config instance = null;
    private static URI propsFileUri = null;

    private Config() {
        propsFileUri = new File(System.getProperty("user.home") + "\\" + Constants.CONFIG_FILE).toURI();
        this.settings = this.loadConfigProperties(Constants.CONFIG_FILE);
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

    public boolean set(String name, String value) {
        if (this.settings.containsKey(name)) {
            this.settings.setProperty(name, value);
            return true;
        }
        return false;
    }

    public static boolean save(Config config) {
        File propsFile = new File(propsFileUri);

        try {
            if (!propsFile.isFile()) {
                propsFile.createNewFile();
                propsFile.setReadable(true);
                propsFile.setWritable(true);
            }

            config.settings.store(new FileOutputStream(propsFile), null);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Properties loadConfigProperties(String fileName) {
        Properties props = new Properties();
        InputStream input = null;
        File propsFile = new File(propsFileUri);

        if (propsFile.isFile() && propsFile.canRead()) {
            try {
                input = new FileInputStream(propsFile.getAbsolutePath());
                props.load(input);
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                input = Config.class.getResourceAsStream("/" + fileName);
                if (input == null) {
                    throw new IOException("Cannot find the file " + fileName);
                }
                props.load(input);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return props;
    }
}
