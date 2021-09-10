package Services;

import Constants.ClassType;
import Models.Slot;
import Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultClassBookingService implements ClassBookingService{
    private Map<String, List<User>> waitingListPerSlot;
    private UserProfileService userProfileService;
    private ClassRepositoryService classRepositoryService;
    private SlotRepositoryService slotRepositoryService;

    public DefaultClassBookingService(UserProfileService userProfileService, ClassRepositoryService classRepositoryService, SlotRepositoryService slotRepositoryService) {
        waitingListPerSlot = new HashMap<>();
        this.userProfileService = userProfileService;
        this.classRepositoryService = classRepositoryService;
        this.slotRepositoryService = slotRepositoryService;
    }

    private boolean validateDuplicateBooking(String userID, String slotID){
        if(!(userProfileService.getSlotsPerUser().containsKey(userID))) return true;
        List<String> slotsForUser = userProfileService.getSlotsPerUser().get(userID);
        if(slotsForUser.contains(slotID))
            return false;
        return true;
    }

    @Override
    public void bookClass(User user, String classTypeName, String slotId) {
        if(!validateDuplicateBooking(user.getUserId(),slotId)){
            System.out.println("User Already Booked");
            return;
        }
        ClassType classType = Constants.ClassType.valueOf(classTypeName);
        List<Slot> slots = classRepositoryService.getSlotsPerClass().get(classType);
        Slot slot = slotRepositoryService.getSlotIdToSlotMap().get(slotId);

        if(!checkAvailability(slots,slot))
        {
            System.out.println("Slot Not Available");
            updateWaitingList(slot.getId(),user);
            return;
        }
        slot.setCapacity(slot.getCapacity()-1);
        Map<String,List<String>> userSlotMap = userProfileService.getSlotsPerUser();
        userSlotMap.computeIfAbsent(user.getUserId(),(key)-> new ArrayList<>());
        List<String> slotList = userSlotMap.get(user.getUserId());
        userSlotMap.put(user.getUserId(),slotList);
        userProfileService.setSlotsPerUser(userSlotMap);
        System.out.println("Slot Booked for User: "+ user.toString() + " slot: "+ slot.toString());
    }
    private boolean checkAvailability(List<Slot>slots, Slot slot){
        if(!slots.contains(slot)){

            return false;
        }
        return slot.getCapacity() > 0;
    }
    private void updateWaitingList(String slotId,User user){
        waitingListPerSlot.computeIfAbsent(slotId,(key)->new ArrayList<>());
        List<User> users = waitingListPerSlot.get(slotId);
        users.add(user);
        waitingListPerSlot.put(slotId,users);

    }

}
