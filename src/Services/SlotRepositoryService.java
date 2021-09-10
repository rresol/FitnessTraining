package Services;

import Models.Slot;
import Models.SlotStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SlotRepositoryService {
    private Map<String, Slot> slotIdToSlotMap;

    public SlotRepositoryService() {
        this.slotIdToSlotMap = new HashMap<>();
    }

    public Map<String, Slot> getSlotIdToSlotMap() {
        return slotIdToSlotMap;
    }
    public void createSlot(String id, String startTime, String capacity, String slotStatus){
        Slot slot = new Slot(id,new Date(),Integer.valueOf(capacity),SlotStatus.valueOf(slotStatus));
        addSlot(slot);
        System.out.println("SUCCESS");
    }

    public void addSlot(Slot slot){
        this.slotIdToSlotMap.put(slot.getId(),slot);
    }
    public void setSlotIdToSlotMap(Map<String, Slot> slotIdToSlotMap) {
        this.slotIdToSlotMap.putAll(slotIdToSlotMap);
    }
}

