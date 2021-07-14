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

    protected String logNameDefaultKey = "logName";
    protected String logFolderDefaultKey = "logFolder";
    protected String backupLogFolderDefaultKey = "backupLogFolder";
    protected String syncLogFolderFolderDefaultKey = "syncLogFolder";
    protected String syncLogNameDefaultKey = "syncLogName";
    protected String backupLogNameDefaultKey = "backupLogName";

    private Map<String, Object> configMap;
    private static final globalConfig INSTANCE = new globalConfig();

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


    //log settings
    public String getLogName(){
        String key = INSTANCE.logNameDefaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getLogFolder(){
        String key = INSTANCE.logFolderDefaultKey;
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
        return null;
    }

    //--OPTIONAL--

    public String getBackupLogFolder(){
        String key = INSTANCE.backupLogFolderDefaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            return null;
        }
    }
    public String getSyncLogFolder(){
        String key = INSTANCE.syncLogFolderFolderDefaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getSyncLogName(){
        String key = INSTANCE.syncLogNameDefaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            return null;
        }
    }

    public String getBackupLogName(){
        String key = INSTANCE.backupLogNameDefaultKey;
        if(INSTANCE.configMap.containsKey(key)){
            return INSTANCE.configMap.get(key).toString();
        }else{
            return null;
        }
    }

}
