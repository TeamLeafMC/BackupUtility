package net.mov51.backup;

import net.mov51.helpers.config.backupConfig;

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
        backupConfig config = new backupConfig(path.toString());

        //sending command
        //todo change to command array processor
        sendCommand(config.getStartCommand(), config);
        //starting rsync
        sync(config);
        //starting archive
        archive(config.getBackupSource(), config.getBackupName(), config.getBackupDestination());
        //sending command
        //todo change to command array processor
        sendCommand(config.getFinishCommand(), config);
        //todo change to info logger
        System.out.println("Backup " + path.getFileName() + " complete!");
        System.out.println("---------------------");

    }
}
