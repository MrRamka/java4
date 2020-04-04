package com.yabcompany.repository;

import com.yabcompany.models.MainUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/*
JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.

Their main functions are:

-CrudRepository mainly provides CRUD functions.
-PagingAndSortingRepository provides methods to do pagination and sorting records.
-JpaRepository provides some JPA-related methods such as flushing the persistence
 context and deleting records in a batch.
*/
public interface UserRepository extends JpaRepository<MainUser, Long> {

    // Find user by Email
    Optional<MainUser> findByEmail(String email);

    // Find users by gender
    Optional<MainUser> findAllByGender(boolean gender);

    // Find using query
    // Possibility: Using Sort.by("birthday")
    @Query("select user from MainUser user where user.country like '%:country%'")
    Optional<MainUser> findAllByCountryLike(@Param("country") String country, Sort sort);

    /*
    see https://habr.com/ru/post/435114/
    Also can use Pageable page
    PageRequest.of(1,3, Sort.by("birthday"))
    */
}
