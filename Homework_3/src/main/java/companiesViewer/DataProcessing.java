package companiesViewer;

import companiesViewer.company.Asset;
import companiesViewer.company.Company;
import companiesViewer.company.Currency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataProcessing {
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

    //    Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
    public static void printCompList(LinkedList<Company> companies){
        companies.forEach(comp -> System.out.println(comp.getName() + " - " + formatter.format(comp.getFoundationDate())));
    }


    //    Вывести все ценные бумаги (их код, дату истечения и полное название организации-владельца),
    //    которые просрочены на текущий день, а также посчитать суммарное число всех таких бумаг;
    public static void printAssetsList(LinkedList<Company> companies) {
        long totalExpires = 0;
        for (Company comp : companies) {
            totalExpires = totalExpires + comp.getAssets().stream()
                    .filter(asset -> {
                        if (asset.getDate().before(new Date())) {
                            System.out.printf("ID: %s  expired: %s  belongs to: %s%n", asset.getId(), formatter.format(asset.getDate()), comp.getName());
                            return true;
                        } else return false;
                    }).count();
        }
        System.out.println("Total expired: " + totalExpires);
    }



    //    На запрос пользователя в виде даты «ДД.ММ.ГГГГ», «ДД.ММ.ГГ», «ДД/ММ/ГГГГ» и «ДД/ММ/ГГ» вывести
//    название и дату создания всех организаций, основанных после введенной даты;
    public static List<Company> getYoungerThan(LinkedList<Company> companies){
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy");
        String strDate;
        Date date = new Date();
        boolean validDate = false;
        while (!validDate){ //validation of input date
            System.out.println("Please input date: ");
            strDate = scanner.nextLine();
            try {
                date = formatter1.parse(strDate);
                validDate = true;
            } catch (java.text.ParseException e){validDate = false;}
            if(!validDate) {
                try {
                    date = formatter2.parse(strDate);
                    validDate = true;
                } catch (java.text.ParseException e) {
                    System.out.println("Incorrect date format!");
                }
            }
        }
        final Date fDate = date;
        return companies.stream().filter(company -> {
            if (company.getFoundationDate().after(fDate)){
                System.out.printf("%s  %s \n", company.getName(), formatter.format(company.getFoundationDate()));
                return true;
            } else return false;
        }).collect(Collectors.toList());
    }



    //    На запрос пользователя в виде кода валюты, например EU, USD, RUB и пр. выводить id и коды ценных
//    бумаг, использующих заданную валюту.
    public static List<Asset> getByCurrency(LinkedList<Company> companies){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.matches("(RUB)|(EUR)|(USD)"))
        {
            System.out.println("Please input currency (RUB/USD/EUR): ");
            input = scanner.nextLine();
            input = input.toUpperCase();
        }
        Currency curr = Currency.valueOf(input);
        List<Asset> currencyAssets = new LinkedList<>();
        companies.forEach(comp ->
                currencyAssets.addAll(comp.getAssets().stream().
                        filter(asset -> asset.getCurrency() == curr).collect(Collectors.toList())));
        System.out.println(currencyAssets);
        return currencyAssets;
    }
}
