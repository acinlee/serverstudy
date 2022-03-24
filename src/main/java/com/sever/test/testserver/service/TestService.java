package com.sever.test.testserver.service;

import com.sever.test.testserver.model.TestEntity;
import com.sever.test.testserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestEntity join(String userId, String userPw) {
        TestEntity test = TestEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .build();
        return testRepository.save(test);
    }

    public boolean login(String userId, String userPw) {
        TestEntity test = testRepository.findById(userId).orElse(null);
        if( test != null ) {
            if ( (userPw).equals(test.getUserPw())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public Optional<TestEntity> getUser(String userId) {
        Optional<TestEntity> testObject = testRepository.findById(userId);

        return testRepository.findById(userId);
    }
}
