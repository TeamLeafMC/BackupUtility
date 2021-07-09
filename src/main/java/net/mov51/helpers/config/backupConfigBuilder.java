package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.util.Map;

import static net.mov51.Main.coreConfig;
import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.logHelper.*;

public class backupConfigBuilder {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigBuilder");

    public String logFolder;
    public String syncLogName;
    //todo if no then no log
    public String backupLogName;

    public String syncSource;
    public String syncDestination;

    //todo if no then use sync destination
    public String backupSource;
    public String backupDestination;
    public String backupName;

    //todo need to build array command processor
    public String startCommand;
    public String finishCommand;

    public String logFolderDefaultKey = "logFolder";
    public String syncLogNameDefaultKey = "syncLogName";
    public String backupLogNameDefaultKey = "BackupLogName";

    public String syncSourceDefaultKey = "syncSource";
    public String syncDestinationDefaultKey = "syncDestination";

    public String backupSourceDefaultKey = "backupSource";
    public String backupDestinationDefaultKey = "backupDestination";
    public String backupNameDefaultKey = "backupName";

    public String startCommandsDefaultKey = "startCommand";
    public String finishCommandsDefaultKey = "finishCommand";

    //------Values Replaced with Core values--------
    public String apiKey;
    public String serverUUID;
    public String panelURL;

    private static Map<String, Object> ConfigMap;

    public backupConfigBuilder(String configPath){

        try{
            ConfigMap = getMap(configPath);

            logFolder = loadValue(logFolderDefaultKey);
            syncLogName = loadValue(syncLogNameDefaultKey);
            backupLogName = loadValue(backupLogNameDefaultKey);

            syncSource = loadValue(syncSourceDefaultKey);
            syncDestination = loadValue(syncDestinationDefaultKey);

            backupDestination = loadValue(backupDestinationDefaultKey);
            backupSource = loadValue(backupSourceDefaultKey);
            backupName = loadValue(backupNameDefaultKey);

            startCommand = loadValue(startCommandsDefaultKey);
            finishCommand = loadValue(finishCommandsDefaultKey);

            apiKey = loadCoreValue(keyDefaultAPIkey);
            serverUUID = loadCoreValue(keyDefaultSeverUUID);
            panelURL = loadCoreValue(keyDefaultPanelURL);

        }catch (Exception e){
            logFatalExitE(Logger,e,"Failed to build backup config at " + configPath);
        }

    }

    private String loadCoreValue(String key){
        //todo do redundancy logic for default config
        if(ConfigMap.containsKey(key)){
            logInfo(Logger,"Using override on value \"" + key + "\" for backup " + backupName);
            return ConfigMap.get(key).toString();
        }else{
            logInfo(Logger,"Using Core Configuration value for backup " + backupName);
            return coreConfig.getCoreByKey(key);
        }

    }

    private String loadValue(String key){
        //todo do redundancy logic for default config
        return ConfigMap.get(key).toString();
    }
}
