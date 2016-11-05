package ic.doc.camera;

public class Camera implements WriteListener {

    private final Sensor sensor;
    private final MemoryCard memoryCard;
    private boolean powerIsOn = false;
    private boolean isWriting = false;

    public Camera(Sensor sensor, MemoryCard memoryCard){
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if (powerIsOn) {
            memoryCard.write(sensor.readData());
            isWriting = true;
        }
    }

    public void powerOn() {
        powerIsOn = true;
        sensor.powerUp();
    }

    public void powerOff() {
        powerIsOn = false;
        if(!isWriting) {
            sensor.powerDown();
        }
    }

    public void writeComplete() {
        isWriting = false;
        if(!powerIsOn){
            sensor.powerDown();
        }
    }
}

