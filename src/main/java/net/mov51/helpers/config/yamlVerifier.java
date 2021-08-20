package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;

import java.util.Map;

import static net.mov51.helpers.logHelper.logFatalExit;
import static net.mov51.helpers.logHelper.logInfo;

public class yamlVerifier {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("YAML_Verifier");

    public static boolean coreVerify(Map<String, Object> map){
        //todo verify the passed core config file
        // - contains all keys ☑
        // - none of the values are default
        // - the Pterodactyl API endpoints can be reached
        for (coreConfig.Keys key : coreConfig.Keys.values()) {
            logInfo(Logger,"Verifying key " + key.defaultKey + " in CoreConfig!");
            if(key.required && !map.containsKey(key.defaultKey)){
                logFatalExit(Logger,"Required key " + key.defaultKey + " does not not exist in CoreConfig!");
                return false;
            }
        }
        return true;
    }

    public static boolean backupVerify(Map<String, Object> map){
        //todo verify the passed backup config
        // - contains all keys ☑
        // - all folders exist
        for (backupConfig.Keys key : backupConfig.Keys.values()) {
            logInfo(Logger,"Verifying key " + key.defaultKey + " in BackupConfig!");
            if(key.required && !map.containsKey(key.defaultKey)){
                logFatalExit(Logger,"Required key " + key.defaultKey + " does not not exist in BackupConfig!");
                return false;
            }
        }
        return false;
    }

    public static boolean globalVerify(Map<String, Object> map){
        //todo verify the passed log config
        // - contains all keys ☑
        // - all folders exist
        for (globalConfig.Keys key : globalConfig.Keys.values()) {
            logInfo(Logger,"Verifying key " + key.defaultKey + " in GlobalConfig!");
            if(key.required && !map.containsKey(key.defaultKey)){
                logFatalExit(Logger,"Required key " + key.defaultKey + " does not not exist in GlobalConfig!");
                return false;
            }
        }
        return false;
    }
}
