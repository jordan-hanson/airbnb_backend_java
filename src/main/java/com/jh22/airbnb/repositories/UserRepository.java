package com.jh22.airbnb.repositories;

import com.jh22.airbnb.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
