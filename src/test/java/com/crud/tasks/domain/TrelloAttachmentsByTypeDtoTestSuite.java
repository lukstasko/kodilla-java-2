package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloAttachmentsByTypeDtoTestSuite {

    @Test
    void getTrelloDto() {
        //Given
        TrelloDto trelloDto=new TrelloDto();
        trelloDto.setCard(1);
        trelloDto.setBoard(2);
        TrelloAttachmentsByTypeDto trelloAttachmentsByTypeDto=new TrelloAttachmentsByTypeDto();
        trelloAttachmentsByTypeDto.setTrelloDto(trelloDto);
        //When
        TrelloDto result=trelloAttachmentsByTypeDto.getTrelloDto();
        //Then
        assertEquals(2,result.getBoard());
        assertEquals(1,result.getCard());
    }
}