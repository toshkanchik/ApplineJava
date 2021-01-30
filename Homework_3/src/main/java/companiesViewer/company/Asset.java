package companiesViewer.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Asset {


    int id;
    String name;
    Date date;
    int quantity;
    Currency currency;


    public Asset(int id, String name, Date date, int quantity, Currency currency) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.currency = currency;
    }

    public Asset() {
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("ID: %s; Name: %s\n",this.id, this.name);
    }
}

