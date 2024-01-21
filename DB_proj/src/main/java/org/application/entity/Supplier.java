package org.application.entity;

public class Supplier {

    private int supplierId;
    private int warehouseId;
    private String supplierName;
    private String phoneNumber;
    private int listaDostawcowIdDostawcy;// XD

    public Supplier(int supplierId, int warehouseId, String supplierName, String phoneNumber, int listaDostawcowIdDostawcy) {
        this.supplierId = supplierId;
        this.warehouseId = warehouseId;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.listaDostawcowIdDostawcy = listaDostawcowIdDostawcy;
    }
    @Override
    public String toString() {
        return "Supplier{" +
                "idDostawcy=" + supplierId +
                ", idMagazynu=" + warehouseId +
                ", nazwaDostawcy='" + supplierName + '\'' +
                ", numerTelefonu='" + phoneNumber + '\'' +
                ", listaDostawcowIdDostawcy=" + listaDostawcowIdDostawcy +
                '}';
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getListaDostawcowIdDostawcy() {
        return listaDostawcowIdDostawcy;
    }

    public void setListaDostawcowIdDostawcy(int listaDostawcowIdDostawcy) {
        this.listaDostawcowIdDostawcy = listaDostawcowIdDostawcy;
    }
}
