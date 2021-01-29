package companiesViewer.company;

import java.util.Date;
import java.util.LinkedList;

public class Company {
    private String name;
    private String phone;
    private String address;
    private Date foundationDate;
    private LinkedList<Asset> assets;

    public Company(){

    }

    public Company(String name, String phone, String address, Date foundationDate, LinkedList<Asset> assets){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.foundationDate = foundationDate;
        this.assets = assets;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public LinkedList<Asset> getAssets() {
        return assets;
    }

}
