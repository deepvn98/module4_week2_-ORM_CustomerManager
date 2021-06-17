package service.customer;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Customer> getAll() {
        String sql = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findByID(int id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
//            Customer customer1 = findByID(customer.getId());
//            customer1.setId(customer.getId());
//            customer1.setName(customer.getName());
//            customer1.setAddress(customer.getAddress());
//            customer1.setImg(customer.getImg());
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
    @Override
    public void delete(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(findByID(id));
            transaction.commit();
        } finally {
            session.close();
        }


    }

    @Override
    public void update(int id, Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer customer1 = findByID(id);
            customer1.setName(customer.getName());
            customer1.setAddress(customer.getAddress());
            customer1.setImg(customer.getImg());
            session.update(customer1);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }


    }


}
