package Services;

import Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileService {
    Map<String, User> userMap;
    Map<String, List<String>> slotsPerUser;

    public UserProfileService() {
        this.userMap = new HashMap<>();
        this.slotsPerUser = new HashMap<>();
    }

    public Map<String, List<String>> getSlotsPerUser() {
        return slotsPerUser;
    }

    public void addSlotForUser(String userId,String slotId){
        slotsPerUser.computeIfAbsent(userId,(key)->new ArrayList<>());
        List<String> slotsForUser = slotsPerUser.get(userId);
        slotsForUser.add(slotId);
        slotsPerUser.put(userId,slotsForUser);
    }
    public void createUser(String id,String name, String email){
        User user = new User(id,name,email);
        addUser(user);
    }
    public void setSlotsPerUser(Map<String, List<String>> slotsPerUser) {
        this.slotsPerUser.putAll(slotsPerUser);
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void addUser(User user){
        this.userMap.put(user.getUserId(),user);
        System.out.println("Success "+ user.getUserId());
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap.putAll(userMap);
        System.out.println("Success ");
    }
}
