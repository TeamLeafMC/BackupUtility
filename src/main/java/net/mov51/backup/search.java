package net.mov51.backup;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static net.mov51.backup.run.StartBackup;

public class search {
        public static void searchForBackups(){
            File folder = new File("backupConfig");
                    for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                    if (fileEntry.isDirectory()) {
                        System.out.println("skipping invalid subdirectory " + fileEntry.getName());
                    } else {
                        System.out.println("\n");
                        System.out.println(fileEntry.getName());
                        StartBackup(fileEntry.toPath());
                    }
                }

        }
}
