package com.godknows.exemplosalvarparaum.repositories;

import com.godknows.exemplosalvarparaum.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
