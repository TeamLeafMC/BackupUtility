package net.mov51.backup;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static net.mov51.Main.BackupConfigurationDirectory;
import static net.mov51.backup.run.StartBackup;

public class search {
    public static void searchForBackups() {
        //search backupConfig Dir for custom backup configurations
        File folder = new File(BackupConfigurationDirectory);
        //for each fileEntry
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                System.out.println("skipping invalid subdirectory " + fileEntry.getName());
            } else {
                //if not directory
                System.out.println("\n");
                //print the name
                //todo change to info logger
                System.out.println(fileEntry.getName());
                //run the backup with the config file
                StartBackup(fileEntry.toPath());
            }
        }

    }
}
