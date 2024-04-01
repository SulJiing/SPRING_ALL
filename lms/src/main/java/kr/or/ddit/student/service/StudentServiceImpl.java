package kr.or.ddit.student.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.student.dao.StudentDAO;
import kr.or.ddit.student.dao.StudentOthersDAO;
import kr.or.ddit.vo.StudentVO;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Inject
	private StudentDAO dao;
	@Inject
	private StudentOthersDAO othersDao;
	
	@Override
	public ServiceResult createStudent(StudentVO student) {
		if(othersDao.selectStudent(student.getStdNo()) == null) {
			return dao.insertStudent(student) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
		}else {
			return ServiceResult.INVALIDPASSWORD;
		}
	}

	@Override
	public List<StudentVO> retrieveStudentList(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentVO retrieveStudent(String stdNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyStudent(StudentVO student) {
		return dao.updateStudent(student) > 0 ? ServiceResult.OK:ServiceResult.FAIL;
	}

}
