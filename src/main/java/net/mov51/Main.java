package net.mov51;

import net.mov51.helpers.config.coreConfig;
import net.mov51.helpers.config.globalConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static net.mov51.backup.search.searchForBackups;

public class Main {

    public static final String BackupConfigurationDirectory = "backupConfig";

    //initialize logger -- Remember mov, DO THIS FIRST!
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("mainLogger");

    //initialize static configs
    public static final globalConfig GlobalConfig = globalConfig.getInstance();
    public static final coreConfig CoreConfig = coreConfig.getInstance();

    public static void main(String[] args){

        PrintStream out = null;
        try {
            out = new PrintStream(
                    new FileOutputStream(GlobalConfig.getLogFolder() + "/" + GlobalConfig.getLogName() + ".log", true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);

        //set logger configurations
        //todo fully configure log4j
        Configurator.setRootLevel(Level.INFO);


        //start backup cycle
        searchForBackups();
    }
}
