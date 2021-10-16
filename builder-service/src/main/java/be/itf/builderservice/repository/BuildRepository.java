package be.itf.builderservice.repository;

import be.itf.builderservice.model.Build;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildRepository extends MongoRepository<Build, String> {
    Build findByNameEquals(String name);
    void deleteByNameEquals(String name);
}
