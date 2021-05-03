package college;
import com.mysql.cj.protocol.Resultset;
import com.sun.security.jgss.GSSUtil;
import jdk.jshell.spi.SPIResolutionException;

import java.sql.*;
import java.util.function.DoubleUnaryOperator;

public class mydbms{

    String url;
    String uname;
    String pass;
    Connection con =null;
   Statement st = null;
   ResultSet rs;


    mydbms()throws Exception{
        url = "jdbc:mysql://localhost/popeye";
        uname = "root";
        pass = "AumShanthi01";
        con =DriverManager.getConnection(url,uname,pass);
        st = con.createStatement();


    }

    String add(String table,String id, String item, String price, String quantity, String disc) throws Exception{

        String value = null;

        String update ="insert into "+table+" values ("+id+",'"+item+"',"+price+","+quantity+",'"+disc+"',"+ value + ","+value+","+value+","+value+")";
        System.out.println(update);
        try{
            st.execute(update);
            return "Item Added";
        }catch (SQLIntegrityConstraintViolationException a){
            return " id number already taken Try again";
        }


    }

    String del(String table,String id)throws Exception {
        String three;
        String del_query = "delete from "+ table +" where item_id = "+id;
        try {
            st.execute(del_query);
             three  = "Item Deleted";

        } catch (NullPointerException E) {
            System.out.println("null pointer exception");
            three = " Item not deleted";
        }

        return three;
    }

    ResultSet display(String id)throws Exception {
        String display_query ="SELECT * FROM items where item_id ="+id;

       rs = st.executeQuery(display_query);

       return rs;
    }

    void update(int id, String name, int quantity,int price,String comments)throws Exception{
        String update_query = "update items set item_price =?, item_quantity =?,item_name =?,comments =? where item_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(update_query);
        preparedStatement.setLong(1,price);
        preparedStatement.setLong(2,quantity);
        preparedStatement.setString(3,name);
        preparedStatement.setString(4,comments);
        preparedStatement.setLong(5,id);
        System.out.println(preparedStatement.executeUpdate());


    }
    void C_update(String id,int quantity, int per_day_sales ) throws SQLException {
            String s = String.valueOf(quantity);
        String update = "update items set item_quantity =?, per_day_sales =? where item_id = ?";

        PreparedStatement preparedStatement = con.prepareStatement(update);
        preparedStatement.setString(1,s);
        preparedStatement.setLong(2,per_day_sales);
        preparedStatement.setString(3,id);

        //String update = "update items set item_quantity ="+quantity+", per_day_sales ="+per_day_sales+" where item_id ="+id;

        preparedStatement.executeUpdate();
        //st.executeQuery(update);

    }

    ResultSet alldisplay() throws Exception{
        String display_query ="SELECT * FROM items";

        rs = st.executeQuery(display_query);
        return rs;
    }

     void calculate(String id, double angle,double angle2, double angle3) throws Exception{
        String query = "update items set angle1 =?, angle2=?, angle3=?, per_day_sales=? where  item_id =?";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setDouble(1, angle);
        preparedStatement.setDouble(2,angle2);
        preparedStatement.setDouble(3,angle3);
        preparedStatement.setLong(4,0);
        preparedStatement.setString(5,id);
        preparedStatement.executeUpdate();

    }

    ResultSet find_user(String username, String password) throws Exception{
        String Query = "SELECT * FROM user";
        rs = st.executeQuery(Query);
        return rs;

    }

    void add_user(String id,String username, String password, String phone, String address, String email) throws Exception{

        String Query = "insert into user values('"+id+"','"+username+"','"+password+"','"+phone+"','"+address+"','"+email+"')";


        try{
            st.execute(Query);
        }catch (SQLIntegrityConstraintViolationException a){
            System.out.println("Something went wrong");
        }

    }

    ResultSet allusers() throws Exception{
        String display_query ="SELECT * FROM user";

        rs = st.executeQuery(display_query);
        return rs;
    }


    void Exit() throws Exception{
        rs.close();
        con.close();

    }



}



