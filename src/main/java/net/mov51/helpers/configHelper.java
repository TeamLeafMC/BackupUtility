package net.mov51.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class configHelper {
    public static final Path userConfigFile = Paths.get("coreConfig.yml");


    public static boolean initiateCoreConfig(){

        try (InputStream defaultConfig = configHelper.class.getResourceAsStream("/defaultConfig.yml")) {
            if(defaultConfig != null){
                if(userConfigFile.toFile().exists()){
                    System.out.println("Core Config file exists.");
                    System.out.println("Core Config reads as:");
                    System.out.println("--Start Core Config--\n");
                    try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(userConfigFile)))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    System.out.println("\n--End Core Config--");
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
