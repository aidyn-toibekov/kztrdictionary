package kz.enu.kztrdictionary.dao;

import kz.enu.kztrdictionary.dao.kzword.*;
import kz.enu.kztrdictionary.model.*;
import kz.enu.kztrdictionary.model.kzword.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;

import javax.sql.*;
import java.sql.*;
import java.util.*;

public class JdbcWordDAO implements WordDAO {

    public String tableName;

    public DataSource dataSource;

    public int insert(Word word) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource);
        jdbcInsert.withTableName(this.tableName).usingGeneratedKeyColumns("Primary_key");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("word", word.getWord());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return ((Number) key).intValue();
    }

    public Word fundByWord(String word) {
        String sql = "SELECT * FROM "+this.tableName+" WHERE word = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, word);
            Word w = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                w = new Word(
                        rs.getInt("ID"),
                        rs.getString("WORD")
                );
            }
            rs.close();
            ps.close();
            return w;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }


    /**
     * Поиск перевода
     * @param sql запрос на поиск
     * @param notFundWord текст если ничего не найдено
     * @return  список слов - переводов
     */
    public List<String> getTranslate(String sql, String notFundWord){
        List<String> result = new ArrayList<String>();

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("WORD"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
            if(result.size()==0){
                result.add(notFundWord);
            }
        }
        return result;
    }
}
