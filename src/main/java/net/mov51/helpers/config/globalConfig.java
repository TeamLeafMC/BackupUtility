package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;
import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.fileHelper.copyFromStream;
import static net.mov51.helpers.fileHelper.createDirs;
import static net.mov51.helpers.logHelper.*;
import static net.mov51.helpers.logHelper.logFatalExitE;

public class globalConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigLogger");

    private static final Path userGlobalConfigPath = Paths.get("config" + File.separator + "globalConfig.yml");
    private static final String internalGlobalConfigPath = "/defaultGlobalConfig.yml";

    private Map<String, Object> configMap;
    private static final globalConfig INSTANCE = new globalConfig();

    private enum globalKeys {
        //logging
        logFolder ("logFolder",true),
        logName ("logName",true),

        //sync Logging
        syncLogFolder ("syncLogFolder",false),
        syncLogName ("syncLogName",false),

        //backup logging
        backupLogFolder ("backupLogFolder",false),
        backupLogName ("backupLogName",false),

        //backup settings
        backupName ("backupName",true);

        public final String defaultKey;

        globalKeys(String defaultKey,boolean required) {
            this.defaultKey = defaultKey;
        }
    }

    public static globalConfig getInstance() {
        return INSTANCE;
    }

    private globalConfig(){
        if(userGlobalConfigPath.toFile().exists()){
            configMap = getMap(userGlobalConfigPath);
        }
        else
        {
        //creating default core config file
        logError(Logger,"No Core Global Configuration exists. Creating default Core Config file.");
            if(createDirs(userGlobalConfigPath)){
                logInfo(Logger,"Verifying default configuration directory for Global Configuration file");

                if (copyFromStream(internalGlobalConfigPath,userGlobalConfigPath)) {
                    logInfo(Logger," Global Configuration file was created :D");
                    logFatalExit(Logger,"Please update it with your values!");
                    //todo link wiki
                }
            }
        }

    }

    //--LOGGING--

    public String getLogName(){
        String key = globalKeys.logName.defaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            //todo replace with verification check
            return null;
        }
    }

    public String getLogFolder(){
        //todo I'm not sure if logging while the log folder is being made makes any sense.
        String key = globalKeys.logFolder.defaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            File file = new File(INSTANCE.configMap.get(key).toString());
            if(!file.isDirectory()){
                if(file.mkdirs()) {
                    logInfo(Logger, "logging folder created!");
                }else{
                    logFatalExit(Logger, "logging folder could not be created!");
                }
            }
            return INSTANCE.configMap.get(key).toString();
        }
        //todo replace with verification check
        return null;
    }

    public String getBackupLogFolder(){
        //Defaults to logFolder
        return loadGetter(globalKeys.backupLogFolder,getLogFolder());
    }
    public String getSyncLogFolder(){
        //defaults to logFolder
        return loadGetter(globalKeys.syncLogFolder,getLogFolder());
    }

    public String getSyncLogName(){
        return loadGetter(globalKeys.syncLogName);
    }

    public String getBackupLogName(){
        return loadGetter(globalKeys.backupLogName);
    }
    //--BACKUPS--

    public String getBackupName(){
        return loadGetter(globalKeys.backupName);
    }

    private String loadGetter(globalKeys key,String defaultOption){
        //no default
        return configMap.getOrDefault(key.defaultKey,defaultOption).toString();
    }
    private String loadGetter(globalKeys key){
        if(configMap.containsKey(key.defaultKey))
            return configMap.get(key.defaultKey).toString();
        logFatal(Logger,"Could not load key " + key + " from globalConfig!");
        return "";
    }

}
