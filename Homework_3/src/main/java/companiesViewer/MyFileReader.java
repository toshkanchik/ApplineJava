package companiesViewer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import companiesViewer.company.Company;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.LinkedList;

public class MyFileReader {
    public static LinkedList<Company> getLListFromFile(){
        final Type REVIEW_TYPE = new TypeToken<LinkedList<Company>>() {
        }.getType();
        LinkedList<Company> companies = new LinkedList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JsonReader reader;
        try {
            reader = new JsonReader(new java.io.FileReader("src\\main\\resources\\inputFile.json"));
            companies = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
        } catch (FileNotFoundException|com.google.gson.JsonIOException|com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
