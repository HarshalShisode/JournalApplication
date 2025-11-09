package com.harshal.learning.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailserviceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail(){
        emailService.sendEmail("56rajputharshal@gmail.com","test email","Hi  I am very happy");
    }

}
