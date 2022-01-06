package be.itf.builderservice.repository;

import be.itf.builderservice.model.Build;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildRepository extends MongoRepository<Build, String> {
    Build findByNameEquals(String name);

    void deleteByNameEquals(String name);

    List<Build> findAllByNameIgnoreCaseContaining(String name);

    List<Build> findAllByUsernameIgnoreCaseContaining(String name);

    List<Build> findAllByPrimaryWeaponNameIgnoreCaseContainingOrSecondaryWeaponNameIgnoreCaseContaining(String name1, String name2);

    List<Build> findAllByTagEquals(String name);
}
