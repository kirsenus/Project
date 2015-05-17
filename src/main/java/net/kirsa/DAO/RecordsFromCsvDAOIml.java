package net.kirsa.DAO;

import net.kirsa.model.RecordsFromCsv;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Object;
import java.util.Objects;



//@Repository
public class RecordsFromCsvDAOIml implements RecordsFromCsvDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(RecordsFromCsv record) {
        Integer numID;

        String sql_1 = "INSERT INTO table_name_file " +
                "(name) VALUES (?) RETURNING id";

        String sql_2 = "INSERT INTO records " +
                "(phone,date,status,id_file_name,time_date_written) VALUES (?,?,?,?,?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        numID = jdbcTemplate.queryForObject(sql_1, new Object[]{record.getFilename()}, Integer.class);

        jdbcTemplate.batchUpdate(sql_2, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String[] customer = record.getStingbyNum(i);
                ps.setString(1, customer[0]);
                ps.setString(2, customer[1]);
                ps.setString(3, customer[2]);
                ps.setInt(4, numID);
                ps.setTimestamp(5,getCurrentTimeStamp());

            }

            @Override
            public int getBatchSize() {
                return record.getListOfStrings().size();
            }
        });

    }
    private static java.sql.Timestamp getCurrentTimeStamp() {

        Date today = new Date();
        return new Timestamp(today.getTime());

    }
    }

