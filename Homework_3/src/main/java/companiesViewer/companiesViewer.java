/*Задание №3
        На входе имеется файл в формате json, содержащий информацию о каком-то количестве организаций,
         в т.ч. названия, адреса, номера телефонов, ИНН, ОГРН, а также о ценных бумагах, которыми
         владеет каждая компания.
        Необходимо сформировать на основе исходного файла коллекцию объектов без потери информации,
        где каждый объект представляет одну организацию.
        Для сформированной коллекции:
        - Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
        - Вывести все ценные бумаги (их код, дату истечения и полное название организации-владельца),
          которые просрочены на текущий день, а также посчитать суммарное число всех таких бумаг;
        - На запрос пользователя в виде даты «ДД.ММ.ГГГГ», «ДД.ММ.ГГ», «ДД/ММ/ГГГГ» и «ДД/ММ/ГГ» вывести
          название и дату создания всех организаций, основанных после введенной даты;
        - На запрос пользователя в виде кода валюты, например EU, USD, RUB и пр. выводить id и коды ценных
          бумаг, использующих заданную валюту.
*/
package companiesViewer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import companiesViewer.company.*;
import companiesViewer.company.Currency;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class companiesViewer {
    private static LinkedList<Company> companies = new LinkedList<>();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");


    public static void main(String[] args) {
        final Type REVIEW_TYPE = new TypeToken<LinkedList<Company>>() {
        }.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("src\\main\\resources\\inputFile.json"));
            companies = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
//            printAll(); //Debug method. Prints all info
            printCompList();
            printAssetsList();
            getYoungerThan();
            getByCurrency();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


//    Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
    public static void printCompList(){
        companies.forEach(comp -> System.out.println(comp.getName() + " - " + formatter.format(comp.getFoundationDate())));
    }


//    Вывести все ценные бумаги (их код, дату истечения и полное название организации-владельца),
//    которые просрочены на текущий день, а также посчитать суммарное число всех таких бумаг;
    public static void printAssetsList() {
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
    public static List<Company> getYoungerThan(){
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
    public static List<Asset> getByCurrency(){
        boolean invalidEnum = true;
        System.out.println("Please input currency (RUB/USD/EUR): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.toUpperCase();
        Currency curr = Currency.USD;
        while(invalidEnum){//validation of input currency
            try{
                curr = Currency.valueOf(input);
                invalidEnum = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Please input currency(RUB/USD/EUR): ");
                input = scanner.nextLine();
                input = input.toUpperCase();
            }
        }
        Currency fCurr = curr;
        List<Asset> currencyAssets = new LinkedList<>();
                companies.forEach(comp ->
                        currencyAssets.addAll(comp.getAssets().stream().
                                filter(asset -> asset.getCurrency() == fCurr).collect(Collectors.toList())));
        System.out.println(currencyAssets);
        return currencyAssets;
    }


    public static void printAll(){//Debug method. Prints all information
        for(Company currComp: companies){
            LinkedList<Asset> asset;
            System.out.println(currComp.getName());
            System.out.println(currComp.getAddress());
            System.out.println(formatter.format(currComp.getFoundationDate()));
            System.out.println(currComp.getPhone());
            System.out.println("Assets:");
            asset = currComp.getAssets();
            for(Asset currAss: asset){
                System.out.println(currAss.getCurrency());
                System.out.println(currAss.getName());
                System.out.println(formatter.format(currAss.getDate()));
                System.out.println(currAss.getId());
                System.out.println(currAss.getQuantity());
                System.out.println("_ _ _ _ _");
            }
            System.out.println("___________________________");
        }
    }

}
