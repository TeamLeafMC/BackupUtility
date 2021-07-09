package net.mov51;

import net.mov51.helpers.config.coreConfigBuilder;
import net.mov51.helpers.config.globalConfigBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import static net.mov51.backup.search.searchForBackups;

public class Main {

    public static final String BackupConfigurationDirectory = "backupConfig";

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("mainLogger");
    public static final globalConfigBuilder globalConfig = globalConfigBuilder.getInstance();
    public static final coreConfigBuilder coreConfig = coreConfigBuilder.getInstance();

    public static void main(String[] args){

        Configurator.setRootLevel(Level.INFO);

        searchForBackups();
    }
}
