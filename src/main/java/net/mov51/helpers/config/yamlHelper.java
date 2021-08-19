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

    public static void loadKeys(){
        allKeys.put("logNameDefaultKey","logName");
        allKeys.put("logFolderDefaultKey","logFolder");
        allKeys.put("backupLogFolderDefaultKey","backupLogFolder");
        allKeys.put("syncLogFolderFolderDefaultKey","syncLogFolder");
        allKeys.put("syncLogNameDefaultKey","syncLogName");
        allKeys.put("backupLogNameDefaultKey","backupLogName");

        allKeys.put("syncSourceDefaultKey","syncSource");
        allKeys.put("syncDestinationDefaultKey","syncDestination");

        allKeys.put("backupSourceDefaultKey","backupSource");
        allKeys.put("backupDestinationDefaultKey","backupDestination");
        allKeys.put("backupNameDefaultKey","backupName");

        allKeys.put("startCommandsDefaultKey","startCommand");
        allKeys.put("finishCommandsDefaultKey","finishCommand");

        allKeys.put("ApiKeyDefaultKey","APIkey");
        allKeys.put("SeverUuidDefaultKey","serverUUID");
        allKeys.put("PanelUrlDefaultKey","panelURL");
    }


    //loads config file into map. Can only be accessed via getters
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
