package spdatabase.spinc.servicethreadhandler.event;


import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class GlobalBus {
    private static Bus sBus;
    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus(ThreadEnforcer.MAIN);
        return sBus;
    }
}