class TimerInterface
{
    private static long startTime=System.currentTimeMillis();
    static void calcTime() {
        long elapsedTime=System.currentTimeMillis()-startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        if(elapsedMinutes>5)//Setting "midnight" as 5 minutes
        {
            BusinessLogic.timerSignal();
        }
    }
}
