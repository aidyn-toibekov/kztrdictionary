package kz.enu.kztrdictionary.model.dictionary;

public class Dictionary {

    private int id;

    private int kzWordId;

    private int trWordId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKzWordId() {
        return kzWordId;
    }

    public void setKzWordId(int kzwordId) {
        this.kzWordId = kzwordId;
    }

    public int getTrWordId() {
        return trWordId;
    }

    public void setTrWordId(int trwordId) {
        this.trWordId = trwordId;
    }
}
