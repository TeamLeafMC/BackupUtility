package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import static net.mov51.helpers.configHelper.defaultConfigFile;
import static net.mov51.helpers.configHelper.userConfigFile;

public class yamlHelper {
    //loads config file into map. Can only be accessed via getters
    private static Map<String,Object> getConfig(){
        try {
            InputStream inputStream = new FileInputStream(String.valueOf(userConfigFile));
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);

        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
        }
        return null;
    }

    public static String getApiKey(){
        return getConfig().get("key").toString();
    }

    public static String getUUID(){
        return getConfig().get("serverUUID").toString();
    }

    public static String getPanelURL(){
        return getConfig().get("panelURL").toString();
    }

    public static String getFromKey(String key){
        return getConfig().get(key).toString();
    }

}
