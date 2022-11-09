package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.SubscribedBooks;


@Repository
public interface ISubscribedBookRepo extends JpaRepository<SubscribedBooks, Integer> {

}
