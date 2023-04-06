package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrelloMapperTestSuite {
    public  TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard=new TrelloCard("Card","Test Trello card","top","123");
        //When
        TrelloCardDto trelloCardDto=trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Card",trelloCardDto.getName());
        assertEquals("Test Trello card",trelloCardDto.getDescription());
        assertEquals("top",trelloCardDto.getPos());
        assertEquals("123",trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto=new TrelloCardDto("Card","Test Trello card","top","123");
        //When
        TrelloCard trelloCard=trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Card",trelloCard.getName());
        assertEquals("Test Trello card",trelloCard.getDescription());
        assertEquals("top",trelloCard.getPos());
        assertEquals("123",trelloCard.getListId());
    }

    @ Test
    public void testMapToList(){
        //Given
        List<TrelloListDto> trelloListsDto=new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1","List 1",true));
        trelloListsDto.add(new TrelloListDto("2","List 2",false));
        //When
        List<TrelloList> trelloList=trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(2,trelloList.size());
        assertEquals("List 2",trelloList.get(1).getName());
        assertTrue(trelloList.get(0).isClosed());
    }

    @ Test
    public void testMapToListDto(){
        //Given
        List<TrelloList> trelloLists=new ArrayList<>();
        trelloLists.add(new TrelloList("1","List 10",true));
        trelloLists.add(new TrelloList("2","List 2",false));
        //When
        List<TrelloListDto> trelloListDto=trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2,trelloListDto.size());
        assertEquals("List 10",trelloListDto.get(0).getName());
        assertTrue(trelloListDto.get(0).isClosed());
    }

    @Test
    public void testMapToBoard(){
        //Given
        List<TrelloListDto> trelloListsDto=new ArrayList<>();
        List<TrelloBoardDto> trelloBoardsDto=new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1","List 1",true));
        trelloListsDto.add(new TrelloListDto("2","List 2",false));
        trelloBoardsDto.add(new TrelloBoardDto("1","Test",trelloListsDto));
        //When
        List<TrelloBoard> trelloBoard=trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(1,trelloBoard.size());
        assertEquals(2,trelloBoard.get(0).getLists().size());
        assertEquals("2",trelloBoard.get(0).getLists().get(1).getId());
        assertEquals("List 1",trelloBoard.get(0).getLists().get(0).getName());
        assertEquals("Test",trelloBoard.get(0).getName());
}

    @Test
    public void testMapToBoardDto(){
        //Given
        List<TrelloList> trelloLists=new ArrayList<>();
        List<TrelloBoard> trelloBoards=new ArrayList<>();
        trelloLists.add(new TrelloList("1","List 1",true));
        trelloLists.add(new TrelloList("2","List 2",false));
        trelloBoards.add(new TrelloBoard("1","Test",trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDto=trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1,trelloBoardDto.size());
        assertEquals(2,trelloBoardDto.get(0).getLists().size());
        assertEquals("2",trelloBoardDto.get(0).getLists().get(1).getId());
        assertEquals("List 1",trelloBoardDto.get(0).getLists().get(0).getName());
        assertEquals("Test",trelloBoardDto.get(0).getName());
    }
}
