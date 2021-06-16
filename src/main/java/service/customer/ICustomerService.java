package service.customer;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAll();
    Customer findByID(int id);
    void save(Customer customer);
    void delete(int id);
    void update(int id, Customer customer);
}
