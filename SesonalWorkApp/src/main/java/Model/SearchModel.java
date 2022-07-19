package Model;

import java.util.ArrayList;
import java.util.List;

public class SearchModel {

    Object filter;
    String type;

    @Override
    public String toString() {
        return "SearchModel{" +
                "filter=" + filter +
                ", type=" + type +
                '}';
    }

    public SearchModel(Object filter, String value) {
        this.filter = filter;
        this.type = value;
    }

}
