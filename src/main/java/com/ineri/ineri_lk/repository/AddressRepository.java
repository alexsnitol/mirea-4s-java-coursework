package com.ineri.ineri_lk.repository;

import com.ineri.ineri_lk.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
