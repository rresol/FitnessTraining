package Services;

import Constants.ClassType;
import Models.*;
import Models.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRepositoryService {
    Map<String,Class> availableClasses;
    Map<ClassType, List<Slot>> slotsPerClass;


    public ClassRepositoryService() {
        this.availableClasses = new HashMap<>();
        this.slotsPerClass = new HashMap<>();
    }
    public void createClass(String classTypeName, String classId, List<Slot>slots){
        ClassType classType = ClassType.valueOf(classTypeName);
        Class className;
        switch (classType){
            case GYM:
                className = new Gym(classId,slots);
                break;
            case YOGA:
                className = new Yoga(classId,slots);
                break;
            case DANCE:
                className = new Dance(classId,slots);
                break;
            default:
                System.out.println("Not Supported");
                return;
        }
        addClass(className);
        addMultipleSlots(classType,slots);
        System.out.println("SUCCESS");
    }
    public void addMultipleSlots(ClassType classType, List<Slot> slotsToAdd){
        this.slotsPerClass.computeIfAbsent(classType, (key)->new ArrayList<>());
        List<Slot> slots = slotsPerClass.get(classType);
        for(int i=0;i<slotsToAdd.size();i++){
            slots.add(slotsToAdd.get(i));
            System.out.println("slots Added");
        }
        this.slotsPerClass.put(classType,slots);
    }
    public Map<ClassType, List<Slot>> getSlotsPerClass() {
        return slotsPerClass;
    }
    public void addSlot(ClassType classType, Slot slot){
        this.slotsPerClass.computeIfAbsent(classType, (key)->new ArrayList<>());
        List<Slot> slots = slotsPerClass.get(classType);
        slots.add(slot);
        this.slotsPerClass.put(classType,slots);
    }

    public void setSlotsPerClass(Map<ClassType, List<Slot>> slotsPerClass) {
        this.slotsPerClass.putAll(slotsPerClass);
    }

    public Map<String, Class> getAvailableClasses() {
        return availableClasses;
    }
    public void addClass(Class classType) {
        this.availableClasses.put(classType.getId(),classType);
        System.out.println("Success "+ classType.getId());
    }
    public void setAvailableClasses(Map<String, Class> availableClasses) {
        this.availableClasses.putAll(availableClasses);
        System.out.println("Success ");
    }
}
