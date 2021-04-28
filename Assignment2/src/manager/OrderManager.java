package manager;

import kioskapp.itemproduct.ItemProduct;
import kioskapp.order.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManager {

	private DatabaseConnection db;

	public OrderManager(){
		db = new DatabaseConnection();
	}

	//Insert order into database
	public Order insertOrder(Order order){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status = 0;
    
		String sql = "INSERT INTO `Order` (TotalAmount,OrderReferenceNumber) " +
                 "VALUES (?,?)";

        try {
			    conn = db.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setFloat(1, order.getTotalAmount());
                ps.setInt(2, order.getOrderReferenceNumber());
               

                status = ps.executeUpdate();
                ps.close();
                if(status!=0)
                {
                    sql ="SELECT OrderId FROM `Order`;";
                    ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = ps.executeQuery();
                    rs.last();
                    
                    order.setOrderId(rs.getInt(1));
                }
            } 
            catch (ClassNotFoundException | SQLException e) {
            	e.printStackTrace();
		    }
            finally{
        
				try {
                        if (ps!= null)
                            ps.close();
                        if (rs!=null)
                            rs.close();
                        if(conn!=null)
					        conn.close();
                            
				} catch (SQLException e) {
					
					e.printStackTrace();
				}  
        } 
 
        
    return order;

	}
	
	public int getOrderID () throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		String sql = "SHOW TABLE STATUS LIKE 'order'";
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(11);
			
		} catch (SQLException | ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		
		try {
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		return rs.getInt(11);
		
	}

}
