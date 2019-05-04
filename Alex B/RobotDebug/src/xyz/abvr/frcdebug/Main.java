package xyz.abvr.frcdebug;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DebugSession ds = new DebugSession(DebugSession.Platform.WINDOWS);
        ds.start();
        DebugObjectSynchronizer dos = new DebugObjectSynchronizer(new LoggingDebugObject[]{new LoggingDebugObject(ds,"m","0","1",System.currentTimeMillis())});
        for(int i=0; i < 10; i++){
            dos.addDebugObject(new LoggingDebugObject(ds,"m",""+i,"0",System.currentTimeMillis()));

        }
        dos.syncAndPush();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < dos.a.length; j++){
                dos.a[j].setEvent(""+j);
                dos.a[j].setAnnotation(""+Math.random()*25);
            }
            dos.syncAndPush();
        }
        ds.end();
    }
}
