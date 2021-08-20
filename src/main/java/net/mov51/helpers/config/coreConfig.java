package net.mov51.helpers.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import static net.mov51.helpers.config.yamlHelper.getMap;
import static net.mov51.helpers.fileHelper.copyFromStream;
import static net.mov51.helpers.fileHelper.createDirs;
import static net.mov51.helpers.logHelper.*;

public class coreConfig {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("coreConfigBuilder");

    private static final Path userCoreConfigPath = Paths.get("config" + File.separator + "coreConfig.yml");
    private static final String internalCoreConfigPath = "/defaultCoreConfig.yml";

    private Map<String, Object> configMap;

    //create Singleton, there should only ever be one instance of the core config file for the duration of the program run
    //this class will also handle the initialization of the core config file

    private static final coreConfig INSTANCE = new coreConfig();

    private enum coreKeys {
        APIkey ("APIkey",true),
        serverUUID ("serverUUID",true),
        panelURL ("panelURL",true);

        public final String defaultKey;

        coreKeys(String defaultKey,boolean required) {
            this.defaultKey = defaultKey;
        }
    }

    private coreConfig() {

        if(userCoreConfigPath.toFile().exists()) {
            configMap = getMap(userCoreConfigPath);
        }else{
            //creating default core config file
            logError(Logger,"No Core Config file exists. Creating default Core Config file file.");
            if(createDirs(userCoreConfigPath)){
                logInfo(Logger,"Verifying default configuration directory for Core Config file");
            }
            if (copyFromStream(internalCoreConfigPath,userCoreConfigPath)) {
                logInfo(Logger," Core Config file was created :D");
                logFatalExit(Logger,"Please update it with your values!");
            }
        }

    }

    public static coreConfig getInstance() {
        return INSTANCE;
    }

    public String getPterodactylAPIkey(){
        return loadGetter(coreKeys.APIkey);
    }

    public String getPterodactylPanelURL(){
        return loadGetter(coreKeys.panelURL);
    }

    public String getPterodactylServerUUID(){
        return loadGetter(coreKeys.serverUUID);
    }

    private String loadGetter(coreKeys key){
        if(configMap.containsKey(key.defaultKey))
            return configMap.get(key.defaultKey).toString();
        logFatal(Logger,"Could not load key " + key + " from coreConfig!");
        return "";
    }

}
