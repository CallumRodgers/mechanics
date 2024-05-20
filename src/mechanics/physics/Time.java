package mechanics.physics;

import java.util.Timer;
import java.util.TimerTask;

public class Time {

    private int freq;

    private double t;
    private Timer timer;
    private boolean isRunning;
    private double speed = 1.0;

    private long lastUpdate;

    private Runnable updateRunnable;

    public Time() {
        this.t = 0;
        this.timer = new Timer();
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setOnUpdate(Runnable r) {
        this.updateRunnable = r;
    }

    public void setFrequency(int freq) {
        if (isRunning) {
            pause();
            this.freq = freq;
            start();
        } else {
            this.freq = freq;
        }
    }

    public void reset() {
        t = 0;
    }

    public void start() {
        isRunning = true;
        lastUpdate = System.currentTimeMillis();
        timer.scheduleAtFixedRate(getTask(), 0, 1000 / freq);
    }

    public void pause() {
        timer.cancel();
        timer = new Timer();
        isRunning = false;
    }

    public void resume() {
        start();
    }

    public void setTime(double t) {
        this.t = t;
    }

    public double now() {
        return t;
    }

    private TimerTask getTask() {
        return new TimerTask() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                t += ((now - lastUpdate) / 1000.0) * speed;
                lastUpdate = now;
                updateRunnable.run();
            }
        };
    }

}
