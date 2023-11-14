package com.example.first.gpio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GPIOManager {

    private static final String[] openCommands = {
            "echo '\\x8a\\x01\\x01\\x11\\x9b' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x02\\x11\\x98' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x03\\x11\\x99' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x04\\x11\\x9e' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x05\\x11\\x9f' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x06\\x11\\x9c' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x07\\x11\\x9d' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x08\\x11\\x92' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x09\\x11\\x93' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0a\\x11\\x90' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0b\\x11\\x91' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0c\\x11\\x96' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0d\\x11\\x97' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0e\\x11\\x94' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x0f\\x11\\x95' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x10\\x11\\x8a' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x11\\x11\\x8b' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x12\\x11\\x88' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x13\\x11\\x89' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x14\\x11\\x8e' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x15\\x11\\x8f' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x16\\x11\\x8c' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x17\\x11\\x8d' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x18\\x11\\x82' > /dev/ttyS5",
            "echo '\\x8a\\x01\\x19\\x11\\x83' > /dev/ttyS5",
    };

    private static final String allOpenCommand = "echo '\\x8a\\x01\\x00\\x11\\x9a' > /dev/ttyS5";


    private static String sudoCmd(String... strings) {
        String res = "";
        Process su = null;
        DataOutputStream outputStream = null;
        InputStream response = null;
        try{
            su = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(su.getOutputStream());
            response = su.getInputStream();

        } catch (IOException ignored){

        }
        if(outputStream!=null && su!=null)
        {
            try{
                for (String s : strings) {
                    outputStream.writeBytes(s+"\n");
                    outputStream.flush();
                }
                outputStream.writeBytes("exit\n");
                outputStream.flush();
                su.waitFor();
                res = readFully(response);

            }catch (Exception ignored){}
        }
        try {
            if(outputStream != null)
                outputStream.close();
            if(response != null)
                response.close();
        } catch (IOException ignored) {
        }

        return res;
    }

    /**
     * check current pin status
     * @param PIN pin number from GPIO map
     * @return true : is already on(High), and false : off(Low) , null : unknown state
     */
    @Nullable
    public static Boolean getPinStatus(@NonNull String PIN) {
        Process p;
        try {

            p = Runtime.getRuntime().exec("cat /sys/class/gpio/gpio"+PIN+"/value");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = reader.readLine();
            reader.close();
            if(line == null)
                return null;
            return "1".equals(line.trim()) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * set pin to high level or 5V
     * @param PIN pin number from GPIO map
     */
    public static void setPinOn(int PIN)
    {
        String c = openCommands[PIN];

        String[] command = new String[]{
                c
        };

        sudoCmd(command);
    }

    public static void setAllPinsOn() {
        String[] command = new String[]{
                allOpenCommand
        };

        sudoCmd(command);
    }

    /**
     * read input stream and return as a string
     * @param is input stream
     * @return result of stream
     * @throws IOException occurred if can't read stream from io
     */
    private static String readFully(@NonNull InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString("UTF-8");
    }
}

