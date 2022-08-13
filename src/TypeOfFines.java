public enum TypeOfFines {
    INCOME ("Подоходный"),
    AUTO ("На авто"),
    PROPERTY ("На недвижимость"),
    LAND ("Земельный");

    final private String nameOfTax;

    TypeOfFines(String nameOfTax) {
        this.nameOfTax = nameOfTax;
    }
}
