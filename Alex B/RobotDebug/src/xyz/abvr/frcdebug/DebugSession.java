package xyz.abvr.frcdebug;
import java.io.*;
import java.util.*;
public class DebugSession {
    public enum Platform{
        WINDOWS,LINUX,ROBORIO
    }
    public Platform platform;
    public String name;
    public long starttime;
    public long endtime;
    public PrintStream fs;
    public DebugSession(Platform platform){
        this.platform = platform;
        this.name = new Date().toString();
    }
    public DebugSession(String name, Platform platform){
        this.name = name;
        this.platform = platform;

    }
    public void start() {
        try {
            starttime = System.currentTimeMillis();
            File f;
            this.name = name.replace(':','-');
            if (this.platform == Platform.WINDOWS) {
               // f = new File("C:\\Users\\Public\\riologging\\export\\" + name + ".csv");
                f = new File(name+".csv");
                System.out.println(f.getAbsolutePath());
            } else if (this.platform == Platform.LINUX) {
                f = new File("/home/riologging/export/" + name + ".csv");
            } else if (this.platform == Platform.ROBORIO) {
                f = new File("/home/lvuser/riologging/export/" + name + ".csv");
            } else {
                throw new InvalidPlatformException("Invalid Platform " + this.platform.toString());
            }
            f.createNewFile();
            fs = new PrintStream(f);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void println(String msg){
        fs.println(msg);
    }
    public void end(){
        fs.close();
    }
}
