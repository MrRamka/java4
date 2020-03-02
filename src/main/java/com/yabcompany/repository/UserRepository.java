package com.yabcompany.repository;

import com.yabcompany.models.MainUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MainUser, Long> {

}
