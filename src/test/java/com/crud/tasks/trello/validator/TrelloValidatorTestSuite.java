package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TrelloValidatorTestSuite {

    TrelloValidator trelloValidator=new TrelloValidator();

    @Test
    public void testValidateCard() {
        //Given
        Logger logger;
        ListAppender<ILoggingEvent> listAppender;
        logger= (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        listAppender=new ListAppender<>();
        TrelloCard trelloCard=new TrelloCard("sample test","One of many test","top","123");
        listAppender.start();
        logger.addAppender(listAppender);

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        logger.detachAppender(listAppender);
        listAppender.stop();
        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getLevel, ILoggingEvent::getMessage)
                .containsExactly(
                        tuple(Level.INFO, "Someone is testing my application!")
                );
    }

    @Test
    void TestValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard1=new TrelloBoard("1","test",new ArrayList<>());

        TrelloBoard trelloBoard2=new TrelloBoard("2","sample test",new ArrayList<>());

        TrelloBoard trelloBoard3=new TrelloBoard("3","sample board",new ArrayList<>());
        List<TrelloBoard> trelloBoards=new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);

        //When
        List<TrelloBoard> resultList=trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(2,resultList.size());
        assertNotEquals("test",resultList.get(0).getName());
    }
}