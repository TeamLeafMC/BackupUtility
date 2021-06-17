package net.mov51.backup;

import java.nio.file.Path;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.archiveHelper.archive;
import static net.mov51.helpers.config.backupGetters.*;
import static net.mov51.rsync.execute.sync;

public class run {
    public static void StartBackup(Path path){

        System.out.println("---------------------");
        System.out.println("Running backup " + path.getFileName());
        sendCommand(getStartCommand(path));
        sync(getSyncSource(path),getSyncDestination(path),true);
        archive(getBackupSource(path),getBackupName(path),getBackupDestination(path));
        sendCommand(getFinishCommand(path));
        System.out.println("Backup " + path.getFileName() + " complete!");
        System.out.println("---------------------");

    }
}
