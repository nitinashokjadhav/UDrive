package nitin.com.u_drive;

import com.google.firebase.database.Exclude;

public class Car {
    private String cName;
    private String cImageUrl;
    private String cOwnerId;
    private String cDetails;
    private String mkey;
    private String status;

    public Car() {
    }

    public Car(String cOwnerId, String cName, String cImageUrl, String cDetails,String status) {
        this.cName = cName;
        this.cImageUrl = cImageUrl;
        this.cOwnerId = cOwnerId;
        this.cDetails = cDetails;
        this.status = status;

    }

    public String getcStatus() {
        return status;
    }

    public void setcStatus(String status) {
        this.status = status;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcImageUrl() {
        return cImageUrl;
    }

    public void setcImageUrl(String cImageUrl) {
        this.cImageUrl = cImageUrl;
    }

    public String getcOwnerId() {
        return cOwnerId;
    }

    public void setcOwnerId(String cOwnerId) {
        this.cOwnerId = cOwnerId;
    }

    public String getcDetails() {
        return cDetails;
    }

    public void setcDetails(String cDetails) {
        this.cDetails = cDetails;
    }

    @Exclude
    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }
}
