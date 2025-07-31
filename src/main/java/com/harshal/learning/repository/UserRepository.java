package com.harshal.learning.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harshal.learning.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId>{

    User findByUserName(String userName);

    void deleteByUserName(String name);

}
