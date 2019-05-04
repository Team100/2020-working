package xyz.abvr.frcdebug;

import java.util.Date;

public class PushDebugObject extends LoggingDebugObject {
    public PushDebugObject(DebugSession ds, String flag, String event, String annotation, long date, String commentary) {
        super(ds, flag, event, annotation, date, commentary);
        super.push();
    }

    public PushDebugObject(DebugSession ds, String flag, String event, String annotation, long date) {
        super(ds, flag, event, annotation, date);
        super.push();
    }

    public PushDebugObject(DebugSession ds, String flag, String event, long date) {
        super(ds, flag, event, date);
        super.push();
    }
}
