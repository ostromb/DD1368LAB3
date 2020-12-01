public class InfoHolder {
    private int id;
    private String profile;

    public InfoHolder() {
        id = 0;
        profile = "";
    }

    public boolean hasProfile() {
        return profile != "";
    }

    public boolean hasId() {
        return id != 0;
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
}