package ic.doc.camera;

public class Camera implements WriteListener {
    boolean isOn;
    boolean isWriting;
    Sensor sensor;
    MemoryCard memoryCard;

    public Camera(Sensor sensor, MemoryCard memoryCard){
        this.sensor = sensor;
        this.memoryCard = memoryCard;
        isOn = false;
    }

    public void pressShutter() {
        if (isOn) {
            isWriting = true;
            memoryCard.write(sensor.readData());
        }
    }

    public void powerOn() {
        isOn = true;
        sensor.powerUp();
    }

    public void powerOff() {
        isOn = false;
        if(!isWriting) {
            sensor.powerDown();
        }
    }

    public void writeComplete() {
        isWriting = false;
        if(!isOn){
            sensor.powerDown();
        }
    }
}

