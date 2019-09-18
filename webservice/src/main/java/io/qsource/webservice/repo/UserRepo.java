package io.qsource.webservice.repo;

import io.qsource.webservice.entitiy.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

    public User findByEmailIgnoreCase(String email);
    public User findByName(String name);
    public boolean existsByEmail(String email);
}
