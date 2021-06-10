package net.mov51;

import net.mov51.helpers.configHelper;

import static net.mov51.helpers.PterodactylApiHelper.sendCommand;

public class Main {

    public static void main(String[] args){

        //If config is invalid or needs to be recreated, exit and let the config helper handle the error code
        if (!configHelper.initiateCoreConfig()){
            System.exit(1);
        }

        //test code to send command with ptero api
        //todo change this asap
        sendCommand("say test");
    }
}
