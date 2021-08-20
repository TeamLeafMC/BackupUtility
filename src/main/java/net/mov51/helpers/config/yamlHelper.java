package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static net.mov51.helpers.logHelper.*;


public class yamlHelper {

    public static Map<String, String> allKeys = new HashMap<>();

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("Config_logger");


    //loads config file into map
    public static Map<String,Object> getMap(String Config){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(Config);
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            logFatalExitE(Logger,e,"Could not load yaml file at " + Config);
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> getMap(Path Config){
            return getMap(Config.toString());
    }

}
