package bean;

public class UniversityInfo {
    private String cid;
    private String year;
    private String batch;
    private String up;
    private String low;
    private String avg;
    private Double difvalue;
    private String lowlevel;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Double getDifvalue() {
        return difvalue;
    }

    public void setDifvalue(Double difvalue) {
        this.difvalue = difvalue;
    }

    public String getLowlevel() {
        return lowlevel;
    }

    public void setLowlevel(String lowlevel) {
        this.lowlevel = lowlevel;
    }

    @Override
    public String toString() {
        return "UniversityInfo{" +
                "cid='" + cid + '\'' +
                ", year='" + year + '\'' +
                ", batch='" + batch + '\'' +
                ", up='" + up + '\'' +
                ", low='" + low + '\'' +
                ", avg='" + avg + '\'' +
                ", difvalue='" + difvalue + '\'' +
                ", lowlevel='" + lowlevel + '\'' +
                '}';
    }
}
