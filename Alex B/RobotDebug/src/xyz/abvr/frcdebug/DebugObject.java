package xyz.abvr.frcdebug;

public class DebugObject {
    /**The correlating DebugSession**/
    public DebugSession ds;
    /**The content of the message**/
    public String content;

    /**
     * Create a DebugObject with a known DebugSession and content
     * @param ds
     * @param content
     */
    public DebugObject(DebugSession ds, String content){
        this.ds = ds;
        this.content = content;
    }

    /**
     * Create a DebugObject with a known DebugSession
     * @param ds
     */
    public DebugObject(DebugSession ds){
        this.ds = ds;
        this.content = "";
    }

    /**
     * Create a DebugObject with no known DebugSession nor content
     */
    public DebugObject(){

    }
    public void push(){
        this.ds.println(content);
    }
    public void mapToDebugSession(DebugSession ds){
        this.ds = ds;
    }
    public void setContent(String content){
        this.content = content;
    }
}
