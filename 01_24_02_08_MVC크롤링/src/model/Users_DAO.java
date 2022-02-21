package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class Users_DAO {

	Connection conn;
	PreparedStatement pstmt;
	final String deleteUser = "delete from users where uPk = ?";
	final String insertUser = "insert into users(uPk,uPw,uName,uAge) values (?,?,?,?)";
	final String login = "select uPw from users where uPk = ?";
	final String selectAll = "select * from users order by uPoint asc";
	final String selectOne = "select * from users where uPk = ?";
	final String updateName = "update users set uName = ? where uPk = ?";
	final String updatePw = "update users set uPw = ? where uPk = ?";
	final String updateAge = "update users set uAge = ?  where uPk = ?";
	final String updatePoint = "update users set uPoint = uPoint+ ?  where uPk = ?";

	public int insertUser(Users_VO vo) {// 가입 아이디 비밀번호 이름 나이 입력
		int flag = 0;

		conn = JDBCUtill.connect();
		String uPk = vo.getuPk();
		String uPw = vo.getuPw();
		String uName = vo.getuName();
		int uAge = vo.getuAge();
		try {
			pstmt = conn.prepareStatement(insertUser);
			pstmt.setString(1, uPk);
			pstmt.setString(2, uPw);
			pstmt.setString(3, uName);
			pstmt.setInt(4, uAge);
			pstmt.executeUpdate();
			flag = 0;// 성공시
		} catch (SQLIntegrityConstraintViolationException e) {
			flag = 1;// 아이디 중복으로 실패시
		} catch (Exception e) {
			flag = 2;// 다른 이유로 실패시
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return flag;
	}

	public boolean login(Users_VO vo) {// 로그인 아이디 비밀번호 입력
		boolean flag = false;// 성공 여부
		conn = JDBCUtill.connect();
		ResultSet rs = null;
		String uPk = vo.getuPk();
		String uPw = vo.getuPw();
		try {
			pstmt = conn.prepareStatement(login);
			pstmt.setString(1, uPk);
			rs = pstmt.executeQuery();// uPk에 해당하는 uPw 호출

			while (rs.next()) {
				if (uPw.equals(rs.getString("uPw"))) {// vo의 uPw와 테이블의 uPw를 비교
					flag = true;// 같으면 true 리턴
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return flag;
	}

	public Users_VO selectOne(Users_VO vo) {// 1명검색 아이디 입력
		conn = JDBCUtill.connect();
		String uPk = vo.getuPk();
		Users_VO data = new Users_VO();
		try {
			pstmt = conn.prepareStatement(selectOne);
			pstmt.setString(1, uPk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				data.setuAge(rs.getInt("uAge"));
				data.setuPoint(rs.getInt("uPoint"));
				data.setuName(rs.getString("uName"));
				data.setuPk(rs.getString("uPk"));
				data.setuPw(rs.getString("uPw"));
				data.setuAdmin(rs.getInt("uAdmin"));
			}

			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return data;
	}

	public ArrayList<Users_VO> selectAll(Users_VO vo) {// 리스트

		ArrayList<Users_VO> datas = new ArrayList<Users_VO>();
		conn = JDBCUtill.connect();
		try {

			pstmt = conn.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Users_VO data = new Users_VO();
				data.setuAge(rs.getInt("uAge"));
				data.setuPoint(rs.getInt("uPoint"));
				data.setuName(rs.getString("uName"));
				data.setuPk(rs.getString("uPk"));
				data.setuPw(rs.getString("uPw"));
				data.setuAdmin(rs.getInt("uAdmin"));
				datas.add(data);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return datas;
	}

	public boolean updateUser(Users_VO vo) {// 유저 정보 수정
		// 아이디 + 비밀번호or이름or나이 입력
		boolean flag = false;// 성공 여부
		int rs = 0;
		conn = JDBCUtill.connect();
		String uPk = vo.getuPk();
		String uName = vo.getuName();
		String uPw = vo.getuPw();
		int uAge = vo.getuAge();

		try {

			if (uName != null) {
				// 이름 변경
				pstmt = conn.prepareStatement(updateName);
				pstmt.setString(1, uName);
				pstmt.setString(2, uPk);
				rs = pstmt.executeUpdate();
			} else if (uPw != null) {
				// 비번 변경
				pstmt = conn.prepareStatement(updatePw);
				pstmt.setString(1, uPw);
				pstmt.setString(2, uPk);
				rs = pstmt.executeUpdate();
			} else if (uAge != 0) {
				// 나이 변경
				pstmt = conn.prepareStatement(updateAge);
				pstmt.setInt(1, uAge);
				pstmt.setString(2, uPk);
				rs = pstmt.executeUpdate();
			} else {
				//System.out.println("로그 - 유저 정보 변경 오류");
			}

			if (rs != 0) {// 수행 횟수가 0이 아니라면
				flag = true;// 트루
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return flag;
	}

	public boolean updatePoint(Users_VO vo) {// 포인트 증감 아이디 포인트 입력
		boolean flag = false;// 성공 여부
		ResultSet rs = null;
		conn = JDBCUtill.connect();
		String uPk = vo.getuPk();
		int uPoint = vo.getuPoint();

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(updatePoint);
			pstmt.setInt(1, uPoint);
			pstmt.setString(2, uPk);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from users where uPk = ?");
			pstmt.setString(1, uPk);
			rs = pstmt.executeQuery();

			rs.next();
			if (rs.getInt("uPoint") < 0) {
				//System.out.println("로그 - 포인트 부족");
				conn.rollback();
			} else {
				//System.out.println("로그 - 구매 성공");
				flag = true;
				conn.commit();
			}
			rs.close();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return flag;
	}

	public boolean deleteUser(Users_VO vo) {// 삭제 아이디 입력
		boolean flag = false;// 성공 여부
		int rs = 0;
		conn = JDBCUtill.connect();
		String uPk = vo.getuPk();
		try {
			pstmt = conn.prepareStatement(deleteUser);
			pstmt.setString(1, uPk);
			rs = pstmt.executeUpdate();

			if (rs != 0) {// 수행 횟수가 0이 아니라면
				flag = true;// 트루
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
		return flag;
	}
}
