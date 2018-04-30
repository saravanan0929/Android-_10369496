package info.dublinbikes.bikes;

/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/

public class bikes
{

        public String name;
        public int empty_slots;
        public String id;
        public String address;
        public int slots;
        public String status;
        public int uid;
        public int free_bikes;
        public double lat;
        public double lan;

        public bikes(){}
         public bikes(String name, int free_bikes, int slots,int empty_slots,String id,String address,String status,int uid,double lat,double lan) {
            this.name = name;
            this.free_bikes = free_bikes;
            this.slots = slots;
            this.empty_slots=empty_slots;
            this.id=id;
            this.address=address;
            this.status=status;
            this.uid=uid;
            this.lat=lat;
            this.lan=lan;

        }

        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getaddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }



    public int getfree_bikes() {
            return free_bikes;
        }

        public void setfree_bikes(int free_bikes) {
            this.free_bikes = free_bikes;
        }

        public int getslots() {
            return slots;
        }

        public void setslots(int slots) {
            this.slots = slots;
        }


    public double getlat() {
        return lat;
    }

    public void setlat(double lat) {
        this.lat = lat;
    }

    public double getlan() {
        return lan;
    }

    public void setlan(double lan) {
        this.lan = lan;
    }

}
