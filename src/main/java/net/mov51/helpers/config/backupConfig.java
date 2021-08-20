package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.logHelper.*;

public class backupConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigBuilder");

    public enum Keys {
        //logging
        logFolder ("logFolder",false),
        logName ("logName",true),

        //sync Logging
        syncLogFolder ("syncLogFolder",false),
        syncLogName ("syncLogName",false),

        //backup logging
        backupLogFolder ("backupLogFolder",false),
        backupLogName ("backupLogName",false),

        //sync settings
        syncSource ("syncSource",true),
        syncDestination ("syncDestination",true),

        //backup settings
        backupSource ("backupSource",false),
        backupDestination ("backupDestination",true),
        backupName ("backupName",true),

        //commands
        startCommand ("startCommand",true),
        finishCommand ("finishCommand",true);

        public final String defaultKey;
        public final boolean required;

        Keys(String defaultKey, boolean required) {
            this.defaultKey = defaultKey;
            this.required = required;

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
        return loadGetter(Keys.logFolder,Global.getLogFolder());
    }

    public String getLogName(){
        //uses global logFolder as default
        return loadGetter(Keys.logName,Global.getLogName());
    }

    public String getSyncLogFolder(){
        //uses global syncLogFolder as default
        return loadGetter(Keys.syncLogFolder,Global.getSyncLogFolder());
    }

    public String getBackupLogFolder(){
        //uses global backupLogFolder as default
        return loadGetter(Keys.backupLogFolder,Global.getBackupLogFolder());
    }

    public String getSyncLogName(){
        //uses global syncLogName as default
        return loadGetter(Keys.syncLogName,Global.getSyncLogName());
    }

    public String getBackupLogName(){
        //uses global backupLogName as default
        return loadGetter(Keys.backupLogName,Global.getBackupLogName());
    }

    public String getSyncSource(){
        return loadGetter(Keys.syncSource);
    }

    public String getSyncDestination(){
        //no default
        return loadGetter(Keys.syncDestination);
    }

    public String getBackupSource(){
        //uses the syncSource as default
        return loadGetter(Keys.backupSource,this.getSyncDestination());
    }

    public String getBackupDestination(){
        //no default
        return loadGetter(Keys.backupDestination);
    }

    public String getBackupName(){
        //uses global backupName as default
        return loadGetter(Keys.backupName,Global.getBackupName());
    }

    public String getStartCommand(){
        //no default
        return loadGetter(Keys.startCommand);
    }

    public String getFinishCommand(){
        //no default
        return loadGetter(Keys.finishCommand);
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

    private String loadGetter(Keys key, String defaultOption){
        //no default
        return configMap.getOrDefault(key.defaultKey,defaultOption).toString();
    }
    private String loadGetter(Keys key){
        if(configMap.containsKey(key.defaultKey))
            return configMap.get(key.defaultKey).toString();
        logFatal(Logger,"Could not load key " + key + " from backupConfig!");
        return "";
    }
}
