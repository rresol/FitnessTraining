package Services;

import Constants.ClassType;
import Models.User;

public interface ClassBookingService {
    public void bookClass(User user, String classId, String slotId);
}
