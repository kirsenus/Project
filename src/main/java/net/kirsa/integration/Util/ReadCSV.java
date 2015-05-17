package net.kirsa.integration.Util;

import net.kirsa.model.RecordsFromCsv;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


 public  class ReadCSV {

    private static final Logger LOGGER = Logger.getLogger(ReadCSV.class);

    public static RecordsFromCsv run(String csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        RecordsFromCsv listRecords = new RecordsFromCsv();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            listRecords.setFilename(csvFile);
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] record  = line.split(cvsSplitBy);
                listRecords.setString(record);

            }
            LOGGER.info(listRecords.getFilename()+ " file attached to e-mail received");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  listRecords;

    }
}
