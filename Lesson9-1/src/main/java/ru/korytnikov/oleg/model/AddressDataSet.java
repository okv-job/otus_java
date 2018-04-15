package ru.korytnikov.oleg.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Address")
public class AddressDataSet extends DataSet {


    @Column(name = "address")
    private String address;

    public AddressDataSet() {
    }

    public AddressDataSet(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "address='" + address + '\'' +
                '}';
    }
}
