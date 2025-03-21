package com.kh.mfw.board.model.service;

import com.kh.mfw.board.model.dao.BoardDAO;
import com.kh.mfw.board.model.dto.BoardDTO;
import static com.kh.mfw.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class BoardService {
	
	private BoardDAO boardDao = new BoardDAO();

	public void insertBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		// 유효성 검증 => 헀다고 침
		
		boardDao.insertBoard(sqlSession, board);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	public Map<String, Object> selectBoards(int page) {
		
		SqlSession sqlSession = getSqlSession();
		
		// Table에서 조회해온 게시글 총 개수
		int boardCount = boardDao.selectBoardCount(sqlSession);
		// page == 앞단에서 넘어온 요청 페이지
		
		// 마지막 페이지를 구해야함
		// 한 페이지에 보여줄 게시글 개수 : 10개
		
		/**
		 * boardCount	한 페이지 개수		마지막 페이지
		 * 	  100			10				10
		 * 	  103			10				11
		 * 	  112			10				12
		 * 
		 * -> 총 게시글 개수 / 한 페이지 개수를 올림처리할 경우 마지막 페이지를 구할 수 있음
		 * 
		 */
		int boardLimit = 10; // 한 페이지에 보여줄 게시글 개수
		int maxPage = (int)(Math.ceil(boardCount / (double)boardLimit));
		
		// startBtn : 페이지 하단에 보여질 버튼 중 시작 값
		// page : 한 페이지에 몇 개의 페이지 버튼을 보일 것인지=
		
		int btnLimit = 3; 	// 한 페이지에 보여줄 버튼 개수
		int startBtn = (page - 1) / btnLimit * btnLimit + 1;
		
		
		// endBtn
		/*
		 * startBtn : 1 => 5
		 * endBtn = startBtn + btnLimit - 1;
		 */
		int endBtn = startBtn + btnLimit - 1;
		if(endBtn > maxPage) endBtn = maxPage;
		
		/*
		 * MyBatis RowBounds 사용하기
		 * 
		 * offset과 limit을 생성자 매개변수로 전달해주어야 함
		 * 
		 * page 1 : 1 ~ 3 ==> 0
		 * page 2 : 4 ~ 6 ==> 3
		 * page 3 : 7 ~ 9 ==> 6
		 * 
		 * (page - 1) * boardLimit
		 * 
		 */
		RowBounds rowBounds = new RowBounds((page - 1) * boardLimit, boardLimit);
		
		// 나중 일 -> 페이징 처리 이후
		List<BoardDTO> boards = new BoardDAO().selectBoards(sqlSession, rowBounds);
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap();
		map.put("board", boards);
		map.put("boardCount", boardCount);
		map.put("page", page);
		map.put("boardLimit", boardLimit);
		map.put("btnLimit", btnLimit);
		map.put("maxPage", maxPage);
		map.put("startBtn", startBtn);
		map.put("endBtn", endBtn);
		
		return map;
	}
	
	public BoardDTO findByBoardNo(int boardNo) {
		
		SqlSession sqlSession = getSqlSession();
		
		// 최대 두 번 가야함
		
		// 조회수 증가 로직 한 번 	 ==> UPDATE
		// 게시글 정보 조회 로직 한 번 ==> SELECT
		
		int updateResult = boardDao.increaseCount(sqlSession, boardNo);
		
		if(updateResult == 0) {
			sqlSession.close();
			return null;
		}
		
		BoardDTO board = boardDao.findByBoardNo(sqlSession, boardNo);
		
		if(board != null) {
			sqlSession.commit();
		}
		sqlSession.close();
	
		return board;
	}
}
