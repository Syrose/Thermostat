package tuehomework.thermostat.Thermostat;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer.*;

import tuehomework.thermostat.UI.MainActivity;
import tuehomework.thermostat.UI.MainControl;

/**
 * Created by mac on 29.05.15.
 */
public class Simulator {

    final static int INERTIA_TIME = 1;
    public static Simulator currentInstance;
    int currentTemperature = 195;
    int goalTemperature = 195;
    Timer timer;
    Timer delayTimer;
    Time currentTime;
    Day currentDay;
    Schedule executedSchedule;
    boolean hold = false;

    public void hold()
    {
        hold = true;
    }

    public void unhold()
    {
        hold = false;
    }

    public int getDayTemp()
    {
        return executedSchedule.getDayTemp();
    }

    public int getNightTemp()
    {
        return executedSchedule.getNightTemp();
    }

    public void setTimeChangedListener(OnTimeChangedListener timeChangedListener) {
        this.timeChangedListener = timeChangedListener;

    }

    public void setDayTemp(final int temp)
    {
        this.executedSchedule.setDayTemp(temp);
        MainActivity.currentInstance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (OnTemperatureChangedListener tempChangedListener : tempChangedListeners) {
                    tempChangedListener.onDayTemperatureChanged(temp);
                }
            }
        });
    }

    public void setNightTemp(final int temp)
    {
        this.executedSchedule.setNightTemp(temp);
        MainActivity.currentInstance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (OnTemperatureChangedListener tempChangedListener : tempChangedListeners) {
                    tempChangedListener.onNightTemperatureChanged(temp);
                }
            }
        });
    }

    public void addTempChangedListener(OnTemperatureChangedListener tempChangedListener) {
        tempChangedListeners.add(tempChangedListener);
    }

    OnTimeChangedListener timeChangedListener;
    ArrayList<OnTemperatureChangedListener> tempChangedListeners = new ArrayList<>();


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

    TimerTask task;
    TimerTask delayTask;

    public static Simulator getInstance()
    {
        if (currentInstance == null)
        {
            currentInstance = new Simulator(new Schedule());
        }

        return currentInstance;
    }

    private Simulator(final Schedule schedule) {
        //start time I should get from the system time?
        currentInstance = this;
        currentTime = new Time(0, 0);

        //always start from Monday
        currentDay = schedule.getDay(Days.MON);
        executedSchedule = schedule;
        task = new TimerTask() {
            @Override
            public void run() {
                //new minute
                if (currentTime.getMinutes() != 59) {
                    currentTime.setMinutes(currentTime.getMinutes() + 1);
                    MainActivity.currentInstance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timeChangedListener.OnCurrentTimeChanged(currentTime);
                        }
                    });
                }
                //new hour
                else {
                    currentTime.setMinutes(0);
                    if (currentTime.getHours() != 23) {
                        currentTime.setHours(currentTime.getHours() + 1);
                        MainActivity.currentInstance.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeChangedListener.OnCurrentTimeChanged(currentTime);
                            }
                        });

                    } else {

                        currentTime = new Time(0, 0);


                        MainActivity.currentInstance.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeChangedListener.OnCurrentTimeChanged(currentTime);
                            }
                        });
                        //new day
                        if (currentDay.name.ordinal() != 6) {
                            currentDay = schedule.getDay(Days.values()[(currentDay.name.ordinal() + 1)]);
                            MainActivity.currentInstance.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timeChangedListener.OnCurrentDayChanged(currentDay);
                                }
                            });
                        }
                        //new week
                        else {
                            currentDay = schedule.getDay(Days.MON);
                            MainActivity.currentInstance.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timeChangedListener.OnCurrentDayChanged(currentDay);
                                }
                            });
                        }
                    }
                }

                if (!hold) {
                    if (currentTime.getHours() == 12 && currentTime.getMinutes() == 0) {
                        setGoalTemperature(executedSchedule.getDayTemp());
                        return;
                    }

                    if (currentTime.getHours() == 0 && currentTime.getMinutes() == 0) {
                        setGoalTemperature(executedSchedule.getNightTemp());
                        return;
                    }

                    for (Switch sw : currentDay.getDayToNightSwitches()) {
                        if (sw.getSwitchTime().equals(currentTime)) {
                            setGoalTemperature(schedule.getNightTemp());
                            return;
                        }
                    }

                    for (Switch sw : currentDay.getNightToDaySwitches()) {
                        if (sw.getSwitchTime().equals(currentTime)) {
                            setGoalTemperature(schedule.getDayTemp());
                            return;
                        }
                    }
                }
            }
        };


    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 1000, 10);
        }
    }

    public void stop() {
        if (task != null && delayTask != null) {
            timer.purge();
            timer = null;
        }
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


    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(final int currentTemperature) {
        this.currentTemperature = currentTemperature;
        MainActivity.currentInstance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (OnTemperatureChangedListener tempChangedListener : tempChangedListeners) {
                    tempChangedListener.onActualTemperatureChanged(currentTemperature);
                }
            }
        });
    }

    public int getGoalTemperature() {
        return goalTemperature;

    }

    public void setGoalTemperature(final int goalTemperature) {
        this.goalTemperature = goalTemperature;
        MainActivity.currentInstance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (OnTemperatureChangedListener tempChangedListener : tempChangedListeners) {
                    tempChangedListener.onTargetTemperatureChanged(goalTemperature);
                }
            }
        });
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                while (currentTemperature != goalTemperature) {
                    if (currentTemperature < goalTemperature) {
                        currentTemperature++;
                    }

                    if (currentTemperature > goalTemperature) {
                        currentTemperature--;
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    MainActivity.currentInstance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (OnTemperatureChangedListener tempChangedListener : tempChangedListeners) {
                                tempChangedListener.onActualTemperatureChanged(currentTemperature);
                            }
                        }
                    });

                }
                return null;
            }
        }.execute();
    }
}
