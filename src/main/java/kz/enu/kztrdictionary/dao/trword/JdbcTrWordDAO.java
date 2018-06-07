package kz.enu.kztrdictionary.dao.trword;

import kz.enu.kztrdictionary.dao.*;
import kz.enu.kztrdictionary.dao.dictionary.*;
import kz.enu.kztrdictionary.dao.kzword.*;
import kz.enu.kztrdictionary.model.*;
import kz.enu.kztrdictionary.model.kzword.*;
import kz.enu.kztrdictionary.model.trword.*;
import org.springframework.beans.factory.annotation.*;

import javax.sql.*;
import java.sql.*;
import java.util.*;

public class JdbcTrWordDAO extends JdbcWordDAO implements TrWordDAO  {

    @Autowired
    DictionaryDAO dictionaryDAO;

    @Autowired
    KzWordDAO kzWordDAO;

    public JdbcTrWordDAO(DataSource dataSource) {
        this.tableName = "TRWORD";
        this.dataSource = dataSource;
    }

    public List<String> translate(String word) {
        String sql = "select t.word from dictionary d\n" +
                " inner join kzword k on k.id = d.kzwordid\n" +
                " inner join trword t on t.id = d.trwordid\n" +
                " where  upper(t.word) like upper('%"+word+"%')";


        String notFoundText = "Çeviri bulunamadı!";

        return getTranslate(sql,notFoundText);
    }

    public void addTranslate(String trWord, String[] translate) throws Exception {

        if (fundByWord(trWord)!=null){
            throw new Exception("Is Contained!");
        }

        List<Integer> kzIds = new ArrayList<>();
        int trId= insert(new TrWord(trWord));
        for (String s : translate) {
            kzIds.add(kzWordDAO.insert(new KzWord(s)));
        }

        for (Integer kzId : kzIds) {
            dictionaryDAO.insert(kzId, trId);
        }
    }
}
