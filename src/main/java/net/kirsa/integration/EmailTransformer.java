package net.kirsa.integration;

import net.kirsa.integration.Util.EmailParser;
import net.kirsa.model.RecordsFromCsv;
import org.apache.log4j.Logger;

import javax.mail.Message;

/**
 * Created by Дом on 20.04.2015.
 */
public class EmailTransformer {

    private static final Logger LOGGER = Logger.getLogger(EmailTransformer.class);
   // @Transformer
    public RecordsFromCsv transformit(Message mailMessage) {

        final RecordsFromCsv listRecords;

        listRecords = EmailParser.handleMessage(mailMessage);

        return listRecords;
    }
}
