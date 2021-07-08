package net.mov51.helpers.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.SafeGetFromKey;
import static net.mov51.helpers.fileHelper.createDirs;
import static net.mov51.helpers.logHelper.*;

public class configHelper {

    private static final Path userConfigFolder = Paths.get("Config" + File.separator);

    public static final Path userCoreConfigPath = Paths.get(userConfigFolder+  File.separator +"coreConfig.yml");
    public static final Path userCoreLogConfigFile = Paths.get(userConfigFolder+ File.separator + "logConfig.yml");

    public static final String defaultCoreConfigFile = "/defaultConfig.yml";
    public static final String defaultLogConfigFile = "/defaultBackupConfig.yml";

    private static final Logger Logger = LogManager.getLogger("Config_logger");


    public static boolean initiateConfig(String internalConfig, Path outputConfig, boolean Validate, String name){
        //todo rebuild into initiate core configs
        // - clarify that it's just for core configs
        // - move utils used for core configs to separate methods to re-use for separated core configs

        try (InputStream defaultConfig = configHelper.class.getResourceAsStream(internalConfig)) {
            if(defaultConfig != null){
                if(outputConfig.toFile().exists()){
                    if(Validate){
                        logInfo(Logger,"Checking Core Config for invalid/default entries...");
                    /* The active coreConfig.yml should *only* have non-default entries.
                    It will only be used for things that are essential for the program
                    to work such as the api token and server URI.

                    Eventually individual backup cycles will have their
                    own core section that can define those independently, but there
                    will still need to be at least some non-default values in the
                    global configuration.  */
                        try {
                            Yaml yaml = new Yaml();
                            Map<String,Object> defaultConfigMap = yaml.load(defaultConfig);

                            defaultConfigMap.forEach((key, value) -> {
                                if(value.equals(SafeGetFromKey(outputConfig,key))){
                                    logError(Logger,key + " is the default Config value for " + name + "!");
                                    logError(Logger,"This is used as the fallback when no value is provided and needs to exist!");
                                    logFatalExit(Logger,"Please update the " + name + " file with your information!");
                                    //todo add link to wiki page
                                }else{
                                    logError(Logger,"-- Config value " + key + " exists in " + name + " and has been changed from the default value.");
                                    //todo do basic verification steps
                                }
                            });
                        } catch (Exception e) {
                            logFatalExitE(Logger,e,"Failed to load the " + name + " YAML file as a map!");
                        }
                        logInfo(Logger,name + "exists and contains non-default entries");
                    }else{
                        logInfo(Logger,"Core Config file exists.");
                    }

                }else{
                    logError(Logger,name + " file does not exist. Creating Default " + name + "file.");
                    try {

                            if(createDirs(outputConfig)){
                                logInfo(Logger,"Verifying default configuration directory for " + name);
                            }else{
                                logError(Logger,"Failed to create default configuration directory for " + name + "!");
                            }

                        Files.copy(defaultConfig, outputConfig);
                        logInfo(Logger,name + "file created :D");
                        logFatalExit(Logger,"Please update it with your values!");
                    }catch (Exception e) {
                        logFatalExitE(Logger,e, "Could not create " + name + "file!");
                    }
                }
                return false;
            }else{
                logFatal(Logger,"Default " + name + " was null...");
                return true;
            }

        }catch(Exception e){
            logFatalE(Logger,e,name + " file could not be tested!");
            return true;
        }
    }
}
