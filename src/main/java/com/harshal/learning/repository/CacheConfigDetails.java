package com.harshal.learning.repository;

import com.harshal.learning.entity.CacheConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CacheConfigDetails extends MongoRepository<CacheConfigEntity,ObjectId> {
}
