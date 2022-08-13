import java.util.Objects;

public class PersonsInfo {
    private String id;
    private Cities city;

    public PersonsInfo(String id, Cities city) {
        this.id = id;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public Cities getCity() {
        return city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonsInfo)) return false;
        PersonsInfo that = (PersonsInfo) o;
        return Objects.equals(getId(), that.getId()) && getCity() == that.getCity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity());
    }

    @Override
    public String toString() {
        return "PersonsInfo{" +
                "id='" + id + '\'' +
                ", city=" + city +
                '}';
    }
}
