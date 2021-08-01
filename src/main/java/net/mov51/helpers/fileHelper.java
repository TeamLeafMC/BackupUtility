package net.mov51.helpers;

import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static net.mov51.helpers.logHelper.*;

public class fileHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("File_Logger");

    public static boolean createDirs(File filePath){
        File parent = filePath.getParentFile();
        if(!parent.exists()){
                if(parent.mkdirs()){
                    logInfo(Logger,"Directory " + parent + " created!");
                    return true;
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

    public static boolean isDirEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();
            }
        }

        return false;
    }

}
