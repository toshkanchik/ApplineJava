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
import java.util.LinkedList;

public class companiesViewer {
    public static LinkedList<Company> companies = new LinkedList<>();
//    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");


    public static void main(String[] args) {
        final Type REVIEW_TYPE = new TypeToken<LinkedList<Company>>() {
        }.getType();
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonReader reader;
        try {
//            reader = new JsonReader(new FileReader("D:\\StudyAppline\\ApplineJava\\Homework_3\\src\\main\\resources\\inputFile.json"));
            reader = new JsonReader(new FileReader("src\\main\\resources\\inputFile.json"));
            companies = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
//            printAll();
            printCompList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printCompList(){ //Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
        for (Company currComp : companies){
            System.out.println(currComp.getName() + " - " + formatter.format(currComp.getFoundationDate()));
        }
    }

    public static void printAssetsList(){
        //ToDo
    }

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
