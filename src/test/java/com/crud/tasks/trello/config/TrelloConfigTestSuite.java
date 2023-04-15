package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testGetTrelloApiEndpoint() {
        assertNotNull(trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    void testGetTrelloAppKey() {
        assertNotNull(trelloConfig.getTrelloAppKey());
    }

    @Test
    void testGetTrelloToken() {
        assertNotNull(trelloConfig.getTrelloToken());
    }

    @Test
    void getTrelloUsername() {
        assertNotNull(trelloConfig.getTrelloUsername());
    }
}