package csulb.cecs327.Models;

/**
 * This class contains the methods to pull in artist data for each song
 */
public class Artist {
    private String similar;
    private double familiarity;
    private double hotttnesss;
    private String terms;
    private double latitude;
    private String name;
    private String location;
    private String id;
    private int termsFreq;
    private double longitude;

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public String getSimilar() {
        return similar;
    }

    public void setFamiliarity(double familiarity) {
        this.familiarity = familiarity;
    }

    public double getFamiliarity() {
        return familiarity;
    }

    public void setHotttnesss(double hotttnesss) {
        this.hotttnesss = hotttnesss;
    }

    public double getHotttnesss() {
        return hotttnesss;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getTerms() {
        return terms;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTermsFreq(int termsFreq) {
        this.termsFreq = termsFreq;
    }

    public int getTermsFreq() {
        return termsFreq;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return
                "Artist{" +
                        "similar = '" + similar + '\'' +
                        ",familiarity = '" + familiarity + '\'' +
                        ",hotttnesss = '" + hotttnesss + '\'' +
                        ",terms = '" + terms + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",name = '" + name + '\'' +
                        ",location = '" + location + '\'' +
                        ",id = '" + id + '\'' +
                        ",terms_freq = '" + termsFreq + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}

