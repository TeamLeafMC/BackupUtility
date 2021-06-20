package net.mov51.helpers.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

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

    private static Map<String,Object> getValue(String Config){
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

    public static String SafeGetFromKey(Path pathToConfig,String key){
        if(getValue(pathToConfig).containsKey(key)){
            return getValue(pathToConfig).get(key).toString();
        }else{
            System.out.println(key + "did not exist in " + pathToConfig.getFileName());
            System.exit(1);
        }
        return "false";
    }

    protected static String getDefaultCoreFromKey(String key){
        if(getValue(defaultCoreConfigFile).containsKey(key)){
            return getValue(defaultCoreConfigFile).get(key).toString();
        }else{
            System.out.println(key + "did not exist in " + defaultCoreConfigFile);
            System.exit(1);
        }
        return "false";
    }

    protected static String getCoreFromKey(String key){
        return SafeGetFromKey(userCoreConfigPath,key);
    }

    protected static String getLogCoreFromKey(String key){
        return SafeGetFromKey(userCoreLogConfigFile,key);
    }

}
