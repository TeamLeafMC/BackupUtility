package net.mov51.helpers.config;

import java.util.Objects;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.yamlHelper.*;

public class coreGetters {

    public static final String keyDefaultLogFolder = "logFolder";
    public static final String keyDefaultSyncLogName = "SyncLogName";

    //Get default core private values

    public static String getCorePanelURL(){
        return Objects.requireNonNull(getCoreFromKey(keyDefaultPanelURL));
    }

    public static String getCoreServerUUID(){
        return Objects.requireNonNull(getCoreFromKey(keyDefaultSeverUUID));

    }

    public static String getCoreAPIkey(){
        return Objects.requireNonNull(getCoreFromKey(keyDefaultAPIkey));
    }

    public static String getCoreLogFolder(){
        return Objects.requireNonNull(getLogCoreFromKey(keyDefaultLogFolder));
    }

    public static String getCoreSyncFileName(){
        return Objects.requireNonNull(getLogCoreFromKey(keyDefaultSyncLogName));
    }



}