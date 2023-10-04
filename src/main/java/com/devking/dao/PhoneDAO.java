package com.devking.dao;

import com.devking.domain.Phone;
import com.devking.repository.Repository;
import com.devking.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneDAO implements Repository<Phone, Long> {

    @Override
    public Long add(Phone item) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Long phoneID = (Long) session.save(item);
            //Phone phone = (Phone) session.save(item);
            //Long phoneID = session.find(Phone.class,item).getId();
            session.getTransaction().commit();
            //System.out.println("Phone " + item.getName() + " added");
            return phoneID;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Phone get(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Phone phone = session.find(Phone.class,id);
            session.getTransaction().commit();
            return phone;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Phone> phoneList = session.createQuery("FROM Phone",Phone.class).list();
            session.getTransaction().commit();
            return phoneList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean remove_id(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Phone phone = session.find(Phone.class,id);
            session.delete(phone);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession())  {
            session.beginTransaction();
            //Phone phone = session.find(Phone.class,item.getId());
            session.delete(item);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
//            Phone phone = session.find(Phone.class,item.getId());
            session.update(item);
            session.getTransaction().commit();
            return true;
//            Phone phone = session.find(Phone.class, item.getId());
//            if (phone != null) {
//                phone.setName(item.getName());
//                phone.setManufacture(item.getManufacture());
//
//                session.getTransaction().commit();
//                return true;
//            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Phone> getHighestPricePhone(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Phone order by price desc");
            //query.getMaxResults(10);
            query.setMaxResults(1);

            List<Phone> list = (List<Phone>) query.getResultList();

            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            //return Collections.emptyList();
        }
        return null;
    }

    public List<Phone> getPhoneByCountry() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Phone order by country asc , price desc");
            List<Phone> phones = query.getResultList();
            session.getTransaction().commit();
            return phones;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean checkPrice(Phone phone) {
        return phone.getPrice()*24000 > 50000000;
    }

    public Phone getPhoneHasPinkColor() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Phone where color like :color and price > :price");
            query.setParameter("color", "%pink%");
            query.setParameter("price", 625);
            query.setMaxResults(1);
            Phone phone = (Phone) query.getSingleResult();
            session.getTransaction().commit();
            return phone;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
