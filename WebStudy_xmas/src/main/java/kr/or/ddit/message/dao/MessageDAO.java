package kr.or.ddit.message.dao;

import kr.or.ddit.vo.MessageVO;

public interface MessageDAO {
	/**
	 * 보낸 카드 전체 조회
	 * @param xcSender
	 * @return
	 */
	public MessageVO selectAll(String xcSender);
	
	public int insertMessage(MessageVO vo);
}
