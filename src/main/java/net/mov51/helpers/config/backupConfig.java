package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.getMap;

public class backupConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigBuilder");

    private enum backupKeys {
        //logging
        logFolder ("logFolder"),
        logName ("logName"),

        //sync Logging
        syncLogFolder ("syncLogFolder"),
        syncLogName ("syncLogName"),

        //backup logging
        backupLogFolder ("backupLogFolder"),
        backupLogName ("backupLogName"),

        //sync settings
        syncSource ("syncSource"),
        syncDestination ("syncDestination"),

        //backup settings
        backupSource ("backupSource"),
        backupDestination ("backupDestination"),
        backupName ("backupName"),

        //commands
        startCommand ("startCommand"),
        finishCommand ("finishCommand");

        public final String defaultKey;

        backupKeys(String defaultKey) {
            this.defaultKey = defaultKey;
        }
    }

    Map<String, Object> configMap;

    globalConfig Global = globalConfig.getInstance();
    coreConfig Core = coreConfig.getInstance();

    public backupConfig(String configPath){
        this.configMap = getMap(configPath);
    }

    public String getLogFolder(){
        //uses global logFolder as default
        String key = backupKeys.logFolder.defaultKey;
        return loadGetter(key,Global.getLogFolder());
    }

    public String getLogName(){
        //uses global logFolder as default
        String key = backupKeys.logName.defaultKey;
        return loadGetter(key,Global.getLogName());
    }

    public String getSyncLogFolder(){
        //uses global syncLogFolder as default
        String key = backupKeys.syncLogFolder.defaultKey;
        return loadGetter(key,Global.getSyncLogFolder());
    }

    public String getBackupLogFolder(){
        //uses global backupLogFolder as default
        String key = backupKeys.backupLogFolder.defaultKey;
        return loadGetter(key,Global.getBackupLogFolder());
    }

    public String getSyncLogName(){
        //uses global syncLogName as default
        String key = backupKeys.syncLogName.defaultKey;
        return loadGetter(key,Global.getSyncLogName());
    }

    public String getBackupLogName(){
        //uses global backupLogName as default
        String key = backupKeys.backupLogName.defaultKey;
        return loadGetter(key,Global.getBackupLogName());
    }

    public String getSyncSource(){
        String key = backupKeys.syncSource.defaultKey;
        return loadGetter(key);
    }

    public String getSyncDestination(){
        //no default
        String key = backupKeys.syncDestination.defaultKey;
        return loadGetter(key);
    }

    public String getBackupSource(){
        //uses the syncSource as default
        String key = backupKeys.backupSource.defaultKey;
        return loadGetter(key,this.getSyncSource());
    }

    public String getBackupDestination(){
        //no default
        String key = backupKeys.backupDestination.defaultKey;
        return loadGetter(key);
    }

    public String getBackupName(){
        //uses global backupName as default
        String key = backupKeys.backupName.defaultKey;
        return loadGetter(key,Global.getBackupName());
    }

    public String getStartCommand(){
        //no default
        String key = backupKeys.startCommand.defaultKey;
        return loadGetter(key);
    }

    public String getFinishCommand(){
        //no default
        String key = backupKeys.finishCommand.defaultKey;
        return loadGetter(key);
    }

    public String getPterodactylAPIkey(){
        //no default
        //todo handle coreConfig overrides
        return Core.getPterodactylAPIkey();
    }

    public String getPterodactylServerUUID(){
        //no default
        //todo handle coreConfig overrides
        return Core.getPterodactylServerUUID();
    }

    public String getPterodactylPanelURL(){
        //no default
        //todo handle coreConfig overrides
        return Core.getPterodactylPanelURL();
    }

    private String loadGetter(String key,String defaultOption){
        //no default
        return configMap.getOrDefault(key,defaultOption).toString();
    }
    private String loadGetter(String key){
        if(configMap.containsKey(key))
            return configMap.get(key).toString();
        return null;
    }
}
