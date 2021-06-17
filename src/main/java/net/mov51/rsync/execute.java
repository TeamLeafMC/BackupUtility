package net.mov51.rsync;

import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput;
import com.github.fracpete.rsync4j.RSync;

import java.nio.file.Path;
import java.nio.file.Paths;

import static net.mov51.helpers.config.coreGetters.*;
import static net.mov51.helpers.dateHelper.getFileSafeDate;

public class execute {
    public static void sync(String source, String destination,Boolean recursive){

        Path userLogPath = Paths.get(getCoreLogFolder());
        Path destinationPath = Paths.get(destination);

        if(!userLogPath.toFile().isDirectory()){
            if(userLogPath.toFile().mkdir())
                System.out.println("config folder created!");
        }

        if(!destinationPath.toFile().exists()){
            if(destinationPath.toFile().mkdirs()){
                System.out.println("Sync Output Directory " + destinationPath.toFile().getName() + " created!");
            }
        }

        RSync rsync = new RSync()
        .source(source + "/")
        .destination(destination)
        .recursive(recursive)
        .verbose(true)
        .wholeFile(true)
        .archive(true)
        .logFile(getCoreLogFolder() + "/" + getCoreSyncFileName() +  getFileSafeDate() + ".txt");

        try {
            ConsoleOutputProcessOutput output = new ConsoleOutputProcessOutput();
            output.monitor(rsync.builder());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
