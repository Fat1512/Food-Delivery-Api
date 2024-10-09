package com.food.phat.repository;

import com.food.phat.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from role where name = ?1", nativeQuery = true)
    Role findByName(String name);
}
