package org.example;


import org.example.dao.CarDao;
import org.example.dao.CarDaoImpl;
import org.example.model.User;

public class Main {
    public static void main(String[] args) {
        CarDao carDao = new CarDaoImpl();
        User dno = new User();
        dno.setYear(123);
        dno.setName("dno-1");
        System.out.println(carDao.get(2L));
        //System.out.println(carDao.save(dno));
    }
}