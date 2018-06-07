package kz.enu.kztrdictionary.dao.trword;

import kz.enu.kztrdictionary.dao.*;

import java.util.*;

public interface TrWordDAO extends WordDAO {

    public List<String> translate(String word);

    public void addTranslate(String trWord, String[] translate) throws Exception;
}
