package kz.enu.kztrdictionary.dao.dictionary;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;

import javax.sql.*;
import java.util.*;

public class JdbcDictionaryDAO implements DictionaryDAO {

    public DataSource dataSource;

    public JdbcDictionaryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(int kzwordId, int trwordId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource);
        jdbcInsert.withTableName("DICTIONARY").usingGeneratedKeyColumns("Primary_key");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("kzwordid", kzwordId);
        parameters.put("trwordid", trwordId);

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    }
}
