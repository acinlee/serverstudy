package com.sever.test.testserver.repository;

import com.sever.test.testserver.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, String> {
}
