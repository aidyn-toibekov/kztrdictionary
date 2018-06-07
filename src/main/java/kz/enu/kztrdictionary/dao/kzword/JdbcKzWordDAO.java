package kz.enu.kztrdictionary.dao.kzword;

import kz.enu.kztrdictionary.dao.*;
import kz.enu.kztrdictionary.dao.dictionary.*;
import kz.enu.kztrdictionary.dao.trword.*;
import kz.enu.kztrdictionary.model.kzword.*;
import kz.enu.kztrdictionary.model.trword.*;
import org.springframework.beans.factory.annotation.*;

import javax.sql.*;
import java.util.*;

public class JdbcKzWordDAO extends JdbcWordDAO implements KzWordDAO {

    @Autowired
    DictionaryDAO dictionaryDAO;

    @Autowired
    TrWordDAO trWordDAO;

    public JdbcKzWordDAO(DataSource dataSource) {
        this.tableName = "KZWORD";
        this.dataSource = dataSource;
    }


    public List<String> translate(String word) {
        String sql = "select t.word from dictionary d\n" +
                " inner join kzword k on k.id = d.kzwordid\n" +
                " inner join trword t on t.id = d.trwordid\n" +
                " where  upper(k.word) like upper('%"+word+"%')";


        String notFoundText = "Аударма табылмады!";

        return getTranslate(sql,notFoundText);
    }


    public void addTranslate(String kzWord, String[] translate) throws Exception {
        if (fundByWord(kzWord)!=null){
            throw new Exception("Is Contained!");
        }


        List<Integer> trIds = new ArrayList<>();
        int kzId= insert(new KzWord(kzWord));
        for (String s : translate) {
            trIds.add(trWordDAO.insert(new TrWord(s)));
        }

        for (Integer trId : trIds) {
            dictionaryDAO.insert(kzId, trId);
        }
    }
}
