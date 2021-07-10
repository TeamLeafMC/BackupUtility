package net.mov51.helpers;

import net.mov51.helpers.config.backupConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static net.mov51.helpers.logHelper.*;



public class PterodactylApiHelper {

    private static final Logger Logger = LogManager.getLogger("PterodactylAPI_Logger");

    /*
    todo Verify global core config and passed cycle configs
     -Verify panel domain resolution
     -verify that api key can retrieve a server list
      -might be able to do in tandem with the one below, but only if there are separate response codes
     -get server to verify that the requested server UUID is accessible by the apikey provided
      -https://docs.panel.gg/#get-a-specific-server
    */

    private static boolean sendCommandRequest(String command, backupConfig config){
        //todo receive uuid to send command to

        try {
            //build API url
            URL url = new URL(config.getPterodactylPanelURL() + "/api/client/servers/"+ config.getPterodactylServerUUID() + "/command");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            //set request type and related properties
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/vnd.wisp.v1+json");

            //use provided key to authorize
            http.setRequestProperty("Authorization", "Bearer " + config.getPterodactylAPIkey());

            //send command
            String data = "{\"command\": \"" + command + "\"}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            if(http.getResponseCode() == 204){
                http.disconnect();
                //todo check for code 412 when server isn't running
                return true;
            }else{
                //todo change to error logger
                //todo create fail safe class to notify user of failures
                //todo create error logger that can handle https errors
                System.out.println(http.getURL());
                System.out.println(http.getResponseCode());
                System.out.println(http.getResponseMessage());
                http.disconnect();
                return false;
            }
        } catch (Exception e) {
            //leaving this as error instead of fatal
            //the rest of the error response needs to be handled by the fail safe class
            //todo create fail safe class to notify user of failures
            logErrorE(Logger,e,"Could not connect to the Pterodactyl API for server with UUID " + config.getPterodactylServerUUID());
            return false;
        }

    }

    public static boolean sendCommand(String command, backupConfig config){
        //todo receive uuid to send command to
        logInfo(Logger,"Sending command \"" + command + "\" to server with UUID " + config.getPterodactylServerUUID());
        if(!sendCommandRequest(command, config)){
            //leaving this as error instead of fatal
            //the rest of the error response needs to be handled by the fail safe class
            //todo create fail safe class to notify user of failures
            logFatal(Logger,"Command \"" + command + "\" could not be sent to server with UUID " + config.getPterodactylServerUUID());
            return false;
        }else{
            logInfo(Logger,"Command \"" + command + "\" was successfully sent server with UUID " + config.getPterodactylServerUUID());
        }
        return true;
    }
}
