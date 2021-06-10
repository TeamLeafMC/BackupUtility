package net.mov51.helpers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static net.mov51.helpers.yamlHelper.*;

public class PterodactylApiHelper {

    public static void sendCommand(String command){

        try {
            //build API url
            URL url = new URL(getPanelURL() + "/api/client/servers/"+ getUUID() + "/command");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            //set request type and related properties
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/vnd.wisp.v1+json");

            //use provided key to authorize
            http.setRequestProperty("Authorization", "Bearer " + getKey());

            //send command
            String data = "{\"command\": \"" + command + "\"}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();
        } catch (Exception e) {
            //todo change to error logger
            e.printStackTrace();
        }

    }
}
