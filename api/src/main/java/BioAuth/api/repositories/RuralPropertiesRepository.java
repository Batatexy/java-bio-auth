package BioAuth.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import BioAuth.api.entities.RuralProperties;

@Repository
public interface RuralPropertiesRepository extends JpaRepository<RuralProperties, Long>, JpaSpecificationExecutor<RuralProperties> {

}
