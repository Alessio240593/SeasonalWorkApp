package Model;

import java.util.Objects;

public class SearchModel {
    private Object filter;
    private String type;
    private String objectType;

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
        this.objectType = null;
    }

    public SearchModel(Object filter, String value, String objectType) {
        this.filter = filter;
        this.type = value;
        this.objectType = objectType.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchModel that = (SearchModel) o;
        return Objects.equals(filter, that.filter) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, type);
    }

    public Object getFilter() {
        return filter;
    }

    public void setFilter(Object filter) {
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}
