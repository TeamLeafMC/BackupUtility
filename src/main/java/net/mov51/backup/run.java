package net.mov51.backup;

import net.mov51.helpers.config.backupConfig;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.archiveHelper.archive;
import static net.mov51.helpers.logHelper.logInfo;
import static net.mov51.rsync.execute.sync;

public class run {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("runBackupLogger");

    public static void StartBackup(Path path){
        
        logInfo(Logger,"---------------------");
        logInfo(Logger,"Running backup " + path.getFileName());

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
        logInfo(Logger,"Backup " + path.getFileName() + " complete!");
        logInfo(Logger,"---------------------");

    }
}
