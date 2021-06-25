package net.mov51.helpers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static net.mov51.helpers.config.coreGetters.*;

public class PterodactylApiHelper {

    /*
    todo Verify global core config and passed cycle configs
     -Verify panel domain resolution
     -verify that api key can retrieve a server list
      -might be able to do in tandem with the one below, but only if there are separate response codes
     -get server to verify that the requested server UUID is accessible by the apikey provided
      -https://docs.panel.gg/#get-a-specific-server
    */

    public static final String keyDefaultPanelURL = "panelURL";
    public static final String keyDefaultSeverUUID = "serverUUID";
    public static final String keyDefaultAPIkey = "key";

    private static boolean sendCommandRequest(String command){

        try {
            //build API url
            URL url = new URL(getCorePanelURL() + "/api/client/servers/"+ getCoreServerUUID() + "/command");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            //set request type and related properties
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/vnd.wisp.v1+json");

            //use provided key to authorize
            http.setRequestProperty("Authorization", "Bearer " + getCoreAPIkey());

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
                System.out.println(http.getResponseCode());
                System.out.println(http.getResponseMessage());
                http.disconnect();
                return false;
            }
        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendCommand(String command){
        //change to accept separate named UUIDS
        //todo log as info
        System.out.println("Sending command \"" + command + "\" to server with UUID " + getCoreServerUUID());
        if(!sendCommandRequest(command)){
            //todo change to error logger
            System.out.println("Command \"" + command + "\" could not be sent to server with UUID " + getCoreServerUUID());
            return false;
        }else{
            //todo log as info
            System.out.println("Command \"" + command + "\" was successfully sent server with UUID " + getCoreServerUUID());
        }
        return true;
    }
}
