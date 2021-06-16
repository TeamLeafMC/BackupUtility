package net.mov51.helpers.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static net.mov51.helpers.config.yamlHelper.getFromKey;

public class configHelper {

    private static final Path userConfigFolder = Paths.get("Config" + File.separator);

    public static final Path userCoreConfigPath = Paths.get(userConfigFolder+  File.separator +"coreConfig.yml");
    public static final Path userCoreLogConfigFile = Paths.get(userConfigFolder+ File.separator + "logConfig.yml");

    public static final String defaultCoreConfigFile = "/defaultConfig.yml";
    public static final String defaultLogConfigFile = "/defaultBackupConfig.yml";


    public static boolean initiateConfig(String internalConfig, Path outputConfig, boolean Validate, String name){


        if(!userConfigFolder.toFile().isDirectory()){
            if(userCoreConfigPath.toFile().mkdir())
                System.out.println("config folder created!");
        }

        try (InputStream defaultConfig = configHelper.class.getResourceAsStream(internalConfig)) {
            if(defaultConfig != null){
                if(outputConfig.toFile().exists()){
                    if(Validate){
                        System.out.println("Checking Core Config for invalid/default entries...");
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
                                if(value.equals(getFromKey(outputConfig,key))){
                                    //todo change to error logger
                                    System.out.println(key + " is the default Config value for " + name + "!");
                                    System.out.println("Please update the " + name + " file with your information!");
                                    //todo add link to wiki page
                                    System.exit(1);
                                }else{
                                    //todo log as info
                                    System.out.println("-- Config value " + key + " exists in " + name + " and has been changed from the default value.");
                                }
                            });
                        } catch (Exception e) {
                            //todo change to error logger
                            e.printStackTrace();
                        }
                        System.out.println(name + "exists and contains non-default entries");
                    }else{
                        System.out.println("Core Config file exists.");
                    }

                }else{
                    //todo change to error logger
                    System.out.println(name + " file does not exist. Creating Default " + name + "file.");
                    Files.copy(defaultConfig, outputConfig);
                    System.out.println(name + "file created :D");
                    //todo change to error logger
                    System.out.println("Please update it with your values!");
                    //todo add link to wiki page
                    return true;
                }
                return false;
            }else{
                //todo change to error logger
                System.out.println("Default " + name + " was null...");
                return true;
            }

        }catch(Exception e){
            //todo change to error logger
            System.out.println(name + " file could not be tested!");
            e.printStackTrace();
            return true;
        }
    }
}
