//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Source
{
    //timer actor exists on a separate thread from main
    private static final ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);
    public static void main(String[] args)
    {
        try{
            startTimer();//first thing we do is start the timer actor
            Scanner in=new Scanner(System.in);
            System.out.println("Boot message:\nEnter 1 for Employee Interface, otherwise Customer Interface will start.");
            String who=in.nextLine();

            if(who.toUpperCase().startsWith("1"))
                EmployeeInterface.welcome();
            else
                CustomerInterface.welcome();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private static void startTimer(){
        final Runnable checkIfMidnight = TimerInterface::calcTime;//makes a Runnable that references TimerInterface's calcTime
        final ScheduledFuture<?> timerHandle =
                scheduler.scheduleAtFixedRate(checkIfMidnight, 1,30, TimeUnit.SECONDS);//this runs the timer thread every 30 seconds
        scheduler.schedule(() -> {
            timerHandle.cancel(true);
        },60*13,TimeUnit.SECONDS);//This will kill the timer thread when its 13mins old
    }

}