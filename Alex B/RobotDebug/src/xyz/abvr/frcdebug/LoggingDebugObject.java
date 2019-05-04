package xyz.abvr.frcdebug;

import java.util.Date;

public class LoggingDebugObject extends  DebugObject{
    private String flag;
    private String event;
    private String annotation;
    private long datetime;



    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    private String commentary;

    /**
     * Create a new Debugger Object given the parameters
     * @param ds DebugSession
     * @param flag Flag to apply
     * @param event Event Description
     * @param annotation Annotation for event
     * @param datetime Timestamp for event
     * @param commentary Commentary for the event
     */
    public LoggingDebugObject(DebugSession ds, String flag, String event, String annotation, long datetime, String commentary) {
        super(ds);
        this.flag = flag;
        this.event = event;
        this.annotation = annotation;
        this.datetime = datetime;
        this.commentary = commentary;
    }

    public LoggingDebugObject(DebugSession ds, String flag, String event, String annotation, long datetime) {
        super(ds);
        this.flag = flag;
        this.event = event;
        this.annotation = annotation;
        this.datetime = datetime;
        this.commentary = "";
    }

    public LoggingDebugObject(DebugSession ds, String flag, String event, long datetime) {
        super(ds);
        this.flag = flag;
        this.annotation = "";
        this.event = event;
        this.datetime = datetime;
        this.commentary = "";
    }

    public void push(){
        this.setContent(this.flag+","+this.datetime +","+this.event+","+this.annotation+","+this.commentary);
        super.push();

    }
}
