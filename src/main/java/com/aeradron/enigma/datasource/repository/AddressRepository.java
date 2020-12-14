package com.aeradron.enigma.datasource.repository;

import com.aeradron.enigma.datasource.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
