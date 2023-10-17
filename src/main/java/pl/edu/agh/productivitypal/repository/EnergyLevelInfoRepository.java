package pl.edu.agh.productivitypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.productivitypal.model.EnergyLevelInfo;

@Repository
public interface EnergyLevelInfoRepository extends JpaRepository<EnergyLevelInfo, Integer> {
}
