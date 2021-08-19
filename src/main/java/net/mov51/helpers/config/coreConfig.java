package net.mov51.helpers.config;

import java.io.File;
import java.io.IOException;
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
    private static final String internalCoreConfigPath = "/defaultCoreConfig.yml";

    private Map<String, Object> configMap;

    //create Singleton, there should only ever be one instance of the core config file for the duration of the program run
    //this class will also handle the initialization of the core config file

    private static final coreConfig INSTANCE = new coreConfig();

    private enum coreKeys {
        APIkey ("APIkey"),
        serverUUID ("serverUUID"),
        panelURL ("panelURL");

        public final String defaultKey;

        coreKeys(String defaultKey) {
            this.defaultKey = defaultKey;
        }
    }

    private coreConfig() {

        if(userCoreConfigPath.toFile().exists()) {
            configMap = getMap(userCoreConfigPath);
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
            }catch (IOException e) {
                //todo use initialization failsafe
                logFatalExitE(Logger,e, "Could not create the Core Config file!");
            }
        }

    }

    public static coreConfig getInstance() {
        return INSTANCE;
    }

    public String getPterodactylAPIkey(){
        String key = coreKeys.APIkey.defaultKey;
        return loadGetter(key);
    }

    public String getPterodactylPanelURL(){
        String key = coreKeys.panelURL.defaultKey;
        return loadGetter(key);
    }

    public String getPterodactylServerUUID(){
        String key = coreKeys.serverUUID.defaultKey;
        return loadGetter(key);
    }

    private String loadGetter(String key){
        if(configMap.containsKey(key)){
            return configMap.get(key).toString();
        }else{
            logFatal(Logger,"Could not lod key " + key + " from coreConfig!");
            return null;
        }

    }

}
