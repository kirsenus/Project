package net.kirsa.integration;

import net.kirsa.DAO.RecordsFromCsvDAO;
import net.kirsa.model.RecordsFromCsv;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Service {
    private static final Logger LOGGER = Logger.getLogger(Service.class);

    public void MailToDao(RecordsFromCsv listStrings) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-DataBase.xml");
        RecordsFromCsvDAO recordsFromCsvDAO = (RecordsFromCsvDAO)appContext.getBean("RecordsFromCsvDAO");
        recordsFromCsvDAO.insert(listStrings);
        LOGGER.info("Contents of the file is added to DB");

    }
}
