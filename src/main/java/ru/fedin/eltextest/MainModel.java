package ru.fedin.eltextest;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainModel {
    private static final Logger log = Logger.getLogger(MainModel.class);

    public String getIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e);
        }
        return "error";
    }

    public String getTime(){
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("y-M-d  H:mm");
        return dateFormatter.format(date);
    }
}
