package net.mov51.helpers;

import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;
import static net.mov51.helpers.logHelper.*;

public class fileHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("File_Logger");


    public static boolean createDirs(File filePath){
        File parent = filePath.getParentFile();
        boolean status = false;
        if(!parent.exists()){
                if(parent.mkdirs()){
                    logInfo(Logger,"Directory " + parent + " created!");
                    status = true;
                }else{
                    logError(Logger,"Failed to create " + parent + " directory!");
                }
        }else{
            status = true;
        }
        return status;
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

    public static boolean copyFromStream(String inputString, Path path){
        boolean status = false;
        try {
            InputStream inputStream = getResourceAsStream(inputString);
            if (inputStream != null) {
                Files.copy(inputStream, path);
                inputStream.close();
                if(path.toFile().exists()){
                    status = true;
                    logFatalExit(Logger, "Could not find the " + path.getFileName() + " file after creating it!");
                }
            } else {
                logFatalExit(Logger, "Could not create the " + path.getFileName() + " file do to the input stream being null!");
            }
        }catch (IOException e){
            //todo use Initialization failsafe
            logFatalExitE(Logger,e, "Could not create the " + path.getFileName() + " file!");
        }
        return status;
    }

    public static boolean isDirEmpty(Path path) throws IOException {
        if(path.toFile().exists()){
            if (Files.isDirectory(path)) {
                try (Stream<Path> entries = Files.list(path)) {
                    return !entries.findFirst().isPresent();
                }
            }
        }
        return false;
    }

}
