package net.mov51.helpers.config;

import java.nio.file.Path;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.coreGetters.keyDefaultLogFolder;
import static net.mov51.helpers.config.coreGetters.keyDefaultSyncLogName;
import static net.mov51.helpers.config.yamlHelper.SafeGetFromKey;

public class backupGetters {

    public static final String keyDefaultStartCommand = "startCommand";
    public static final String keyDefaultFinishCommand = "finishCommand";
    public static final String keyDefaultSyncSource = "syncSource";
    public static final String keyDefaultSyncDestination = "syncDestination";
    public static final String keyDefaultSyncVerbosity = "SyncVerbose";
    public static final String keyDefaultBackupSource = "backupSource";
    public static final String keyDefaultBackupDestination = "backupDestination";
    public static final String keyDefaultBackupName = "backupName";

    //--Pre-made getters with path options--

    //Private core config values

    public static String getPanelURL(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultPanelURL);
    }

    public static String getServerUUID(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultSeverUUID);

    }

    public static String getAPIkey(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultAPIkey);
    }

    public static String getLogFolder(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultLogFolder);
    }

    public static String getSyncFileName(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultSyncLogName);
    }

    //General backup settings

    public static String getStartCommand(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultStartCommand);
    }

    public static String getFinishCommand(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultFinishCommand);
    }

    public static String getSyncSource(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultSyncSource);
    }

    public static String getSyncDestination(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultSyncDestination);
    }

    public static Boolean getSyncVerbosity(Path pathToConfig){
        return Boolean.valueOf(SafeGetFromKey(pathToConfig,keyDefaultSyncVerbosity));
    }

    public static String getBackupSource(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultBackupSource);
    }

    public static String getBackupDestination(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultBackupDestination);
    }

    public static String getBackupName(Path pathToConfig){
        return SafeGetFromKey(pathToConfig,keyDefaultBackupName);
    }

}
