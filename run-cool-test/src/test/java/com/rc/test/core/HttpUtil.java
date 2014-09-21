package com.rc.test.core;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public final static int HTTP_STATUS_OK = 200;

    public byte[] doPost(String sUrl, String data) {
        return doPost(sUrl, data.getBytes());
    }

    public byte[] doPost(String sUrl, byte[] bytes) {
        System.out.println("post start...");
        OutputStream out = null;
        HttpURLConnection connection = null;
        byte[] response = null;
        try {
            URL url = new URL(sUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            out = connection.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();
            int code = connection.getResponseCode();
            System.out.println("post getResponseCode: " + code);
            if (code == HTTP_STATUS_OK) {
                InputStream in = connection.getInputStream();
                response = IOUtils.toByteArray(in);
            } else {
                System.out.println("Post failed.");
            }
            connection.disconnect();
            System.out.println("post end...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (connection != null)
                    connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
