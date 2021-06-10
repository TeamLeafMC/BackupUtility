package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static net.mov51.helpers.yamlHelper.getFromKey;

public class configHelper {
    public static final Path userConfigFile = Paths.get("coreConfig.yml");
    public static final String defaultConfigFile = "/defaultConfig.yml";


    public static boolean initiateCoreConfig(){

        try (InputStream defaultConfig = configHelper.class.getResourceAsStream(defaultConfigFile)) {
            if(defaultConfig != null){
                if(userConfigFile.toFile().exists()){
                    System.out.println("Core Config file exists.");
                    System.out.println("Checking Core Config for invalid entries...");
                    try {
                        Yaml yaml = new Yaml();
                        Map<String,Object> defaultConfigMap = yaml.load(defaultConfig);

                        defaultConfigMap.forEach((key, value) -> {
                            if(value.equals(getFromKey(key))){
                                //todo change to error logger
                                System.out.println(key + " is the default Core Config value!");
                                System.out.println("Please change the coreConfig.yml with your pane information!");
                                //todo add link to wiki page
                                System.exit(1);
                            }else{
                                //todo log as info
                                System.out.println("-- Core Config value " + key + " exists and has been changed from the default value.");
                            }
                        });
                    } catch (Exception e) {
                        //todo change to error logger
                        e.printStackTrace();
                    }
                    System.out.println("Core Config exists and contains non-default entries");

                }else{
                    //todo change to error logger
                    System.out.println("Core Config file does not exist. Creating Default Core Config file.");
                    Files.copy(defaultConfig, userConfigFile);
                    System.out.println("Core Config file created :D");
                    //todo change to error logger
                    System.out.println("Please update it with your values!");
                    //todo add link to wiki page
                    return false;
                }
                return true;
            }else{
                //todo change to error logger
                System.out.println("Default Core Config was null...");
                return false;
            }

        }catch(Exception e){
            //todo change to error logger
            System.out.println("Core Config file could not be tested!");
            e.printStackTrace();
            return false;
        }
    }
}
