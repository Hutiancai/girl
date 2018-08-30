package com.firstboot.repository;

import com.firstboot.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewGirlRepository extends JpaRepository<Girl,String>, JpaSpecificationExecutor<Girl> {
}
