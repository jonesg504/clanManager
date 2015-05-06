import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.NoSuchElementException;

public class SQLTesting {
	 public static String insertMember(Player player) 
	    {
	        try 
	        {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	String name = player.getName();
	        	String thLevel = "" + player.getThLevel();
	        	String worth  = "" + player.getWorth();
	        	String baseRank = "" + player.getRank();
	        	String numWars = "" + player.getNumWars();
	            Connection con = DriverManager.getConnection("insert", "gj_stores", "gj_stores");
	            String query = "INSERT into MEMBER_LIST VALUES(" + name + ", " + thLevel + ", "+ worth + ", "+ numWars + ", " + baseRank + ")";
	            PreparedStatement st = con.prepareStatement(query);
	            st.executeQuery();  
	            
	        } 
	        catch (Exception e) 
	        {
	        	 e.printStackTrace();
	        }
	        return"Record inserted successfully";
	    }
	 public static String insertAttack(Attack att) 
	    {
	        try 
	        {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	String name = att.getPlayer();
	        	String num = "" + att.getAttNum();
        	String thLevel = "" + att.getThLevel();
	        	String worth  = "" + att.getWorth();
	        	String baseRank = "" + att.getBaseRank();
	        	String warNum = "" + att.getWarNum();
	            Connection con = DriverManager.getConnection("insert", "gj_stores", "gj_stores");
	            String query = "INSERT into ATTACKS VALUES(" + name + ", " + num + ", "+ worth + ", "+ warNum + ", " + thLevel + ", " + baseRank + ")";
	            PreparedStatement st = con.prepareStatement(query);
	            st.executeQuery();  
	            
	        } 
	        catch (Exception e) 
	        {
	        	 e.printStackTrace();
	        }
	        return"Record inserted successfully";
	    }
	 public static String insertDefense(Defense att) 
	    {
	        try 
	        {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	String name = att.getPlayer();
	        	String num = "" + att.getDefNum();
	        	String thLevel = "" + att.getThLevel();
	        	String worth  = "" + att.getWorth();
	        	String warNum = "" + att.getWarNum();
	            Connection con = DriverManager.getConnection("insert", "gj_stores", "gj_stores");
	            String query = "INSERT into ATTACKS VALUES(" + name + ", " + num + ", "+ worth + ", "+ warNum + ", " + thLevel  + ")";
	            PreparedStatement st = con.prepareStatement(query);
	            st.executeQuery();  
	            
	        } 
	        catch (Exception e) 
	        {
	        	 e.printStackTrace();
	        }
	        return"Record inserted successfully";
	    }
	 public static Player getMember(String name) 
	    {
		 ResultSet rs = null;
		 name = name.toLowerCase();
		 Player play = null;
	        try 
	        {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	
	            Connection con = DriverManager.getConnection("insert", "gj_stores", "gj_stores");
	            String query = "SELECT * FROM  `MEMBER_LIST` WHERE Name =  " + "\"" + name + "\"";
	            PreparedStatement st = con.prepareStatement(query);
	            rs = st.executeQuery();  
	            if (rs == null) {
	            	throw new NoSuchElementException();
	            }
	            play = new Player(rs.getString("NAME"));
	            play.setThLevel(rs.getInt("thLevel"));
	            play.setWorth(rs.getInt("worth"));
	            play.setNumWars(rs.getInt("numwars"));
	            play.setRank(rs.getInt("baseRank"));
	        } 
	        catch (Exception e) 
	        {
	        	System.out.println(e.getMessage());
	        	 e.printStackTrace();
	        	 return null;
	        	 
	        }
	        return play;
	    }
	 
	 
	 
}
