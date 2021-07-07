package net.mov51.helpers;

import org.apache.logging.log4j.Logger;

public class logHelper {

    public static void logInfo(Logger l,String message){
        l.info(message);
    }

    public static void logWarn(Logger l,String message){
        l.warn(message);
    }

    public static void logError(Logger l,String message){
        l.error(message);
    }

    public static void logErrorE(Logger l,Exception e,String message){
        l.error(message);
        e.printStackTrace();
    }

    public static void logFatalExit(Logger l,String message){
        l.fatal(message);
        System.exit(1);
    }

    public static void logFatalE(Logger l,Exception e,String message){
        l.fatal(message);
        e.printStackTrace();
    }

    public static void logFatalExitE(Logger l,Exception e,String message){
        l.fatal(message);
        e.printStackTrace();
        System.exit(1);
    }

    public static void logFatal(Logger l,String message){
        l.fatal(message);
    }
}
