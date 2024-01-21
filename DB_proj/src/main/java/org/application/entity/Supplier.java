package org.application.entity;

public class Supplier {

    private int idDostawcy;
    private int idMagazynu;
    private String nazwaDostawcy;
    private String numerTelefonu;
    private int listaDostawcowIdDostawcy;

    public Supplier(int idDostawcy, int idMagazynu, String nazwaDostawcy, String numerTelefonu, int listaDostawcowIdDostawcy) {
        this.idDostawcy = idDostawcy;
        this.idMagazynu = idMagazynu;
        this.nazwaDostawcy = nazwaDostawcy;
        this.numerTelefonu = numerTelefonu;
        this.listaDostawcowIdDostawcy = listaDostawcowIdDostawcy;
    }
    @Override
    public String toString() {
        return "Supplier{" +
                "idDostawcy=" + idDostawcy +
                ", idMagazynu=" + idMagazynu +
                ", nazwaDostawcy='" + nazwaDostawcy + '\'' +
                ", numerTelefonu='" + numerTelefonu + '\'' +
                ", listaDostawcowIdDostawcy=" + listaDostawcowIdDostawcy +
                '}';
    }

    public int getIdDostawcy() {
        return idDostawcy;
    }

    public void setIdDostawcy(int idDostawcy) {
        this.idDostawcy = idDostawcy;
    }

    public int getIdMagazynu() {
        return idMagazynu;
    }

    public void setIdMagazynu(int idMagazynu) {
        this.idMagazynu = idMagazynu;
    }

    public String getNazwaDostawcy() {
        return nazwaDostawcy;
    }

    public void setNazwaDostawcy(String nazwaDostawcy) {
        this.nazwaDostawcy = nazwaDostawcy;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public int getListaDostawcowIdDostawcy() {
        return listaDostawcowIdDostawcy;
    }

    public void setListaDostawcowIdDostawcy(int listaDostawcowIdDostawcy) {
        this.listaDostawcowIdDostawcy = listaDostawcowIdDostawcy;
    }
}
