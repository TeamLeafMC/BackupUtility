package net.mov51.helpers.config;

import java.util.Objects;

import static net.mov51.helpers.PterodactylApiHelper.*;
import static net.mov51.helpers.config.yamlHelper.getValue;
import static net.mov51.helpers.config.configHelper.*;

public class getters {

    public static final String keyDefaultLogFolder = "logFolder";
    public static final String keyDefaultSyncLogName = "SyncLogName";

    //---Pre-made getters---

    public static String getPanelURL(){
        return Objects.requireNonNull(getValue(userCoreConfigFile)).get(keyDefaultPanelURL).toString();
    }

    public static String getServerUUID(){
        return Objects.requireNonNull(getValue(userCoreConfigFile)).get(keyDefaultSeverUUID).toString();

    }

    public static String getAPIkey(){
        return Objects.requireNonNull(getValue(userCoreConfigFile)).get(keyDefaultAPIkey).toString();
    }

    public static String getLogFolder(){
        return Objects.requireNonNull(getValue(userLogConfigFile)).get(keyDefaultLogFolder).toString();
    }

    public static String getSyncFileName(){
        return Objects.requireNonNull(getValue(userLogConfigFile)).get(keyDefaultSyncLogName).toString();
    }



}