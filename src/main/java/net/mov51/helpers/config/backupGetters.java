package net.mov51.helpers.config;

import java.nio.file.Path;
import java.util.Objects;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.coreGetters.keyDefaultLogFolder;
import static net.mov51.helpers.config.coreGetters.keyDefaultSyncLogName;
import static net.mov51.helpers.config.yamlHelper.getFromKey;

public class backupGetters {

    public static final String keyDefaultStartCommand = "startCommand";
    public static final String keyDefaultFinishCommand = "finishCommand";
    public static final String keyDefaultSyncSource = "syncSource";
    public static final String keyDefaultSyncDestination = "syncDestination";
    public static final String keyDefaultBackupSource = "backupSource";
    public static final String keyDefaultBackupDestination = "backupDestination";
    public static final String keyDefaultBackupName = "backupName";

    //--Pre-made getters with path options--

    public static String getPanelURL(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultPanelURL));
    }

    public static String getServerUUID(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultSeverUUID));

    }

    public static String getAPIkey(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultAPIkey));
    }

    public static String getLogFolder(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultLogFolder));
    }

    public static String getSyncFileName(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultSyncLogName));
    }

    public static String getStartCommand(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultStartCommand));
    }

    public static String getFinishCommand(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultFinishCommand));
    }

    public static String getSyncSource(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultSyncSource));
    }

    public static String getSyncDestination(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultSyncDestination));
    }

    public static String getBackupSource(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultBackupSource));
    }

    public static String getBackupDestination(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultBackupDestination));
    }

    public static String getBackupName(Path pathToConfig){
        return Objects.requireNonNull(getFromKey(pathToConfig,keyDefaultBackupName));
    }

}
