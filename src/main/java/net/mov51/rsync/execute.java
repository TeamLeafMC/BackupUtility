package net.mov51.rsync;

import com.github.fracpete.processoutput4j.output.CollectingProcessOutput;
import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput;
import com.github.fracpete.rsync4j.RSync;

import java.text.SimpleDateFormat;
import java.util.Date;

public class execute {
    public static void sync(String source, String destination,Boolean recursive){
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;

        String sDate = dateFormat.format(date);

        RSync rsync = new RSync()
        .source(source + "/")
        .destination(destination)
        .recursive(recursive)
        .verbose(true)
        .wholeFile(true)
        .archive(true)
        .logFile("syncLog__" + sDate + ".txt");

        try {
            ConsoleOutputProcessOutput output = new ConsoleOutputProcessOutput();
            output.monitor(rsync.builder());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
