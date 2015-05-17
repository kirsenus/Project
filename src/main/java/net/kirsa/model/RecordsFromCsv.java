package net.kirsa.model;

import java.util.ArrayList;
import java.util.List;


public class RecordsFromCsv {

    private  String filename;
    private List <String[]> listOfStrings= new ArrayList<>();


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String[]> getListOfStrings() {
            return listOfStrings;
        }
    public String[] getStingbyNum(int i){

        return listOfStrings.get(i);
    }


    public void setString(String[] record) {
        listOfStrings.add(record);
    }
}
