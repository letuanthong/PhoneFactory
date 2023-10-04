package com.devking;

import com.devking.dao.ManufactureDAO;
import com.devking.dao.PhoneDAO;
import com.devking.domain.Manufacture;
import com.devking.domain.Phone;
import com.devking.utils.HibernateUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private PhoneDAO phoneDao;
    private ManufactureDAO manufactureDAO;
    //private HibernateUtils hibernateUtils;
    public App(){
        this.phoneDao = new PhoneDAO();
        this.manufactureDAO = new ManufactureDAO();
        //this.hibernateUtils = new HibernateUtils();
    }

    private void addPhone(){
        Phone phone1 = new Phone(1L,"Iphone X", 2200,"White","China",100);
        Phone phone2 = new Phone(2L,"Iphone 11", 1500,"White","China",100);
        Phone phone3 = new Phone(3L,"Iphone 12", 1500,"White","Vietnam",100);
        Phone phone4 = new Phone(4L,"Iphone 13", 1000,"White","China",100);
        Phone phone5 = new Phone(5L,"Iphone 14", 1000,"Pink","Vietnam",100);
        Phone phone6 = new Phone(6L,"Samsung Z-Flip", 1000,"Pink","Korea",100);
        phoneDao.add(phone1);
        phoneDao.add(phone2);
        phoneDao.add(phone3);
        phoneDao.add(phone4);
        phoneDao.add(phone5);
        phoneDao.add(phone6);
    }

    private void addManu(){
        Manufacture manufacture1 = new Manufacture(1L,"Apple","Cupertino",100000);
        manufactureDAO.add(manufacture1);
        Manufacture manufacture2 = new Manufacture(2L,"Apple","US",100000);
        manufactureDAO.add(manufacture2);
        Manufacture manufacture3 = new Manufacture(3L,"Samsung","US",100000);
        manufactureDAO.add(manufacture3);
    }

    private void updatePhones(){
        Phone phone1 = phoneDao.get(1L);
        Phone phone2 = phoneDao.get(2L);
        Phone phone3 = phoneDao.get(3L);
        Phone phone4 = phoneDao.get(4L);
        Phone phone5 = phoneDao.get(5L);
        Phone phone6 = phoneDao.get(6L);
        Manufacture manufacture1 = manufactureDAO.get(1L);
        Manufacture manufacture2 = manufactureDAO.get(2L);
        Manufacture manufacture3 = manufactureDAO.get(3L);
        phone1.setManufacture(manufacture1);
        phoneDao.update(phone1);
        phone2.setManufacture(manufacture1);
        phoneDao.update(phone2);
        phone3.setManufacture(manufacture1);
        phoneDao.update(phone3);
        phone4.setManufacture(manufacture2);
        phoneDao.update(phone4);
        phone5.setManufacture(manufacture2);
        phoneDao.update(phone5);
        phone6.setManufacture(manufacture3);
        phoneDao.update(phone6);
    }

    private void initData(){
        addPhone();
        addManu();
        updatePhones();
    }

    public void showHighestPricePhone(){
        List<Phone> phoneList = phoneDao.getHighestPricePhone();
        if (phoneList != null){
            System.out.println("The highest price phones are: ");
            for (Phone phone: phoneList)
                phone.print();
        }else {
            System.out.println("Can not perform the query");
        }
    }

    public void showListByCountry(){
        List<Phone> phoneList = phoneDao.getPhoneByCountry();
        if (phoneList != null){
            System.out.println("Sorted by Country: ");
            for (Phone phone: phoneList)
                phone.print();
        }else {
            System.out.println("Can not perform the query");
        }
    }

    public void checkPriceVND(){
        List<Phone> phoneList = phoneDao.getAll();
        if (phoneList != null){
            System.out.println("Check price VND: ");
            for (Phone phone: phoneList)
                if(phoneDao.checkPrice(phone)){
                    System.out.println("Gia cua "+phone.getName()+" voi ID "+phone.getId()+" co gia lon hon 50tr");
                }else{
                    System.out.println("Gia cua "+phone.getName()+" voi ID "+phone.getId()+" co gia nho hon 50tr");
                }
                //phone.print();
        }else {
            System.out.println("Can not perform the query");
        }
    }

    public void getPinkPhone(){
        Phone phone = phoneDao.getPhoneHasPinkColor();
        if (phone != null){
            System.out.println("First phone Pink is: ");
            phone.print();
        }else {
            System.out.println("Can not perform the query");
        }
    }

    public void checkEmployees(){
        List<Manufacture> list = manufactureDAO.checkManufacture();
        if (list != null){
            System.out.println("Manufactures have Employees > 100: ");
            for (Manufacture manu: list)
                manu.print();
        }else {
            System.out.println("Can not perform the query");
        }
    }

    public void sumEmployee(){
        Integer sum = manufactureDAO.sumEmployee();
        System.out.println("Sum Employee of the Manufactures: ");
        System.out.println(sum);
    }

    public void getLastManufactureLocation() throws ManufactureDAO.InvalidOperationException {
        Manufacture manufacture = manufactureDAO.getLastManufactureByCountry();
        //System.out.println(manufacture);
        if (manufacture != null){
            //System.out.println(manufacture);
            System.out.println("Manufacture "+ manufacture.getName()+" voi ID "+ manufacture.getId() + " co location la: "+ manufacture.getLocation());
            //System.out.println(manufacture.print());
        }else {
            System.out.println("Can not perform the query");
            //throw new ManufactureDAO.InvalidOperationException();
        }
    }

    public static void main( String[] args ) throws ManufactureDAO.InvalidOperationException {
        System.out.println( "Hello World!" );
        App app = new App();
        app.initData();
        app.showHighestPricePhone();
        app.showListByCountry();
        app.checkPriceVND();
        app.getPinkPhone();

        app.checkEmployees();
        app.sumEmployee();
        app.getLastManufactureLocation();
    }
}
