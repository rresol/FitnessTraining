package Models;

import java.util.Date;

public class Slot {
    private String id;
    private Date startTime;
    private int  capacity;
    private SlotStatus slotStatus;

    public Slot(String id, Date startTime, int capacity, SlotStatus slotStatus) {
        this.id = id;
        this.startTime = startTime;
        this.capacity = capacity;
        this.slotStatus = slotStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }
}
