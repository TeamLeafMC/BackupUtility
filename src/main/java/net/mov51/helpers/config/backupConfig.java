package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.logHelper.logError;
import static net.mov51.helpers.logHelper.logInfo;

public class backupConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigBuilder");

    protected String logFolderDefaultKey = "logFolder";
    protected String backupLogFolderDefaultKey = "backupLogFolder";
    protected String syncLogFolderFolderDefaultKey = "syncLogFolder";
    protected String syncLogNameDefaultKey = "syncLogName";
    protected String backupLogNameDefaultKey = "backupLogName";

    protected String syncSourceDefaultKey = "syncSource";
    protected String syncDestinationDefaultKey = "syncDestination";

    protected String backupSourceDefaultKey = "backupSource";
    protected String backupDestinationDefaultKey = "backupDestination";
    protected String backupNameDefaultKey = "backupName";

    protected String startCommandsDefaultKey = "startCommand";
    protected String finishCommandsDefaultKey = "finishCommand";

    Map<String, Object> configMap;

    public backupConfig(String configPath){
        this.configMap = getMap(configPath);
    }

    public String getLogFolder(){
        String key = this.logFolderDefaultKey ;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getSyncLogFolder(){
        String key = this.syncLogFolderFolderDefaultKey;
        if(configMap.containsKey(key)){
            String out = configMap.get(key).toString();
            File file = Paths.get(out).toFile();

            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "config folder created!");
                }else{
                    logError(Logger, "config folder could not be created!");
                }
            }
            return out;
        }else{
            return this.getLogFolder();
        }
    }

    public String getBackupLogFolder(){
        String key = this.backupLogFolderDefaultKey;
        if(configMap.containsKey(key)){
            String out = configMap.get(key).toString();
            File file = Paths.get(out).toFile();

            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "log folder created!");
                }else{
                    logError(Logger, "log folder could not be created!");
                }
            }
            return out;
        }else{
            return this.getLogFolder();
        }
    }

    public String getSyncLogName(){
        String key = this.syncLogNameDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getBackupLogName(){
        String key = this.backupLogNameDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getSyncSource(){
        String key = this.syncSourceDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getSyncDestination(){
        String key = this.syncDestinationDefaultKey;
        if(configMap.containsKey(key)){
            String out = configMap.get(key).toString();
            File file = Paths.get(out).toFile();

            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "sync destination folder created!");
                }else{
                    logError(Logger, "sync destination folder could not be created!");
                }
            }
            return out;
        }else{
            return this.getLogFolder();
        }
    }

    public String getBackupSource(){
        String key = this.backupSourceDefaultKey;
        if(configMap.containsKey(key)){
            String out = configMap.get(key).toString();
            File file = Paths.get(out).toFile();

            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "backup source folder created!");
                }else{
                    logError(Logger, "backup source folder could not be created!");
                }
            }
            return out;
        }else{
            return this.getLogFolder();
        }
    }

    public String getBackupDestination(){
        String key = this.backupDestinationDefaultKey;
        if(configMap.containsKey(key)){
            String out = configMap.get(key).toString();
            File file = Paths.get(out).toFile();

            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "backup destination folder created!");
                }else{
                    logError(Logger, "backup destination folder could not be created!");
                }
            }
            return out;
        }else{
            return this.getLogFolder();
        }
    }

    public String getBackupName(){
        String key = this.backupNameDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getStartCommand(){
        String key = this.startCommandsDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getFinishCommand(){
        String key = this.finishCommandsDefaultKey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getPterodactylAPIkey(){
        String key = coreConfig.getInstance().keyDefaultAPIkey;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return coreConfig.getPterodactylAPIkey();
        }
    }

    public String getPterodactylServerUUID(){
        String key = coreConfig.getInstance().keyDefaultSeverUUID;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return coreConfig.getPterodactylServerUUID();
        }
    }

    public String getPterodactylPanelURL(){
        String key = coreConfig.getInstance().keyDefaultPanelURL;
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            return coreConfig.getPterodactylPanelURL();
        }
    }
}
