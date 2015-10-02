package ugia.io.androidbeautytreatment.model;

/**
 * Created by joseluisugia on 26/09/15.
 */
public class Contact {

    public int avatarResource;
    public String name;
    public String phoneNumber;
    public boolean hasRecentConversation;

    public Contact(int avatarResource, String name, String phoneNumber, boolean hasRecentConversation) {

        this.avatarResource = avatarResource;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hasRecentConversation = hasRecentConversation;
    }
}
