package net.kirsa.integration.Util;

import java.io.*;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;

import net.kirsa.model.RecordsFromCsv;
import org.apache.log4j.Logger;


public class EmailParser {
    private static final Logger LOGGER = Logger.getLogger(EmailParser.class);



    public static RecordsFromCsv handleMessage(final Message mailMessage) {

        final Object content;
        final RecordsFromCsv list;
        try {
            content = mailMessage.getContent();

        } catch (IOException | MessagingException e ) {
            LOGGER.error("Error while retrieving the email contents.",e);
            throw new IllegalStateException("Error while retrieving the email contents.", e);
        }


        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            list =  handleMultipart( multipart);
        }else
         {
             LOGGER.error("Mail does not contain a .csv file");
             throw new IllegalStateException("This content type is not handled - " + content.getClass().getSimpleName());
        }
       return list;
    }


    public static RecordsFromCsv handleMultipart( Multipart multipart) {


        final int count;
        RecordsFromCsv rec= new RecordsFromCsv();
        try {
            count = multipart.getCount();
        }
        catch (MessagingException e) {
            throw new IllegalStateException("Error while retrieving the number of enclosed BodyPart objects.", e);
        }

        for (int i = 0; i < count; i++) {

            final BodyPart bp;
            try {
                bp = multipart.getBodyPart(i);
            }
            catch (MessagingException e) {
                throw new IllegalStateException("Error while retrieving body part.", e);
            }

            final String filename;

            try {

                filename    = bp.getFileName();
            }
            catch (MessagingException e) {
                throw new IllegalStateException("Unable to retrieve body part meta data.", e);
            }

            final Object content;


            try {
                content = bp.getContent();
            }
            catch (IOException e) {
                throw new IllegalStateException("Error while retrieving the email contents.", e);
            }
            catch (MessagingException e) {
                throw new IllegalStateException("Error while retrieving the email contents.", e);
            }


            if (content instanceof InputStream) {
                try {
                    if (filename != null && filename.endsWith(".csv")){
                        if (bp instanceof MimeBodyPart){
                            try {
                                ((MimeBodyPart) bp).saveFile(filename);

                                rec = ReadCSV.run(filename);
                                File f = new File(filename);
                                f.delete();

                            }catch (MessagingException e){
                                throw new IllegalStateException("Error while retrieving the email contents.", e);
                            }
                        }
                    }
                    } catch (IOException e) {
                          e.printStackTrace();
                    }
            }
        }
        return  rec;
    }
}
