package xyz.iwasacar.api.domain.insurances.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.insurances.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
