package net.mov51.rsync;

import com.github.fracpete.processoutput4j.output.CollectingProcessOutput;
import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput;
import com.github.fracpete.rsync4j.RSync;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.mov51.helpers.config.getters.*;
import static net.mov51.helpers.dateHelper.getFileSafeDate;

public class execute {
    public static void sync(String source, String destination,Boolean recursive){

        Path userLogPath = Paths.get(getLogFolder());

        if(!userLogPath.toFile().isDirectory()){
            if(userLogPath.toFile().mkdir())
                System.out.println("config folder created!");
        }

        RSync rsync = new RSync()
        .source(source + "/")
        .destination(destination)
        .recursive(recursive)
        .verbose(true)
        .wholeFile(true)
        .archive(true)
        .logFile(getLogFolder() + "/" + getSyncFileName() +  getFileSafeDate() + ".txt");

        try {
            ConsoleOutputProcessOutput output = new ConsoleOutputProcessOutput();
            output.monitor(rsync.builder());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
