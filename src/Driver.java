import Constants.ClassType;
import Models.Class;
import Models.Slot;
import Models.User;
import Services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        UserProfileService userProfileService = new UserProfileService();
        SlotRepositoryService slotRepositoryService = new SlotRepositoryService();
        ClassRepositoryService classRepositoryService = new ClassRepositoryService();
        ClassBookingService defaultClassBookingService = new DefaultClassBookingService(userProfileService,classRepositoryService,slotRepositoryService);

        userProfileService.createUser("u1","test1","txyz@gmail.com");
        userProfileService.createUser("u2","test2","twyz@gmail.com");
        userProfileService.createUser("u3","test3","tzyz@gmail.com");
        slotRepositoryService.createSlot("s1","Time","100","AVAILABLE");
        slotRepositoryService.createSlot("s2","Time","100","AVAILABLE");
        slotRepositoryService.createSlot("s3","Time","100","AVAILABLE");
        Map<String,Slot> slotIdToSlotMap = slotRepositoryService.getSlotIdToSlotMap();
        List<Slot> slotList = new ArrayList<>();
        slotList.add(slotIdToSlotMap.get("s1"));
        slotList.add(slotIdToSlotMap.get("s2"));

        classRepositoryService.createClass("DANCE","c1",slotList);
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                String command = scanner.nextLine();
                String[] commands = command.split(" ");
                //User user, String classTypeName, String slotId) {
                switch (commands[0]){
                    case "BOOK":
                        User user = userProfileService.getUserMap().get(commands[1]);
                        String classType = commands[2];
                        String slotID = commands[3];
                        defaultClassBookingService.bookClass(user,classType,slotID);
                        break;
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



    }
}
