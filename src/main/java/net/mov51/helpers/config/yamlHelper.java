package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

import static net.mov51.helpers.logHelper.*;


public class yamlHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("Config_logger");

    //loads config file into map. Can only be accessed via getters
    public static Map<String,Object> getMap(String Config){
        try {
            InputStream inputStream = new FileInputStream(Config);
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);

        } catch (Exception e) {
            logFatalExitE(Logger,e,"Could not load yaml file at " + Config);
        }
        return null;
    }

    public static Map<String,Object> getMap(Path Config){
            return getMap(Config.toString());
    }

}
