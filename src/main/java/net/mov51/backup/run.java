package net.mov51.backup;

import java.nio.file.Path;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.archiveHelper.archive;
import static net.mov51.helpers.config.backupGetters.*;
import static net.mov51.rsync.execute.sync;

public class run {
    public static void StartBackup(Path path){

        //todo change to info logger
        System.out.println("---------------------");
        System.out.println("Running backup " + path.getFileName());
        //sending command
        //todo change to command array processor
        sendCommand(getStartCommand(path));
        //starting rsync
        sync(getSyncSource(path),getSyncFileName(path), getSyncDestination(path),getSyncVerbosity(path));
        //starting archive
        archive(getBackupSource(path),getBackupName(path),getBackupDestination(path));
        //sending command
        //todo change to command array processor
        sendCommand(getFinishCommand(path));
        //todo change to info logger
        System.out.println("Backup " + path.getFileName() + " complete!");
        System.out.println("---------------------");

    }
}
