import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
	private String dburl="jdbc:mysql://localhost:3306/userdb";
	private String dbuname="root";
	private String dbpassword="password";
	private String dbdriver="com.mysql.cj.jdbc.driver";
	
	public void loaddriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws ClassNotFoundException 
	{	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null ;
		try {
			
			
			conn=DriverManager.getConnection(dburl,dbuname,dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		return conn;
	}
	public String insert(Member member) throws ClassNotFoundException 
	{
		loaddriver(dbdriver);
		Connection con = getConnection();
		String result = "data entered success";
		String sql = "insert into member values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getUname());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getPhone());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			result="not entered successsfully";
		}
		return result;
	}
}
