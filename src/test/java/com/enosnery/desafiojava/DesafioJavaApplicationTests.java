package com.enosnery.desafiojava;

import com.enosnery.desafiojava.controller.MainController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafioJavaApplicationTests {

    private MainController mainController;

    @BeforeEach
    private void initialize(){
        mainController = new MainController();
    }

    @Test
    void contextLoads() {

    }

}
