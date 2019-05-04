package xyz.abvr.frc100.robotdebug.lib;
import java.util.*;
import java.io.*;
public class LoggingSession {
    public enum Platform{
        WINDOWS,LINUX,ROBORIO
    }
    public Platform platform;
    public String name;
    public Date starttime;
    public Date endtime;
    public BufferedWriter bw;
    public LoggingSession(Platform platform){
        this.platform = platform;
        this.name = new Date().toString();
    }
    public LoggingSession(String name, Platform platform){
        this.name = name;
        this.platform = platform;

    }
    public void start(){
        starttime = new Date();
        FileWriter fw;
        if(this.platform == Platform.WINDOWS){
            fw = new FileWriter("c:/riologging/export/"+name+".csv");
        }else if(this.platform == Platform.LINUX){
            fw = new FileWriter("/home/riologging/export/"+name+".csv");
        }
        else if(this.platform == Platform.ROBORIO){
            fw = new FileWriter("/home/lvuser/riologging/export/"+name+".csv");
        }else{
            throw new Inv
        }
    }
    public void end(){

    }
}
