package com.mindhub.crud.repositories;

import com.mindhub.crud.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
