package resume.vehicles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resume.Result;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


@XmlRootElement
public abstract class Vehicle implements Callable<Result> {
    private static final int MAX_RANDOM = 100;
    protected String name;
    protected double speed;
    protected int possibilityPuncture;
    protected int stopTime;
    protected double circleSize;
    private Random random = new Random();
    private static Logger fileLogger = LoggerFactory.getLogger("FILE");


    public void setCircleSize(double circleSize) {
        this.circleSize = circleSize;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "speed")
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @XmlElement(name = "possibilityPuncture")
    public void setPossibilityPuncture(int possibilityPuncture) {
        this.possibilityPuncture = possibilityPuncture;
    }

    @XmlElement(name = "stopTime")
    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    private void messageAboutPuncture() {
        System.out.println("Прокол " + name);
    }

    public void messageWithParameters() {
        System.out.println(name + ": \n" +
                "Скорость: " + speed + "\n" +
                "Вероятность прокола колеса: " + possibilityPuncture);
    }

    private void puncture() {
        try {
            TimeUnit.SECONDS.sleep(stopTime);
        } catch (InterruptedException e) {
            fileLogger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public Result call() {
        double mileage = 0;
        double time = 0;
        double distanceLeft;
        while (mileage < circleSize) {
            time++;
            mileage += speed;
            distanceLeft = circleSize - mileage;
            if (random.nextInt(MAX_RANDOM) <= possibilityPuncture) {
                messageAboutPuncture();
                puncture();
                time += stopTime;
            }
            if (distanceLeft < speed) {
                time = time + (distanceLeft / speed);
                break;
            }
            System.out.println(name + " пройденое расстояние: " + mileage);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                fileLogger.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return new Result(name, time);
    }
}

