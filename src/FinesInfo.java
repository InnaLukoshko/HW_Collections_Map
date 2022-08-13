import java.util.Objects;

public class FinesInfo {
    private TypeOfFines typeOfFine;
    private Long numberOfFine;

    public FinesInfo(TypeOfFines typeOfFine, Long numberOfFine) {
        this.typeOfFine = typeOfFine;
        this.numberOfFine = numberOfFine;
    }

    public TypeOfFines getTypeOfFine() {
        return typeOfFine;
    }

    public Long getNumberOfFine() {
        return numberOfFine;
    }

    public void setTypeOfFine(TypeOfFines typeOfFine) {
        this.typeOfFine = typeOfFine;
    }

    public void setNumberOfFine(Long numberOfFine) {
        this.numberOfFine = numberOfFine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinesInfo)) return false;
        FinesInfo finesInfo = (FinesInfo) o;
        return getTypeOfFine() == finesInfo.getTypeOfFine() && Objects.equals(getNumberOfFine(), finesInfo.getNumberOfFine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeOfFine(), getNumberOfFine());
    }

    @Override
    public String toString() {
        return "FinesInfo{" +
                "typeOfFine=" + typeOfFine +
                ", numberOfFine=" + numberOfFine +
                '}';
    }
}

