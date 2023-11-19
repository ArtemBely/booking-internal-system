package com.example.bookingmodel.repositories;

import com.example.bookingmodel.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
