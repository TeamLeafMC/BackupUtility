package net.mov51.rsync;

import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput;
import com.github.fracpete.rsync4j.RSync;

import java.nio.file.Path;
import java.nio.file.Paths;

import static net.mov51.helpers.config.coreGetters.*;
import static net.mov51.helpers.dateHelper.getFileSafeDate;

public class execute {
    public static void sync(String source, String logName, String destination,Boolean verbose){

        Path userLogPath = Paths.get(getCoreLogFolder());
        Path destinationPath = Paths.get(destination);

        //creating user defined sync log directory
        if(!userLogPath.toFile().isDirectory()){
            if(userLogPath.toFile().mkdirs())
                //todo change to info logger
                System.out.println("config folder created!");
        }

        //creating the output for sync tasks
        if(!destinationPath.toFile().exists()){
            if(destinationPath.toFile().mkdirs()){
                //todo change to info logger
                System.out.println("Sync Output Directory " + destinationPath.toFile().getName() + " created!");
            }
        }

        //Creating new rsync task
        RSync rsync = new RSync()
        .source(source + "/")
                //adding new destination
        .destination(destination)
                //setting recursive
        .recursive(true)
                //setting the verbose state from config file
        .verbose(verbose)
                //transferring the whole file and preserving timestamps.
                //This allows for a more stable sync over sshfs connections
        .wholeFile(true)
        .archive(true)
                //defining the log output and adding the FileSafe date
                //todo add placeholders
        .logFile(getCoreLogFolder() + "/" + logName +  getFileSafeDate() + ".txt");

        try {
            //displaying console output in primary log
            //todo change to info logger
            ConsoleOutputProcessOutput output = new ConsoleOutputProcessOutput();
            //Start rsync task
            System.out.println("Rsync task starting from " + destinationPath.toFile().getName() + " to " + Paths.get(source).toFile().getName());
            output.monitor(rsync.builder());

        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
        }


    }
}
