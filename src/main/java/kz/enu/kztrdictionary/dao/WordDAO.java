package kz.enu.kztrdictionary.dao;

import kz.enu.kztrdictionary.model.*;

public interface WordDAO {

    /**
     * Вставка строки
     * @param word
     * @return id
     */
    public int insert(Word word);

    /**
     * Поиск по слову
     * @param word
     * @return
     */
    public Word fundByWord(String word);

}
