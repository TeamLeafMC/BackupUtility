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

public class coreConfigBuilder {

    private static final Path userCoreConfigPath = Paths.get("config" + File.separator + "coreConfig.yml");
    private static final String internalCoreConfigPath = "/defaultBackupConfig.yml";

    public final String keyDefaultAPIkey = "key";
    public final String keyDefaultSeverUUID = "serverUUID";
    public final String keyDefaultPanelURL = "panelURL";

    public String apiKey;
    public String serverUUID;
    public String panelURL;

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("coreConfigBuilder");

    private final Map<String, Object> defaultConfigMap = getMap(userCoreConfigPath);

    //create Singleton, there should only ever be one instance of the core config file for the duration of the program run
    //this class will also handle the initialization of the core config file

    private static final coreConfigBuilder INSTANCE = new coreConfigBuilder();

    private coreConfigBuilder() {


        if(userCoreConfigPath.toFile().exists()) {
            //todo create and use core verification method
            //map used to test other core values for default settings
            apiKey = defaultConfigMap.get(keyDefaultAPIkey).toString();
            serverUUID = defaultConfigMap.get(keyDefaultSeverUUID).toString();
            panelURL = defaultConfigMap.get(keyDefaultPanelURL).toString();
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

    public String getCoreByKey(String key){
        return defaultConfigMap.get(key).toString();
    }

    public static coreConfigBuilder getInstance() {
        return INSTANCE;
    }




}
