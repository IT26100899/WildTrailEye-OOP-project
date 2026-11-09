package com.wildtrail.modules.users;

import com.wildtrail.exception.ApiException;
import com.wildtrail.util.RequestValues;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
// CustomerService: Service for the WildTrail application.
public class CustomerService {
    private final JdbcCustomerRepository people;

    public CustomerService(JdbcCustomerRepository people) {
        this.people = people;
    }

    // Handles customers using the existing project logic.
    public List<Map<String, Object>> customers() {
        return this.people.customers();
    }

    @org.springframework.transaction.annotation.Transactional
    // Updates existing data without changing the stored format.
    public Map<String, Object> updateCustomer(int id, Map<String, Object> body) {
        Map<String, Object> values = Map.of("fullName", RequestValues.text(body, "fullName", 120), "email", RequestValues.email(body), "phone", RequestValues.optionalText(body, "phone"), "country", RequestValues.optionalText(body, "country"));
        Map<String, Object> updated = this.people.updateCustomer(id, values);
        if (updated == null) {
            throw ApiException.notFound("Customer with id " + id + " was not found");
        }
        return updated;
    }

    @org.springframework.transaction.annotation.Transactional
    // Deletes a matching record according to existing rules.
    public void deleteCustomer(int id) {
        if (!this.people.deleteCustomer(id)) {
            throw ApiException.notFound("Customer with id " + id + " was not found");
        }
    }

}
