import java.util.ArrayList;

public class InfoHolder {
    private int id;
    private String profile;
    private char userProfile;
    private ArrayList<String> profile_names;
    private ArrayList<Character> userProfiles;
    private int monthplancost;


    public InfoHolder() {
        id = 0;
        profile = "";
        userProfile = 'z';
        profile_names = new ArrayList<>();
        userProfiles = new ArrayList<>();
        monthplancost = 0;

    }

    public boolean hasProfile() {
        return profile != "";
    }

    public boolean hasId() {
        return id != 0;
    }

    public boolean hasUserProfile() {
        return userProfile != 'z';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setUserProfile(char userProfile) {
        this.userProfile = userProfile;
    }

    public char getUserProfile() {
        return userProfile;
    }

    public void setUserProfiles(ArrayList<Character> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public ArrayList<Character> getUserProfiles() {
        return userProfiles;
    }

    public void setProfile_names(ArrayList<String> profile_names) {
        this.profile_names = profile_names;
    }

    public ArrayList<String> getProfile_names() {
        return profile_names;
    }

    public void setMonthplancost(String monthplan) {
        if (monthplan.compareTo("Family")==0) {
            this.monthplancost =  99;
        }
        else if (monthplan.compareTo("Couple")==0) {
            this.monthplancost =  69;
        }
        else if (monthplan.compareTo("Individual")==0) {
            this.monthplancost =  49;
        }
    }
    public int getMonthplancost() {
        return monthplancost;
    }

}