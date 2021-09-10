package Models;

import java.util.List;

public abstract class Class {
    private String id;
    private List<Slot> slots;

    public Class(String id, List<Slot> slots) {
        this.id = id;
        this.slots = slots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
