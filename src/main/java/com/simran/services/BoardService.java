package com.simran.services;

import com.simran.exceptions.BoardListNotFoundException;
import com.simran.exceptions.BoardNotFoundException;
import com.simran.exceptions.CardNotFoundException;
import com.simran.exceptions.UserNotFoundException;
import com.simran.models.*;
import lombok.NonNull;


import java.util.*;

public class BoardService
{

    private final Map<String, Board> boardMap;

    public BoardService()
    {
        this.boardMap = new HashMap<>();
    }

    public String createBoard(@NonNull String name, BoardPrivacyStatus privacyStatus)
    {
        if(privacyStatus == null)
        {
            privacyStatus = BoardPrivacyStatus.PUBLIC;
        }

        Board board = new Board(name, privacyStatus);
        boardMap.put(board.getId(),board);
        return board.getId();
    }

    public String addMemberToBoard(@NonNull String boardId,@NonNull String name,@NonNull String email)
    {
        if(!boardMap.containsKey(boardId))
        {
            throw new BoardNotFoundException();
        }
       User user = new User(name, email);
       Board board =  boardMap.get(boardId);
       board.getMemberMap().put(user.getId(), user);
       return user.getId();
    }

    public String removeMemberFromBoard(@NonNull String boardId,@NonNull String userId)
    {
        if(!boardMap.containsKey(boardId))
        {
            throw new BoardNotFoundException();
        }
        Board board =  boardMap.get(boardId);
        if(!board.getMemberMap().containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        board.getMemberMap().remove(userId);
        return userId;
    }

    public String addListToBoard(@NonNull String boardId,@NonNull String name)
    {
        if(!boardMap.containsKey(boardId))
        {
            throw new BoardNotFoundException();
        }

        BoardList boardList = new BoardList(name);
        Board board =  boardMap.get(boardId);
        board.getBoardListMap().put(boardList.getId(),boardList);

        return boardList.getId();
    }

    public Card addCardToBoardList(@NonNull String boardId,@NonNull String boardListId, @NonNull String cardName,@NonNull String description)
    {
        if(!boardMap.containsKey(boardId))
        {
            throw new BoardNotFoundException();
        }

        Board board =  boardMap.get(boardId);
        Card card = new Card(cardName,description);

        if(!board.getBoardListMap().containsKey(boardListId))
        {
            throw new BoardListNotFoundException();
        }
        board.getBoardListMap().get(boardListId).getCardMap().put(card.getId(),card);

        return card;
    }

    public Card assignUser(@NonNull String boardId, @NonNull String listId,@NonNull String cardId, @NonNull String userId)
    {
        if(!boardMap.get(boardId).getMemberMap().containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        User user = boardMap.get(boardId).getMemberMap().get(userId);
        if(!this.boardMap.get(boardId).getBoardListMap().get(listId).getCardMap().containsKey(cardId))
        {
            throw new CardNotFoundException();
        }
        Card card = this.boardMap.get(boardId).getBoardListMap().get(listId).getCardMap().get(cardId);
        card.setAssignedUser(user);
        return card;
    }

    public Board getBoard(@NonNull String boardId)
    {
        if(!boardMap.containsKey(boardId))
        {
            throw new BoardNotFoundException();
        }
        return boardMap.get(boardId);
    }

    public List<Board> getAllBoards()
    {
        return new ArrayList<>(this.boardMap.values());
    }

    public BoardList getList(@NonNull String listId)
    {
        Collection<Board> boards = this.boardMap.values();
        for (Board board:boards)
        {
            if(board.getBoardListMap().containsKey(listId))
            {
                return board.getBoardListMap().get(listId);
            }
        }

        throw new BoardListNotFoundException();
    }

    public Card getCard(@NonNull String cardId)
    {
        Collection<Board> boards = this.boardMap.values();

        for (Board board:boards)
        {
            Collection<BoardList> lists= board.getBoardListMap().values();
            for (BoardList list: lists) {
                if(list.getCardMap().containsKey(cardId))
                {
                    return list.getCardMap().get(cardId);
                }
            }
        }

        throw new CardNotFoundException();
    }

    public void deleteBoard(@NonNull String boardId)
    {
        boardMap.remove(boardId);
    }

    public void deleteList(@NonNull String listId)
    {
        Collection<Board> boards = this.boardMap.values();
        for (Board board:boards)
        {
            if(board.getBoardListMap().containsKey(listId))
            {
                board.getBoardListMap().remove(listId);
                return;
            }
        }

        throw new BoardListNotFoundException();
    }

    public void deleteCard(@NonNull String cardId)
    {
        Collection<Board> boards = this.boardMap.values();

        for (Board board:boards)
        {
            Collection<BoardList> lists= board.getBoardListMap().values();
            for (BoardList list: lists) {
                if(list.getCardMap().containsKey(cardId))
                {
                    list.getCardMap().remove(cardId);
                    return;
                }
            }
        }

        throw new CardNotFoundException();
    }

}
