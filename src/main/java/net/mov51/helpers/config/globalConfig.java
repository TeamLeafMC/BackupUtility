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
        logFolder ("logFolder"),
        logName ("logName"),

        //sync Logging
        syncLogFolder ("syncLogFolder"),
        syncLogName ("syncLogName"),

        //backup logging
        backupLogFolder ("backupLogFolder"),
        backupLogName ("backupLogName"),

        //backup settings
        backupName ("backupName");

        public final String defaultKey;

        globalKeys(String defaultKey) {
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
            logError(Logger,"No Core Global Configuration exists. Creating default Core Config file file.");
            try {

                if(createDirs(userGlobalConfigPath)){
                    logInfo(Logger,"Verifying default configuration directory for Global Configuration file");
                }else{
                    logError(Logger,"Failed to create default configuration directory for Global Configuration file!");
                }

                InputStream defaultConfig = getResourceAsStream(internalGlobalConfigPath);

                if (defaultConfig != null) {
                    Files.copy(defaultConfig, userGlobalConfigPath);
                    defaultConfig.close();
                }else {
                    logFatalExit(Logger, "Could not create the Global Configuration file!");
                }              logInfo(Logger," Global Configuration file was created :D");
                logFatalExit(Logger,"Please update it with your values!");
            }catch (IOException e) {
                //todo use Initialization failsafe
                logFatalExitE(Logger,e, "Could not create the Global Configuration file!");
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
        String key = globalKeys.backupLogFolder.defaultKey;
        return loadGetter(key,getLogFolder());
    }
    public String getSyncLogFolder(){
        //defaults to logFolder
        String key = globalKeys.syncLogFolder.defaultKey;
        return loadGetter(key,getLogFolder());
    }

    public String getSyncLogName(){
        String key = globalKeys.syncLogName.defaultKey;
        return loadGetter(key);
    }

    public String getBackupLogName(){
        String key = globalKeys.backupLogName.defaultKey;
        return loadGetter(key);
    }
    //--BACKUPS--

    public String getBackupName(){
        String key = globalKeys.backupName.defaultKey;
        return loadGetter(key);
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
