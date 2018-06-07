package kz.enu.kztrdictionary.model.trword;

import kz.enu.kztrdictionary.model.*;

import javax.persistence.*;

public class TrWord extends Word {

    public TrWord(String word) {
        this.setWord(word);
    }

    public TrWord(int id, String word) {
        this.setId(id);
        this.setWord(word);
    }

}
