package com.user.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.SubscribedBooks;


@Repository
public interface ISubscribedBookRepo extends JpaRepository<SubscribedBooks, Integer> {

	Optional<SubscribedBooks> findById(Integer id);
	
	SubscribedBooks findBySubscriptionId(Integer sId);
	
	LocalDate getDateBySubscriptionId(Integer sId);
}
