package ugia.io.androidbeautytreatment.model;

/**
 * Simple POJO to hold the information of a contact or friend
 * <p/>
 * Created by joseluisugia on 26/09/15.
 */
public class Contact {

    private final int avatarResource;
    private final String name;
    private final String phoneNumber;
    private final boolean hasRecentConversation;

    public Contact(int avatarResource, String name, String phoneNumber, boolean hasRecentConversation) {

        this.avatarResource = avatarResource;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hasRecentConversation = hasRecentConversation;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean hasRecentConversation() {
        return hasRecentConversation;
    }
}
