package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class yamlHelper {
    //loads config file into map. Can only be accessed via getters
    private static Map<String,Object> getConfig(){
        try {
            InputStream inputStream = new FileInputStream(new File("config.yml"));
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);

        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
        }
        return null;
    }

    public static String getKey(){
        return getConfig().get("key").toString();
    }

    public static String getUUID(){
        return getConfig().get("serverUUID").toString();
    }

    public static String getPanelURL(){
        return getConfig().get("panelURL").toString();
    }
}
