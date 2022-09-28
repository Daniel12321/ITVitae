package com.github.daniel12321.phones.service;

import com.github.daniel12321.phones.model.Phone;
import com.github.daniel12321.phones.repository.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    @Autowired
    private IPhoneRepository repo;

    public Phone getPhone(int id) {
        return this.repo.findById(id);
    }

    public void addPhone(Phone phone) {
        this.repo.save(phone);
    }

    public void deletePhone(int id) {
        this.repo.delete(this.repo.findById(id));
    }

    public Collection<Phone> getAll() {
        return this.repo.findAll();
    }

    public Collection<Phone> search(String query) {
//        return this.repo.search(query);

        return this.getAll().stream().filter(p -> this.isResultFor(p, query.toLowerCase())).collect(Collectors.toList());
    }

    private boolean isResultFor(Phone phone, String query) {
        return phone.getBrand().toLowerCase().contains(query)
                || phone.getModel().toLowerCase().contains(query)
                || phone.getDesc().toLowerCase().contains(query);
    }
}
