package kz.enu.kztrdictionary.model.kzword;

import kz.enu.kztrdictionary.model.*;

public class KzWord  extends Word {

    public KzWord(String word) {
        this.setWord(word);
    }


    public KzWord(int id, String word) {
        this.setId(id);
        this.setWord(word);
    }

}
