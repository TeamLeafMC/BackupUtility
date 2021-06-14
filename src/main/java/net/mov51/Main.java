package net.mov51;

import net.mov51.helpers.config.configHelper;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;
import static net.mov51.helpers.config.configHelper.*;
import static net.mov51.rsync.execute.sync;

public class Main {

    public static void main(String[] args){

        //If config is invalid or needs to be recreated, exit and let the config helper handle the error code
        if (configHelper.initiateConfig(defaultCoreConfigFile, userCoreConfigFile, true, "Core Config")){
            System.exit(1);
        }
        if (configHelper.initiateConfig(defaultLogConfigFile, userLogConfigFile, false, "Logging Config")){
            System.exit(1);
        }

        //test code to send command with ptero api
        //todo change this asap
        sendCommand("say test");
        sync("test1","test2",true);
        sendCommand("say test complete");
    }
}
