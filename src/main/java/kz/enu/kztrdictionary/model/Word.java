package kz.enu.kztrdictionary.model;

public class Word {

    public Word() {
    }

    public Word(int id, String word) {
        this.id = id;
        this.word = word;
    }

    private int id;

    private String word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
