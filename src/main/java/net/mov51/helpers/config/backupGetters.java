package net.mov51.helpers.config;

import java.util.Objects;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.getters.keyDefaultLogFolder;
import static net.mov51.helpers.config.getters.keyDefaultSyncLogName;
import static net.mov51.helpers.config.yamlHelper.getValue;

public class backupGetters {

    public static final String keyDefaultStartCommand = "startCommand";
    public static final String keyDefaultFinishCommand = "finishCommand";
    public static final String keyDefaultSyncSource = "syncSource";
    public static final String keyDefaultSyncDestination = "syncDestination";
    public static final String keyDefaultBackupSource = "backupSource";
    public static final String keyDefaultBackupDestination = "backupDestination";
    public static final String keyDefaultBackupName = "backupName";

    //--Pre-made getters with path options--

    public static String getPanelURL(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-core.yml")).get(keyDefaultPanelURL).toString();
    }

    public static String getServerUUID(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-core.yml")).get(keyDefaultSeverUUID).toString();

    }

    public static String getAPIkey(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-core.yml")).get(keyDefaultAPIkey).toString();
    }

    public static String getLogFolder(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultLogFolder).toString();
    }

    public static String getSyncFileName(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultSyncLogName).toString();
    }

    public static String getStartCommand(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultStartCommand).toString();
    }

    public static String getFinishCommand(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultFinishCommand).toString();
    }

    public static String getSyncSource(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultSyncSource).toString();
    }

    public static String getSyncDestination(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultSyncDestination).toString();
    }

    public static String getBackupSource(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultBackupSource).toString();
    }

    public static String getBackupDestination(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultBackupDestination).toString();
    }

    public static String getBackupName(String pathToConfig){
        return Objects.requireNonNull(getValue(pathToConfig + "-options.yml")).get(keyDefaultBackupName).toString();
    }

}
