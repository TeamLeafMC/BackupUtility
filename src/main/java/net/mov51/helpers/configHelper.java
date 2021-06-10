package net.mov51.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class configHelper {
    public static Path userConfigFile = Paths.get("config.yml");


    public static boolean initiateConfig(){

        try (InputStream defaultConfig = configHelper.class.getResourceAsStream("/defaultConfig.yml")) {
            if(defaultConfig != null){
                if(userConfigFile.toFile().exists()){
                    System.out.println("config file exists.");
                    System.out.println("config reads as:");
                    System.out.println("--Start Config--\n");
                    try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(userConfigFile)))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    System.out.println("\n--End Config--");
                }else{
                    //todo change to error logger
                    System.out.println("config file does not exist. Creating default config.");
                    Files.copy(defaultConfig, userConfigFile);
                    System.out.println("config file created");
                    //todo change to error logger
                    System.out.println("Please update it with your values!");
                    return false;
                }
                return true;
            }else{
                //todo change to error logger
                System.out.println("default config was null...");
                return false;
            }

        }catch(Exception e){
            //todo change to error logger
            System.out.println("config file could not be tested!");
            e.printStackTrace();
            return false;
        }

    }
}
