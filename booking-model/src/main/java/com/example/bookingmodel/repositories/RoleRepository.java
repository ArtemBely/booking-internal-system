package com.example.bookingmodel.repositories;

import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
