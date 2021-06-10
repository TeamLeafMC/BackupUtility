package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

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
            System.exit(1);
        }
        return null;
    }

    public static String getApiKey(){
        return Objects.requireNonNull(getConfig()).get("key").toString();
    }

    public static String getUUID(){
        return Objects.requireNonNull(getConfig()).get("serverUUID").toString();
    }

    public static String getPanelURL(){
        return Objects.requireNonNull(getConfig()).get("panelURL").toString();
    }

    public static String getFromKey(String key){

        return Objects.requireNonNull(getConfig()).get(key).toString();
    }

}
