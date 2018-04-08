package ru.korytnikov.oleg.dao;

public class Option {
    private Object type;
    private String name;
    private Object value;

    public Option() {
    }

    public Option(Object type, String name) {
        this.type = type;
        this.name = name;
    }

    public Option(Object type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Option{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
