package com.amaka.springexcel.repository;

import com.amaka.springexcel.data.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findBranchByCode(String branchCode);
    List<Branch> findAllByCity(String branchCity);
    Branch findBranchByEmail(String branchEmail);
}