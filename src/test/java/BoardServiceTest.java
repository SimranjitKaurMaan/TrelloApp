import com.simran.models.Board;
import com.simran.models.Card;
import com.simran.models.BoardPrivacyStatus;
import com.simran.services.BoardService;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class BoardServiceTest
{
    @Test
    public void defaultTest()
    {
        BoardService boardService = new BoardService();
        boardService.getAllBoards();
        String boardId = boardService.createBoard("B1", BoardPrivacyStatus.PRIVATE);
        String userId1 = boardService.addMemberToBoard(boardId,"U1","u1@gmail.com");
        String userId2 = boardService.addMemberToBoard(boardId,"U2","u2@gmail.com");
        String listId1 = boardService.addListToBoard(boardId,"L1");
        String listId2 = boardService.addListToBoard(boardId,"L2");
        Card card1 = boardService.addCardToBoardList(boardId,listId1,"C1","description 1");
        Card card2 = boardService.addCardToBoardList(boardId,listId2,"C2","description 2");
        boardService.assignUser(boardId,listId1,card1.getId(),userId1);
        boardService.assignUser(boardId,listId2,card2.getId(),userId2);
        List<Board> boards = boardService.getAllBoards();
        boardService.removeMemberFromBoard(boardId,userId1);
        System.out.println(boards);
        System.out.println(boardService.getBoard(boardId));
        System.out.println(boardService.getCard(card1.getId()));
        System.out.println(boardService.getList(listId1));
        boardService.deleteCard(card1.getId());
        boardService.deleteList(listId1);
        boardService.deleteBoard(boardId);
        System.out.println(boardService.getAllBoards());
    }
}
