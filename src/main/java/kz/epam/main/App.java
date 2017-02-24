package kz.epam.main;


import kz.epam.validator.ValidatorXSD;

public class App {
    public static void main(String[] args) {
        ValidatorXSD.validate();
        new UI();

    }
}
