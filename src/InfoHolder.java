public class InfoHolder {
    private int id;
    private String profile;
    private char userProfile;

    public InfoHolder() {
        id = 0;
        profile = "";
        userProfile = 'z';
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
}