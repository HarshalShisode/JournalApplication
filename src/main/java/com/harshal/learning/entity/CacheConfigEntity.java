package com.harshal.learning.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cache_config_details")
@Data
public class CacheConfigEntity {

    private String key;
    private String value;
}