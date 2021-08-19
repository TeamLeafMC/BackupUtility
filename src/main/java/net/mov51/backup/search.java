package net.mov51.backup;

import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.Objects;

import static net.mov51.Main.BackupConfigurationDirectory;
import static net.mov51.backup.run.StartBackup;
import static net.mov51.helpers.logHelper.logInfo;

public class search {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("searchBackupLogger");

    public static void searchForBackups() {

        //search backupConfig Dir for custom backup configurations
        //todo if no backups configs exist, create a default file
        //todo verify all backups for valid information BEFORE running
        File folder = new File(BackupConfigurationDirectory);
        //for each fileEntry
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                logInfo(Logger,"skipping invalid subdirectory " + fileEntry.getName());
            } else {
                //run the backup with the config file
                StartBackup(fileEntry.toPath());
            }
        }

    }
}
