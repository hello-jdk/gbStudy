package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.JDBCUtill;
import model.Mv_VO;

public class Naver_mv {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      // [1. 크롤링 할 url 상수화작업, SQL문 상수화]
      final String url1 = "https://movie.naver.com/movie/running/current.naver";
      String sql= "insert into MV (mNum, mName, onAir, genre, nation ,showTime ,release, dName,actors, rAge, rAgeInt, rank, story) values( (select (nvl(max(mNum),1000))+1 from MV),?,?,?,?,?,?,?,?,?,?,?,?)";
      Document doc = null;
      ArrayList<String> urlDatas = new ArrayList<String>();//url저장
      ArrayList<Mv_VO> datas = new ArrayList<Mv_VO>();//DB전달

      try {
         doc = Jsoup.connect(url1).get(); 
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // [2. 각 영화 페이지 컬렉션에 저장]
      Elements eles = doc.select("dt > a");

      for(Element e : eles) {
         urlDatas.add(e.attr("abs:href"));
      }

      for(int i=0; i<urlDatas.size(); i++) {
         Mv_VO vo = new Mv_VO();//★스코프 잘 확인하자....제발..★
         vo.setdName("감독 정보 없음");
         vo.setActors("배우 정보 없음");
         try {
            doc=Jsoup.connect(urlDatas.get(i)).get();
         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }


         // [3. 각 data VO에 저장]
         // 1) mName, onAir
         String mName = "h3.h_movie>a";
         eles = doc.select(mName);
         Iterator<Element> itr = eles.iterator();
         while(itr.hasNext()) {
            itr.next().text();
            itr.next().text();
            vo.setmName(itr.next().text()); //제목
            vo.setOnAir(itr.next().text()); //상영중
         }

         // 2) genre, nation, showTimr, release
         String genre = "dl.info_spec>dd>p>span";
         eles = doc.select(genre);
         itr = eles.iterator();
         while(itr.hasNext()) {
            vo.setGenre(itr.next().text());//장르
            vo.setNation(itr.next().text());//개봉국
            vo.setShowTime(itr.next().text());//상영시간
            vo.setRelease(itr.next().text());//개봉일자
         }

         // 3) dName, actors, rAge, rAgeInt
         String a = "dl.info_spec>dd>p";
         eles = doc.select(a);
         itr = eles.iterator();

         while(itr.hasNext()) {
            itr.next().text(); //하나 날리고

            // 바로 관람가면? age,ageInt 세팅하고 바로 종료
            String str1 = itr.next().text();
            if(str1.contains("관람")) {
               vo.setrAge(str1);
               if(vo.getrAge().contains("전체")) {
                  vo.setrAgeInt(0);
               } else if(vo.getrAge().contains("청소년")) {
                  vo.setrAgeInt(19);
               } else {
                  vo.setrAgeInt(Integer.parseInt(vo.getrAge().substring(5, 7)));
               }
               break;
            }
            //관람가 아니면 감독이름으로 저장
            vo.setdName(str1);
            
            // 두번째 조회값이 관람가면? age,ageInt 세팅하고 바로 종료
            String str2 = itr.next().text();
            if(str2.contains("관람")) {
               vo.setrAge(str2);
               if(vo.getrAge().contains("전체")) {
                  vo.setrAgeInt(0);
               } else if(vo.getrAge().contains("청소년")) {
                  vo.setrAgeInt(19);
               } else {
                  vo.setrAgeInt(Integer.parseInt(vo.getrAge().substring(5, 7)));
               }
               break;
            }
            //관람가 아니면 출연진으로 저장
            vo.setActors(str2);
            
            // 3번째가 관람가면 그에 맞게 저장!
            String str3 = itr.next().text();
            if(str3.contains("관람")) {
               vo.setrAge(str3);
               if(vo.getrAge().contains("전체")) {
                  vo.setrAgeInt(0);
               } else if(vo.getrAge().contains("청소년")) {
                  vo.setrAgeInt(19);
               } else {
                  vo.setrAgeInt(Integer.parseInt(vo.getrAge().substring(5, 7)));
               }
               break;
            }
         }

         // 4) rank
         String rank = "dd>div.step9_cont>p.rate";
         eles = doc.select(rank);
         itr = eles.iterator();
         while(itr.hasNext()) {
            String tmpRank = itr.next().text(); 
            tmpRank = tmpRank.substring(tmpRank.indexOf(" ")+1,tmpRank.indexOf("위"));
            vo.setRank(Integer.parseInt(tmpRank)); //형변환, 예매율
         }

         //5) story
         String story = "div.story_area";
         eles = doc.select(story);
         itr = eles.iterator();

         while(itr.hasNext()) {
            //제일 짧은 줄거리 기준으로 축소
            vo.setStory(itr.next().text().substring(4,40)+"..."); 
         }

         //VO로그!!
         //System.out.println(vo);
         datas.add(vo);// vo값 리스트에 저장
      }

      // datas로그!!
      for(Mv_VO v : datas) {
         System.out.println(v);
      }

      // [4. DB전달]

       Connection conn = JDBCUtill.connect();
       PreparedStatement pstmt =null;
       
      try {
         pstmt = conn.prepareStatement(sql);
         //리스트의 길이만큼 update
         for(Mv_VO v : datas) {
            pstmt.setString(1, v.getmName());
            pstmt.setString(2, v.getOnAir());
            pstmt.setString(3, v.getGenre());
            pstmt.setString(4, v.getNation());
            pstmt.setString(5, v.getShowTime());
            pstmt.setString(6, v.getRelease());
            pstmt.setString(7, v.getdName());
            pstmt.setString(8, v.getActors());
            pstmt.setString(9, v.getrAge());
            pstmt.setInt(10, v.getrAgeInt());
            pstmt.setInt(11, v.getRank());
            pstmt.setString(12, v.getStory());
            pstmt.executeUpdate();
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         JDBCUtill.disconnect(pstmt, conn);
      }

   }

}