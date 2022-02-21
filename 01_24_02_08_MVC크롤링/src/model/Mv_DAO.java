package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mv_DAO {

	Connection conn;
	PreparedStatement pstmt;

	// [SQL문]
	final String selectAll = "select * from MV";
	final String selectAll_rank = "select * from MV where rank>0 and rank<=10 order by rank asc";
	final String updatePrice = "update MV set price=?";
	final String updateStock = "update MV set stock=";// (+?/-1 where mNum=?)

	// [목록조회]
	public ArrayList<Mv_VO> selectAll(Mv_VO vo) {
		ArrayList<Mv_VO> datas = new ArrayList<Mv_VO>();
		conn = JDBCUtill.connect();

		try {
			// pk의 입력이 있다면, 상세조회!
			if (vo.getmNum() != 0) {
				pstmt = conn.prepareStatement(selectAll + " where mNum =?");
				pstmt.setInt(1, vo.getmNum());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					Mv_VO data = new Mv_VO();
					data.setmNum(rs.getInt("mNum"));
					data.setmName(rs.getString("mName"));
					data.setOnAir(rs.getString("onAir"));
					data.setGenre(rs.getString("genre"));
					data.setNation(rs.getString("nation"));
					data.setShowTime(rs.getString("showTime"));
					data.setRelease(rs.getString("release"));
					data.setdName(rs.getString("dName"));
					data.setActors(rs.getString("actors"));
					data.setrAge(rs.getString("rAge"));
					data.setrAgeInt(rs.getInt("rAgeInt"));
					data.setRank(rs.getInt("rank"));
					data.setStory(rs.getString("story"));
					data.setStock(rs.getInt("stock"));
					data.setPrice(rs.getInt("price"));
					datas.add(data);
				} else {
//                System.out.println("Mv_DAO 로그 : 대상없음");
				}
			}

				// else, 사용자의 입력이 없다면 목록조회!
				pstmt = conn.prepareStatement(selectAll + " order by mName asc");
				ResultSet rs = pstmt.executeQuery();

				// 목록조회시 출력 안할부분 주석처리
				while (rs.next()) {
					Mv_VO data = new Mv_VO();
					data.setmNum(rs.getInt("mNum"));
					data.setmName(rs.getString("mName"));
					data.setOnAir(rs.getString("onAir"));
					data.setGenre(rs.getString("genre"));
					data.setNation(rs.getString("nation"));
					data.setShowTime(rs.getString("showTime"));
					data.setRelease(rs.getString("release"));
					data.setdName(rs.getString("dName"));
					data.setActors(rs.getString("actors"));
					data.setrAge(rs.getString("rAge"));
					data.setrAgeInt(rs.getInt("rAgeInt"));
					data.setRank(rs.getInt("rank"));
					data.setStory(rs.getString("story"));
					data.setStock(rs.getInt("stock"));
					data.setPrice(rs.getInt("price"));
					datas.add(data);
				}
				rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
//      System.out.println("Mv_DAO 로그 : 목록조회 성공");
		return datas;
	}

	// [rank TOP 10]
	public ArrayList<Mv_VO> selectAll_rank(Mv_VO vo) {
		ArrayList<Mv_VO> datas = new ArrayList<Mv_VO>();
		conn = JDBCUtill.connect();

		try {
			pstmt = conn.prepareStatement(selectAll_rank);
			ResultSet rs = pstmt.executeQuery();

			// 목록조회시 출력 안할부분 주석처리
			while (rs.next()) {
				Mv_VO data = new Mv_VO();
				data.setmNum(rs.getInt("mNum"));
				data.setmName(rs.getString("mName"));
				data.setOnAir(rs.getString("onAir"));
				data.setGenre(rs.getString("genre"));
				data.setNation(rs.getString("nation"));
				data.setShowTime(rs.getString("showTime"));
				data.setRelease(rs.getString("release"));
				data.setdName(rs.getString("dName"));
				data.setActors(rs.getString("actors"));
				data.setrAge(rs.getString("rAge"));
				data.setrAgeInt(rs.getInt("rAgeInt"));
				data.setRank(rs.getInt("rank"));
				data.setStory(rs.getString("story"));
				data.setStock(rs.getInt("stock"));
				data.setPrice(rs.getInt("price"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
//      System.out.println("Mv_DAO 로그 : 목록조회 성공");
		return datas;
	}

	// [가격수정]
	public boolean updatePrice(Mv_VO vo) {
		conn = JDBCUtill.connect();
		try {
			pstmt = conn.prepareStatement(updatePrice);
			pstmt.setInt(1, vo.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//         System.out.println("Mv_DAO로그 : 가격변경 실패");
			return false;
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
//      System.out.println("Mv_DAO로그 : 가격변경 성공");
		return true;
	}

	// [재고수정(판매시, 재고 변경시)]
	public boolean updateStock(Mv_VO vo) {
		conn = JDBCUtill.connect();

		try {
			// 재고입력이 있다면(관리자의 권한이라면), Stock+?
			if (vo.getStock() != 0) {
				pstmt = conn.prepareStatement(updateStock + "stock+? where mNum=?");
				pstmt.setInt(1, vo.getStock());
				pstmt.setInt(2, vo.getmNum());
				int res1 = pstmt.executeUpdate();

				if (res1 == 0) { // 해당 mNum이 없으면
//               System.out.println("Mv_DAO로그 : pk조회 실패");
					return false;
				}
			} else {
				
				// else(사용자의 구매라면), Stock-1
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(updateStock + "stock-1 where mNum=?");
				pstmt.setInt(1, vo.getmNum());
				int res2 = pstmt.executeUpdate();

				if (res2 == 0) { // 해당 mNum이 없으면
					return false;
				}

				pstmt = conn.prepareStatement("select * from MV where mNum=?");
				pstmt.setInt(1, vo.getmNum());
				ResultSet rs = pstmt.executeQuery();
				rs.next();

				if (rs.getInt("Stock") < 0) {
					conn.rollback();
					conn.setAutoCommit(true);
					return false;
				} else {
					conn.commit();
					conn.setAutoCommit(true);
				}

				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//         System.out.println("Mv_DAO로그 : 재고 수정 실패");
			return false;
		} finally {
			JDBCUtill.disconnect(pstmt, conn);
		}
//      System.out.println("Mv_DAO로그 : 재고 수정 성공");
		return true;
	}
}