package com.antonio.gestao_vagas.modules.candidate.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.antonio.gestao_vagas.modules.candidate.entity.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
