package Bootstrap.Tools;

import java.util.Date;

/***
 * @author Aaron Chen
 * Let logs are coloful
 */
public class Alog {
    final static private Date date = new java.util.Date();
    private static boolean isUseColorLog = GetConfigProperty.isUseColorLog;

    final static public void log(String message) {
        System.out.println(message);
    }

    final static public void logWithDate(String message) {
        System.out.println("[" + date + "]" + message);
    }

    final static public void logStartup(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[44m" + "[Startup]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Startup]" + " - " + message);
        }
    }

    final static public void logStartupWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[44m" + "[Startup]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Startup]" + " - " + message);
        }
    }

    final static public void logInfo(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[34m" + "[Info]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Info]" + " - " + message);
        }
    }

    final static public void logInfoWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[34m" + "[Info]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Info]" + " - " + message);
        }
    }

    final static public void logWarning(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[33m" + "[Warning]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Warning]" + " - " + message);
        }
    }

    final static public void logWarningWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[33m" + "[Warning]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Warning]" + " - " + message);
        }
    }

    final static public void logError(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[31m" + "[Error]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Error]" + " - " + message);
        }
    }

    final static public void logErrorWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[31m" + "[Error]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Error]" + " - " + message);
        }
    }

    final static public void logException(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[47m" + "[Exception]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Exception]" + " - " + message);
        }
    }

    final static public void logExceptionWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[47m" + "[Exception]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Exception]" + " - " + message);
        }
    }

    final static public void logShutdown(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[41m" + "[Shutdown]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Shutdown]" + " - " + message);
        }
    }

    final static public void logShutdownWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[41m" + "[Shutdown]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + " - " + "[Shutdown]" + " - " + message);
        }
    }

    final static public void logUnknown(String message) {
        if (isUseColorLog) {
            System.out.println((char) 27 + "[0;97m" + "[Unknown]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[Unknown]" + " - " + message);
        }
    }

    final static public void logUnknownWithDate(String message) {
        if (isUseColorLog) {
            System.out.println("[" + date + "]" + " - " + (char) 27 + "[0;97m" + "[Unknown]" + (char) 27 + "[0m" + " - " + message);
        } else {
            System.out.println("[" + date + "]" + "[Unknown]" + " - " + message);
        }
    }
}
