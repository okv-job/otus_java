package ru.oleg.korytnikov;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String company;
    private String phone;


    public Customer(String name, String company, String phone) {
        this.name = name;
        this.company = company;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCustomerCsvData(){
        List<String> data = new ArrayList<>();
        data.add(name);
        data.add(company);
        data.add(phone);
        return data;
    }
}
