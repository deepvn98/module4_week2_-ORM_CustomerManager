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

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, Customer customer) {

    }







}
