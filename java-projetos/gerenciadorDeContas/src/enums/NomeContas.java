package enums;

public enum NomeContas {
    LUZ("Luz"),
    AGUA("Agua"),
    INTERNET("Internet"),
    FACULDADE("Faculdade"),
    MEI("Mei"),
    CELULAR("Celular");

    private final String name;


    NomeContas(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


}
