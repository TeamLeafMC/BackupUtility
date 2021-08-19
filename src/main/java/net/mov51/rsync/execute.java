package net.mov51.rsync;

import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput;
import com.github.fracpete.rsync4j.RSync;
import net.mov51.helpers.config.backupConfig;
import org.apache.logging.log4j.LogManager;

import static net.mov51.helpers.logHelper.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.mov51.helpers.dateHelper.getFileSafeDate;

public class execute {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("Rsync_Logger");

    public static void sync(backupConfig config){

        Path destinationPath = Paths.get(config.getSyncDestination());
        File destinationFile = destinationPath.toFile();

        //creating the output for sync tasks
        if(!destinationFile.exists()){
            if(destinationFile.mkdirs()){
                logInfo(Logger,"Sync Output Directory " + destinationPath.toFile().getName() + " created!");
            }else{
                logError(Logger,"Sync Output Directory " + destinationPath.toFile().getName() + " could not be created!");
            }
        }

        //Creating new rsync task
        RSync rsync = new RSync()
        .source(config.getSyncSource() + "/")
                //adding new destination
        .destination(config.getSyncDestination())
                //setting recursive
        .recursive(true)
                //setting the verbose state from config file
        .verbose(true)
                //transferring the whole file and preserving timestamps.
                //This allows for a more stable sync over sshfs connections
        .wholeFile(true)
        .archive(true);

        if(!config.getSyncLogName().isEmpty()){
            //defining the log output and adding the FileSafe date
            //todo add placeholders
            rsync.logFile(config.getSyncLogFolder() + "/" + config.getSyncLogName() +  getFileSafeDate() + ".txt");
        }

        try {
            //displaying console output in primary log
            ConsoleOutputProcessOutput output = new ConsoleOutputProcessOutput();
            //Start rsync task
            logInfo(Logger,"Rsync task starting from " + destinationFile.getName() + " to " + Paths.get(config.getSyncSource()).toFile().getName());
            output.monitor(rsync.builder());

        } catch (Exception e) {
            //todo use Rsync failsafe
            logErrorE(Logger,e,"Rsync task to location " + config.getSyncDestination() + " failed!");
        }


    }
}
