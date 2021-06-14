package net.mov51.helpers.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;


public class yamlHelper {
    //loads config file into map. Can only be accessed via getters
    protected static Map<String,Object> getValue(String Config){
        try {
            InputStream inputStream = new FileInputStream(String.valueOf(Config));
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);

        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static String getFromKey(String pathToConfig,String key){
        return Objects.requireNonNull(getValue(pathToConfig)).get(key).toString();
    }

}
