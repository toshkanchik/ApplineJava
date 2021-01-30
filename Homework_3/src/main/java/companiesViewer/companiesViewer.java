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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class companiesViewer {
    public static LinkedList<Company> companies = new LinkedList<>();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");


    public static void main(String[] args) {
        final Type REVIEW_TYPE = new TypeToken<LinkedList<Company>>() {
        }.getType();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("src\\main\\resources\\inputFile.json"));
            companies = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
//            printAll();
            printCompList();
            printAssetsList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getYoungerThan();
    }


//    Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
    public static void printCompList(){
        for (Company currComp : companies){
            System.out.println(currComp.getName() + " - " + formatter.format(currComp.getFoundationDate()));
        }
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
        while (!validDate){
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
                    validDate = false;
                }
            }
        System.out.println(date);
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
    public static void getByCurrency(){

    }

//        compan.stream().forEach(company -> {
//            System.out.println(company.getName());
//        });

    public static void printAll(){
        //ToDo Comment this method
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        for(Company currComp: companies){
            LinkedList<Asset> ass;
            System.out.println(currComp.getName());
            System.out.println(currComp.getAddress());
            System.out.println(formatter.format(currComp.getFoundationDate()));
            System.out.println(currComp.getPhone());
            System.out.println("Assets:");
            ass = currComp.getAssets();
            for(Asset currAss: ass){
                System.out.println(currAss.getCurrency());
                System.out.println(currAss.getName());
                System.out.println(formatter.format(currAss.getDate()));
                System.out.println(currAss.getId());
                System.out.println(currAss.getQuantity());
                System.out.println("_ _ _ _ _");
            }

        }
    }

}
