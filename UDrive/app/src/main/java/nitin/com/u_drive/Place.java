package nitin.com.u_drive;
public class Place {
    private String movieName;
   public String pId;
    public Place(String movieName) {
        this.movieName = movieName;

    }


    public Place(int i,String pId)
    {
        this.pId=pId;
    }




    public String getpName() {
        return this.movieName;
    }

    public String getpId() {
        return this.pId;
    }
}