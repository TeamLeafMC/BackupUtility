package net.mov51.helpers.config;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;
import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.fileHelper.createDirs;
import static net.mov51.helpers.logHelper.*;
import static net.mov51.helpers.logHelper.logFatalExitE;

public class coreConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("coreConfigBuilder");

    private static final Path userCoreConfigPath = Paths.get("config" + File.separator + "coreConfig.yml");
    private static final String internalCoreConfigPath = "/defaultBackupConfig.yml";

    protected final String keyDefaultAPIkey = "key";
    protected final String keyDefaultSeverUUID = "serverUUID";
    protected final String keyDefaultPanelURL = "panelURL";

    private Map<String, Object> defaultConfigMap;

    //create Singleton, there should only ever be one instance of the core config file for the duration of the program run
    //this class will also handle the initialization of the core config file

    private static final coreConfig INSTANCE = new coreConfig();

    private coreConfig() {


        if(userCoreConfigPath.toFile().exists()) {
            defaultConfigMap = getMap(userCoreConfigPath);
        }
        else
        {
            //creating default core config file
            logError(Logger,"No Core Config file exists. Creating default Core Config file file.");
            try {

                if(createDirs(userCoreConfigPath)){
                    logInfo(Logger,"Verifying default configuration directory for Core Config file");
                }else{
                    logError(Logger,"Failed to create default configuration directory for Core Config file!");
                }

                InputStream defaultConfig = getResourceAsStream(internalCoreConfigPath);

                if (defaultConfig != null) {
                    Files.copy(defaultConfig, userCoreConfigPath);
                }else {
                    logFatalExit(Logger, "Could not create the Core Config file!");
                }              logInfo(Logger," Core Config file was created :D");
                logFatalExit(Logger,"Please update it with your values!");
            }catch (Exception e) {
                logFatalExitE(Logger,e, "Could not create the Core Config file!");
            }
        }

    }



    public static coreConfig getInstance() {
        return INSTANCE;
    }

    public static String getPterodactylAPIkey(){
        return INSTANCE.defaultConfigMap.get(INSTANCE.keyDefaultAPIkey).toString();
    }

    public static String getPterodactylPanelURL(){
        return INSTANCE.defaultConfigMap.get(INSTANCE.keyDefaultPanelURL).toString();
    }

    public static String getPterodactylServerUUID(){
        return INSTANCE.defaultConfigMap.get(INSTANCE.keyDefaultSeverUUID).toString();
    }

}
