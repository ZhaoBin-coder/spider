package bean;

public class UniversityPicture {
    private String cid;
    private String picture;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "UniversityPicture{" +
                "cid='" + cid + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
