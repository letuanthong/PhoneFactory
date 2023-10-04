package com.devking.dao;

import com.devking.domain.Manufacture;
import com.devking.repository.Repository;
import com.devking.utils.HibernateUtils;
import org.hibernate.Session;
import javax.persistence.Query;

import java.util.List;

public class ManufactureDAO implements Repository<Manufacture, Long> {

    @Override
    public Long add(Manufacture item) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Long manuID = (Long) session.save(item);
            session.getTransaction().commit();
            //System.out.println("Manufacture " + item.getName() + " added");
            return manuID;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Manufacture get(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Manufacture manufacture = session.find(Manufacture.class,id);
            session.getTransaction().commit();
            return manufacture;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Manufacture> getAll() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Manufacture> manufactureList = session.createQuery("FROM Manufacture ",Manufacture.class).list();
            session.getTransaction().commit();
            return manufactureList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean remove_id(Long id) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Manufacture manufacture = session.find(Manufacture.class,id);
            session.delete(manufacture);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Manufacture item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession())  {
            session.beginTransaction();
            //Manufacture manufacture = session.find(Manufacture.class,item.getId());
            session.delete(item);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Manufacture item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
//            Manufacture manufacture = session.find(Manufacture.class,item.getId());
            session.update(item);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Manufacture> checkManufacture() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture where employee > 100");
            List<Manufacture> manufactures = query.getResultList();
            session.getTransaction().commit();
            return manufactures;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int sumEmployee() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select sum(employee) from Manufacture");
            int sum = Integer.parseInt(query.getSingleResult().toString());
            session.getTransaction().commit();
            return sum;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Manufacture getLastManufactureByCountry() throws InvalidOperationException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Manufacture where location like 'US'");

            List<Manufacture> manufactures = query.getResultList();
            Manufacture manufacture = manufactures.get(manufactures.size() - 1);
            session.getTransaction().commit();
            return manufacture;
        } catch (Exception e) {
            throw new InvalidOperationException("There is no producer that meets the above criteria");
        }
    }
    public static class InvalidOperationException extends Exception {
        public InvalidOperationException(String mess) {
            super(mess);
        }
    }

}
