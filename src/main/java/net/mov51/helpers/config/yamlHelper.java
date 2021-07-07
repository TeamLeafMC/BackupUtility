package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import static net.mov51.helpers.config.configHelper.*;
import static net.mov51.helpers.logHelper.*;


public class yamlHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("Config_logger");

    //loads config file into map. Can only be accessed via getters
    private static Map<String,Object> getValue(Path Config){
        try {
            InputStream inputStream = new FileInputStream(String.valueOf(Config));
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);

        } catch (Exception e) {
            logFatalExitE(Logger,e,"Could not load yaml file at " + Config.toString());
        }
        return null;
    }

    private static Map<String,Object> getValue(String Config){
        try {
            InputStream inputStream = new FileInputStream(String.valueOf(Config));
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (Exception e) {
            logFatalExitE(Logger,e,"Could not load key from yaml file at " + Config);
        }
        return null;
    }

    public static String SafeGetFromKey(Path pathToConfig,String key){
        if(Objects.requireNonNull(getValue(pathToConfig)).containsKey(key)){
            return Objects.requireNonNull(getValue(pathToConfig)).get(key).toString();
        }else{
            logFatalExit(Logger,key + "did not exist in " + pathToConfig.getFileName());
        }
        return "false";
    }

    protected static String getDefaultCoreFromKey(String key){
        if(Objects.requireNonNull(getValue(defaultCoreConfigFile)).containsKey(key)){
            return Objects.requireNonNull(getValue(defaultCoreConfigFile)).get(key).toString();
        }else{
            logFatalExit(Logger,key + "did not exist in " + defaultCoreConfigFile);
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
