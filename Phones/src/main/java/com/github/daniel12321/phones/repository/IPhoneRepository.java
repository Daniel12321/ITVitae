package com.github.daniel12321.phones.repository;

import com.github.daniel12321.phones.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface IPhoneRepository extends JpaRepository<Phone, Integer> {

    Phone findById(int id);

    @Query(value = "SELECT p FROM Phone p WHERE p.brand LIKE '%:q%' OR p.model LIKE '%:q%' OR p.desc LIKE '%:q%'")
    Collection<Phone> search(@Param("q") String query);
}
