package net.mov51.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;

import static net.mov51.helpers.logHelper.*;

public class fileHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("File_Logger");

    public static boolean createDirs(File filePath){
        File parent = filePath.getParentFile();
        if(!parent.exists()){
            try{
                if(parent.mkdirs()){
                    logInfo(Logger,"Directory " + parent + " created!");
                    return true;
                }
            }catch (Exception e){
                logError(Logger,"Couldn't create directory " + parent);
                return false;
            }
        }
        return true;
    }

    //Path Overload
    public static boolean createDirs(Path filePath){
        return createDirs(String.valueOf(filePath));
    }

    //String Overload
    public static boolean createDirs(String filePath){
        File targetFile = new File(filePath);
        return createDirs(targetFile);
    }

}
