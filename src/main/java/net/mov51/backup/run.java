package net.mov51.backup;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.archiveHelper.archive;
import static net.mov51.helpers.config.backupGetters.*;
import static net.mov51.rsync.execute.sync;

public class run {
    public static void StartBackup(String name){
        String fileName = "backupConfig/name/name";
        sendCommand(getStartCommand(fileName));
        sync(getSyncSource(fileName),getSyncDestination(fileName),true);
        archive(getBackupSource(fileName),getBackupName(fileName));
        sendCommand(getFinishCommand(fileName));
    }
}
