package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.io.File;
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

public class globalConfigBuilder {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("backupConfigLogger");

    private static final Path userGlobalConfigPath = Paths.get("config" + File.separator + "globalConfig.yml");
    private static final String internalGlobalConfigPath = "/defaultGlobalConfig.yml";

    public String logFolder;
    public String syncLogName;
    //todo if no then no log
    public String backupLogName;

    public String syncSource;
    public String SyncDestination;

    //todo if no then use sync destination
    public String backupSource;
    public String backupDestination;
    public String backupName;

    //todo need to build array command processor
    public String startCommand;
    public String stopCommand;

    public String logFolderDefaultKey = "logFolder";
    public String syncLogNameDefaultKey = "syncLogName";
    public String backupLogNameDefaultKey = "backupLogName";

    public String syncSourceDefaultKey = "syncSource";
    public String syncDestinationDefaultKey = "syncDestination";

    public String backupSourceDefaultKey = "backupSource";
    public String backupDestinationDefaultKey = "backupDestination";
    public String backupNameDefaultKey = "backupName";

    public String startCommandsDefaultKey = "startCommand";
    public String finishCommandsDefaultKey = "finishCommand";

    private static Map<String, Object> ConfigMap;

    private static final globalConfigBuilder INSTANCE = new globalConfigBuilder();

    public static globalConfigBuilder getInstance() {
        return INSTANCE;
    }

    private globalConfigBuilder(){
        if(userGlobalConfigPath.toFile().exists()){
            try{
                ConfigMap = getMap(userGlobalConfigPath);

                logFolder = loadValue(logFolderDefaultKey);
                syncLogName = loadValue(syncLogNameDefaultKey);
                backupLogName = loadValue(backupLogNameDefaultKey);

                syncSource = loadValue(syncSourceDefaultKey);
                SyncDestination = loadValue(syncDestinationDefaultKey);

                backupDestination = loadValue(backupSourceDefaultKey);
                backupSource = loadValue(backupDestinationDefaultKey);
                backupName = loadValue(backupNameDefaultKey);

                startCommand = loadValue(startCommandsDefaultKey);
                stopCommand = loadValue(finishCommandsDefaultKey);

            }catch (Exception e){
                logFatalExitE(Logger,e,"Couldn't load the Global Configuration file!");
            }
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
            }catch (Exception e) {
                logFatalExitE(Logger,e, "Could not create the Global Configuration file!");
            }
        }

    }

    private static String loadValue(String key){
        //todo do redundancy logic for default config
        return ConfigMap.get(key).toString();
    }
}
