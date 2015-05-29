package tuehomework.thermostat.Thermostat;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer.*;

/**
 * Created by mac on 29.05.15.
 */
public class Simulator {

    final static int INERTIA_TIME =1;//?????
    double currentTemperature;
    double goalTemperature;
    Timer timer;
    Timer delayTimer;
    Time currentTime;
    Day currentDay;
    Schedule executedSchedule;

    public void setTimeChangedListener(OnTimeChangedListener timeChangedListener) {
        this.timeChangedListener = timeChangedListener;
    }

    public void setTempChangedListener(OnTemperatureChangedListener tempChangedListener) {
        this.tempChangedListener = tempChangedListener;
    }

    OnTimeChangedListener timeChangedListener;
    OnTemperatureChangedListener tempChangedListener;


    public Day getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Day currentDay) {
        this.currentDay = currentDay;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Time currentTime) {
        this.currentTime = currentTime;
    }


    public Simulator (final Schedule schedule)
    {
        //start time I should get from the system time?
        currentTime=new Time(Calendar.HOUR, Calendar.MINUTE);

        //always start from Monday
        currentDay =schedule.getDay(Days.MON);
        executedSchedule=schedule;
        TimerTask task =new TimerTask() {
            @Override
            public void run() {
                //new minute
                if(currentTime.getMinutes()!=59)
                {
                    currentTime.setMinutes(currentTime.getMinutes()+1);
                    timeChangedListener.OnCurrentTimeChanged(currentTime);
                }
                //new hour
                else{
                    currentTime.setMinutes(0);
                    if (currentTime.getHours()!=23)
                    {
                        currentTime.setHours(currentTime.getHours()+1);
                        timeChangedListener.OnCurrentTimeChanged(currentTime);

                    }
                    else
                    {

                        currentTime=new Time(0,0);
                        timeChangedListener.OnCurrentTimeChanged(currentTime);
                        //new day
                        if(currentDay.name.ordinal()!=6) {
                            currentDay = schedule.getDay(Days.values()[(currentDay.name.ordinal() + 1)]);
                            timeChangedListener.OnCurrentDayChanged(currentDay);
                        }
                        //new week
                        else{
                            currentDay=schedule.getDay(Days.MON);
                            timeChangedListener.OnCurrentDayChanged(currentDay);
                        }
                    }
                }

            }
        };

        timer=new Timer();
        timer.schedule(task,200);

        delayTimer=new Timer();


        TimerTask delayTask=new TimerTask() {
            @Override
            public void run() {
                if (currentTemperature-goalTemperature>0)
                {
                    currentTemperature=currentTemperature-0.1;
                   tempChangedListener.onActualTemperatureChanged(currentTemperature);

                }
                else if(currentTemperature-goalTemperature<0)
                {
                    currentTemperature=currentTemperature+0.1;
                    tempChangedListener.onActualTemperatureChanged(currentTemperature);
                }
            }
        };


delayTimer.schedule(delayTask, 200);



    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Schedule getExecutedSchedule() {
        return executedSchedule;
    }

    public void setExecutedSchedule(Schedule executedShedule) {
        this.executedSchedule = executedShedule;
    }



    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
        tempChangedListener.onActualTemperatureChanged(currentTemperature);
    }

    public double getGoalTemperature() {
        return goalTemperature;

    }

    public void setGoalTemperature(double goalTemperature) {
        this.goalTemperature = goalTemperature;
        tempChangedListener.onTargetTemperatureChanged(goalTemperature);
    }

}
