package xyz.abvr.frcdebug;

import java.util.Date;

public class DebugObjectSynchronizer {
    public LoggingDebugObject[] a;
    public DebugObjectSynchronizer(LoggingDebugObject[] a){
        this.a = a;
    }
    public void addDebugObject(LoggingDebugObject b){
        if(this.a != null){
            // Copy over array to new array
            LoggingDebugObject[] c = new LoggingDebugObject[this.a.length + 1];
            for(int i = 0; i < this.a.length; i++){
               c[i] = this.a[i];
            }
            // Add new object
            c[this.a.length] = b;
            // Copy array back over
            this.a = new LoggingDebugObject[c.length];
            for(int i = 0; i < c.length; i++){
                this.a[i]=c[i];
            }
        }
    }
    public void syncAndPush(){
        long d = System.currentTimeMillis();
        for(int i = 0; i < a.length; i++){
            a[i].setDatetime(d);
            a[i].push();
        }
    }
}
