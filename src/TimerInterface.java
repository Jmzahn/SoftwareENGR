class TimerInterface
{
    private static long startTime=System.currentTimeMillis();//logs start time
    static void calcTime() {
        long elapsedTime=System.currentTimeMillis()-startTime;//gets elapsed time
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        if(elapsedMinutes>5)//Setting "midnight" as every 5 minutes
        {
            BusinessLogic.timerSignal();
        }
    }
}
