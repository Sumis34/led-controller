package com.example.firstapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GetRequest {
    private String responseStr;
    private int responseCode;
    public boolean changeState(String ip) throws IOException {
        URL url = new URL("http://" + ip + "/state");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return true;
    }

    public String sendGET(String ip, String action) throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /*URL url = new URL("http://" + ip + "/state");
                    URLConnection yc = url.openConnection();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    yc.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null)
                        System.out.println(inputLine);
                    in.close();
                    */
                    URL obj = new URL("http://" + ip + "/" + action);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    responseCode = con.getResponseCode();
                    System.out.println("GET Response Code :: " + responseCode);
                    //responseStr = "" + responseCode;
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        responseStr = response.toString() + " Response Code:" + responseCode;
                        // print result
                        //System.out.println(responseStr);

                    } else {
                        System.out.println("GET request not worked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while (responseStr == null)
            System.out.println("Waiting");
        if (responseStr == null)
            responseStr = "Could not find Ski!";
        return responseStr;
    }
}
