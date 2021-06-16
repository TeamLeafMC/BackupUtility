package net.mov51.helpers.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.configHelper.*;


public class yamlHelper {
    //loads config file into map. Can only be accessed via getters
    private static Map<String,Object> getValue(Path Config){
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

    protected static String getFromKey(Path pathToConfig,String key){
        return Objects.requireNonNull(getValue(pathToConfig)).get(key).toString();
    }

    protected static String getDefaultFromKey(String key){
        return Objects.requireNonNull(getValue(userCoreConfigPath)).get(key).toString();
    }

    protected static String getCoreFromKey(String key){
        return Objects.requireNonNull(getValue(userCoreConfigPath)).get(key).toString();
    }

    protected static String getLogCoreFromKey(String key){
        return Objects.requireNonNull(getValue(userCoreLogConfigFile)).get(key).toString();
    }

}
