package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.config.yamlVerifier.backupVerify;
import static net.mov51.helpers.config.yamlVerifier.dataType;
import static net.mov51.helpers.logHelper.*;

public class backupConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigBuilder");

    public enum Keys {
        //logging
        logFolder ("logFolder",false, dataType.FileName),
        logName ("logName",true,dataType.FileName),

        //sync Logging
        syncLogFolder ("syncLogFolder",false,dataType.Folder),
        syncLogName ("syncLogName",false,dataType.FileName),

        //backup logging
        backupLogFolder ("backupLogFolder",false,dataType.Folder),
        backupLogName ("backupLogName",false,dataType.FileName),

        //sync settings
        syncSource ("syncSource",true,dataType.Folder),
        syncDestination ("syncDestination",true,dataType.Folder),

        //backup settings
        backupSource ("backupSource",false,dataType.Folder),
        backupDestination ("backupDestination",true,dataType.Folder),
        backupName ("backupName",true,dataType.FileName),

        //commands
        startCommand ("startCommand",true,dataType.Command),
        finishCommand ("finishCommand",true,dataType.Command);

        public final String defaultKey;
        public final boolean required;
        public final dataType DataType;

        Keys(String defaultKey, boolean required, dataType DataType) {
            this.defaultKey = defaultKey;
            this.required = required;
            this.DataType = DataType;
        }
    }

    Map<String, Object> configMap;

    globalConfig Global = globalConfig.getInstance();
    coreConfig Core = coreConfig.getInstance();

    public backupConfig(String configPath){
        this.configMap = getMap(configPath);
        backupVerify(this.configMap);
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
        return this.configMap.getOrDefault(key.defaultKey,defaultOption).toString();
    }
    private String loadGetter(Keys key){
        if(configMap.containsKey(key.defaultKey))
            return this.configMap.get(key.defaultKey).toString();
        logFatal(Logger,"Could not load key " + key + " from backupConfig!");
        return "";
    }
}
