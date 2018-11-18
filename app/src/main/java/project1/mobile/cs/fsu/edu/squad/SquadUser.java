package project1.mobile.cs.fsu.edu.squad;

public class SquadUser {

    private String name;
    private String email;
    private String uPassword;
    private float uLat;
    private float uLonge;

    SquadUser(String n, String em, String p, float lon, float lat) {
        setName(n);
        setEmail(em);
        setuPassword(p);
        uLat = lon;
        uLonge = lat;

    }


    public void setName(String s) {

        char[] chars = s.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }

        name = String.valueOf(chars);

    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public void setEmail(String s) {

        email = s;

    }


    public String getName() {

        return name;

    }


    public String getEmail() {

        return email;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuLat(float uLat) {
        this.uLat = uLat;
    }

    public void setuLonge(float uLonge) {
        this.uLonge = uLonge;
    }

    public float getuLat() {
        return uLat;
    }

    public float getuLonge() {
        return uLonge;
    }
}
