package com.data.collection;

import com.data.collection.common.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CollectionApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(RedisUtils.get("age"));
    }

}
