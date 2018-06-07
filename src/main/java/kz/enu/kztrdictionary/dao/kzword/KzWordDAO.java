package kz.enu.kztrdictionary.dao.kzword;

import kz.enu.kztrdictionary.dao.*;

import java.util.*;

public interface KzWordDAO extends WordDAO {

    public List<String> translate(String word);

    public void addTranslate(String kzWord, String[] translate) throws Exception;
}
