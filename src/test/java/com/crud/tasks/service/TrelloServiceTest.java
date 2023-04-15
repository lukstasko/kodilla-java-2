package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService emailService;
    @Mock
    AdminConfig adminConfig;
    @Mock
    RestTemplate restTemplate;

    @Test
    void fetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardDtoList=new ArrayList<>();
        boardDtoList.add(new TrelloBoardDto("1","Board 1",new ArrayList<>()));
        boardDtoList.add(new TrelloBoardDto("2","Board 2",new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);
        //When
        List<TrelloBoardDto> resultList=trelloService.fetchTrelloBoards();
        //Then
        assertEquals(2 ,resultList.size());
        assertEquals("2",resultList.get(1).getId());
        assertEquals("Board 1",resultList.get(0).getName());
        assertEquals(new ArrayList<>(),resultList.get(1).getLists());
    }

    @Test
    void TestCreateTrelloCard() {
        // Given
        CreatedTrelloCardDto createdTrelloCardDto=new CreatedTrelloCardDto("1","Trello card","shortUrl");
        TrelloCardDto trelloCardDto=new TrelloCardDto("1","Trello card","top","123");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("mail");
        doNothing().when(emailService).send(any());
        // When
        CreatedTrelloCardDto result=trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("1",result.getId());
        assertEquals("Trello card",result.getName());
        assertEquals("shortUrl",result.getShortUrl());
      }
}