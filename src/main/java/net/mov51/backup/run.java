package net.mov51.backup;

import net.mov51.helpers.config.backupConfigBuilder;

import java.nio.file.Path;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.archiveHelper.archive;
import static net.mov51.rsync.execute.sync;

public class run {
    public static void StartBackup(Path path){

        //todo change to info logger
        System.out.println("---------------------");
        System.out.println("Running backup " + path.getFileName());

        //getting backup config object
        backupConfigBuilder config = new backupConfigBuilder(path.toString());

        //sending command
        //todo change to command array processor
        sendCommand(config.startCommand, config);
        //starting rsync
        sync(config.syncSource,config.syncDestination,config.syncLogName,true);
        //starting archive
        archive(config.backupSource, config.backupName, config.backupDestination);
        //sending command
        //todo change to command array processor
        sendCommand(config.finishCommand, config);
        //todo change to info logger
        System.out.println("Backup " + path.getFileName() + " complete!");
        System.out.println("---------------------");

    }
}
