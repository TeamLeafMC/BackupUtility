package net.mov51.helpers.config;

public class yamlVerifier {
    public static boolean coreVerify(){
        //todo verify the passed core config file
        // - contains all keys
        // - none of the values are default
        // - the Pterodactyl API endpoints can be reached
        return false;
    }

    public static boolean backupVerify(){
        //todo verify the passed backup config
        // - contains all keys
        // - all folders exist
        return false;
    }

    public static boolean logVerify(){
        //todo verify the passed log config
        // - contains all keys
        // - all folders exist
        return false;
    }
}
