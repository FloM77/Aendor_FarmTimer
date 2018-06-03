package AT.MSev.FarmTimer;

import AT.MSev.Mango_Core.Utils.TimedCall;
import AT.MSev.Mango_Core.Utils.TimedEvent;

public class GrowCall extends TimedCall {
    @Override
    public void Run() {
        FarmTimer.GrowAllStated();
    }
}
