package conf;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "log.txt";

    public static void info(String msg) {
        log("INFO", msg);
 
    }

    public static void error(String msg) {
        System.err.println("[ERROR] " + timestamp() + " - " + msg);
    }

     private static void log(String level, String msg) {
        String logEntry = String.format("[%s] %s - %s%n", level, timestamp(), msg);

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) { // Abrir el archivo en modo append
            fw.write(logEntry); // Escribir la entrada de log en el archivo
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el log: " + e.getMessage());
        }
    }
     
    private static String timestamp() { // Metodo para obtener la fecha y hora actual formateada
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

   
}
