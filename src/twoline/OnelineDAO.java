package twoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OnelineDAO { //Data Access Object:데이터 접속
	private String url; 
	private String user;
	private	String passwd;
	 
	public OnelineDAO()//생성자
	{
		this.url = "jdbc:mysql://localhost/world?characterEncoding=UTF-8&serverTimezone=UTC";	
		this.user = "root";
		this.passwd = "root11";
	}
	
	public Connection connect() {//메소드는 소문자로
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return con;
	}
	
	public ArrayList<OnelineDTO> getList() 
	{
		Connection con = null;
		String sql = "select * from oneline order by no desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<OnelineDTO> dtos = new ArrayList<OnelineDTO>();


		try {
			con = connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select
			while(rs.next()) {
				int no = rs.getInt("no");
				String memo = rs.getString("memo");
				String wdate = rs.getString("wdate");
				
				OnelineDTO dto = new OnelineDTO(no, memo, wdate);
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public void insert(OnelineDTO dto)
	{
		Connection con = null;
		String sql = "insert into oneline(memo) values( ? )";
		PreparedStatement pstmt = null;

		try {
			con = connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemo());
			pstmt.executeUpdate(); // select
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(OnelineDTO dto)
	{
		Connection con = null;
		String sql = "insert into oneline(memo) values( ? )";
		PreparedStatement pstmt = null;
	}
	
	public void delete(int no) 
	{
		Connection con = null;
		String sql = "delete from oneline where no=("+ no +")";
		PreparedStatement pstmt = null;
	}
}