/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbManager;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author bigpa
 */
public class Logger {
    
    private static final String LOG_FILE = "log.txt"; //Se crea en la raiz del proyecto
    
    public static void info (String msg){
        log("INFO",msg);
    }
    
    private static void error(String msg){
        log("ERROR",msg);
    }
    
    private static String timestamp(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
    }
    
    private static void log(String level, String msg) {
        String logEntry = String.format("[%] %s - %s%n", level, timestamp(), msg);
        try (FileWriter fw = new FileWriter(LOG_FILE, true)){
            fw.write(logEntry);
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el log: " + e.getMessage());
        }
    }
    
}
