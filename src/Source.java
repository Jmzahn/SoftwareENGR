import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Source
{
    private final ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);
    public static void main(String[] args)
    {




    }
    public void startTimer(){
        final Runnable checkIfMidnight = TimerInterface::calcTime;//makes a Runnable that references TimerInterface's calcTime
        final ScheduledFuture<?> timerHandle =
                scheduler.scheduleAtFixedRate(checkIfMidnight, 1,30, TimeUnit.SECONDS);//this runs the timer thread every 30 seconds
        scheduler.schedule(() -> {
            timerHandle.cancel(true);
        },60*13,TimeUnit.SECONDS);//This will kill the timer thread when its 13mins old
    }

}