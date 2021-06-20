package net.mov51;

import net.mov51.helpers.config.configHelper;

import static net.mov51.backup.run.StartBackup;
import static net.mov51.backup.search.searchForBackups;
import static net.mov51.helpers.config.configHelper.*;

public class Main {

    public static final String BackupConfigurationDirectory = "backupConfig";

    public static void main(String[] args){

        //If config is invalid or needs to be recreated, exit and let the config helper handle the error code
        if (configHelper.initiateConfig(defaultCoreConfigFile, userCoreConfigPath, true, "Core Config")){
            System.exit(1);
        }
        if (configHelper.initiateConfig(defaultLogConfigFile, userCoreLogConfigFile, false, "Logging Config")){
            System.exit(1);
        }

        searchForBackups();

    }
}
