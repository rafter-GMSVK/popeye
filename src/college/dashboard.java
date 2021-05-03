package college;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;


// this is is the main array so be careful when you are playing around with it
class data_from_dbms {
    String Item_id;
    String Item_name;
    int Item_quantity;
    int Item_price;
    double Item_angle;
    int Item_sales;
    double angle2;
    double angle3;
    String Item_comments;
    New_window each_product;
    JButton check;


    data_from_dbms(String id,String name,int price, int quantity , int x , int y,String comments, double angle,double ang1,double ang2, int sales) throws Exception{
        Item_id = id;
        Item_name = name;
        Item_quantity = quantity;
        Item_price = price;
        Item_angle = angle;
        Item_sales = quantity;
        Item_comments = comments;
        angle2 = ang1;
        angle3 = ang2;



        check = new JButton(Item_name);
        check.setBounds(x,y,120,30);
        check.setOpaque(true);
        check.setBackground(Color.cyan);
        check.setForeground(Color.black);
        check.setFocusPainted(false);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Item_name);

                  try{
                      itemwindow each_product = new itemwindow(500,500,Item_id,Item_name,Item_quantity,Item_price,Item_angle,Item_sales,Item_comments,angle2,angle3);


                  }catch (Exception c){
                      c.printStackTrace();
                  }


            }
        });
    }
}



class dashboard extends New_window{

    // connecting to the database
    private mydbms mdb = new mydbms();
    ResultSet rs;
    private JLabel dash = new JLabel();
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    int  i=0;
    int y =80;
    int x = 280;
    int scale= 0;
    int yscale=0;
    int j=3;
    JLabel topframe = new JLabel();
    int Total_data=0;
    JLabel Mtitle = new JLabel("OUR PRODUCTS");
    dashboard(String user, String phone_number, String user_address, String user_email ) throws Exception{

        create("Popeye",1200,1200);
       // getting the user details
        String[] a = new String[20];
        int j =0;
        try{
            File open = new File("Details.txt");
            Scanner read = new Scanner(open);
            while (read.hasNext()){
                a[j] = read.nextLine();
                j++;
            }

        }catch (IOException c){
            c.printStackTrace();

        }

        dash.setBounds(0,0,1200,1200);
        dash.setOpaque(true);
        dash.setBackground(Color.white);
        Frame.add(dash);

        //---------------------------------------------front views--------------------------------------------------------
        // main dashboard heading
        JLabel Heading3 = new JLabel("Dashboard");
        Heading3.setFont(new Font("Roboto",Font.PLAIN,34));
        Heading3.setBounds(JLabel.RIGHT,JLabel.LEADING,200,30);
        Heading3.setHorizontalAlignment(JLabel.CENTER);
        Heading3.setVerticalAlignment(JLabel.CENTER);
        Heading3.setForeground(Color.BLACK);
        dash.add(Heading3);

        JLabel about = new JLabel();
        about.setBounds(850,0,300,200);

        JLabel name = new JLabel("Username : "+user);
        name.setBounds(JLabel.RIGHT,2,200,30);
        name.setFont(new Font("Roboto",Font.BOLD,16));
        name.setForeground(Color.BLACK);
        about.add(name);

        JLabel email = new JLabel("Email : "+user_email);
        email.setBounds(JLabel.RIGHT,34,300,30);
        email.setFont(new Font("Roboto",Font.PLAIN,16));
        email.setForeground(Color.BLACK);
        about.add(email);

        JLabel phone = new JLabel("Phone : "+phone_number);
        phone.setBounds(JLabel.RIGHT,66,300,30);
        phone.setFont(new Font("Roboto",Font.PLAIN,16));
        phone.setForeground(Color.BLACK);
        about.add(phone);

        JLabel address = new JLabel("Address : "+user_address);
        address.setBounds(JLabel.RIGHT,98,300,30);
        address.setFont(new Font("Roboto",Font.PLAIN,16));
        address.setForeground(Color.BLACK);
        about.add(address);

        /*JLabel address2 = new JLabel("               "+a[5]);
        address2.setBounds(JLabel.RIGHT,130,300,30);
        address2.setFont(new Font("Roboto",Font.PLAIN,16));
        address2.setForeground(Color.cyan);
        about.add(address2);
        */

        topframe.setBounds(10,350,1170,500);
        topframe.setBorder(border);
        topframe.setVerticalAlignment(JLabel.CENTER);
        topframe.setHorizontalAlignment(JLabel.CENTER);

        JLabel Button_Label = new JLabel();
        Button_Label.setBounds(100,250,1000,70);


        Mtitle.setBounds(450,20,250,50);
        Mtitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        topframe.add(Mtitle);
//---------------------------------------------end of front view---------------------------------------------------------
        // Button prints storing the data the array
         rs = mdb.alldisplay();
        System.out.println("uploading in the array");
        while (rs.next()){
            y =y+30;

            if (i%5==0&&i!=0){
                y = 80+30;
                x =x +125;
            }
            // 1.Item_id,2. Item_name,3. Item_price,4.Item_quantity,5.angle,6.per_day-sales,7.comments,8.amgle1,9.angle2
            arraydata[i] = new data_from_dbms(rs.getString(1),rs.getString(2),
                    Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),x,y,rs.getString(5),
                    rs.getInt(6),rs.getDouble(7),rs.getDouble(8),rs.getInt(9));
            topframe.add(arraydata[i].check);
            i++;
        }

        int Total_data = i;
        System.out.println(Total_data);
        System.out.println("Confirming that it's working");

        for(int i =0;i<Total_data;i++){
            System.out.println(arraydata[i].Item_id);
        }


        //----------------------------------------button function---------------------------------------------------------------

        //Refresh
        JButton Reboot = new JButton("Refresh");
        Reboot.setBounds(535,25,150,30);
        Reboot.setFocusPainted(false);
        Button_Label.add(Reboot);
        Reboot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refresh();
                }catch (Exception c){
                    c.printStackTrace();
                }
            }
        });



        // this is for the billing aw
        JButton Billing = new JButton("Billing");
        Billing.setBounds(365,25,150,30);
        Billing.setFocusPainted(false);
        Billing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    daily_slales open = new daily_slales(arraydata);
                }catch (Exception c){
                    c.printStackTrace();
                }

            }
        });

        // add item label
        JButton add_Item = new JButton("Add New Item");
        add_Item.setBounds(30,25,150,30);
        add_Item.setFocusPainted(false);
        add_Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(Total_data);
                    addItem one = new addItem("Add New Item", 500, 500,arraydata,Total_data);
                    System.out.println("ok good");
                }catch (Exception c){
                    c.printStackTrace();
                }

                }
        });

        JButton del_Item = new JButton("Delete Item");
        del_Item.setBounds(200,25,150,30);
        del_Item.setFocusPainted(false);
        del_Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    delItem two =new delItem("Delete Item",500,500);
                }catch (Exception c){
                    c.printStackTrace();
                }

            }
        });

        // Close the program button UPDATE

        JButton Exit = new JButton("Exit");
        Exit.setBounds(695,25,150,30);
        Exit.setOpaque(true);
        Exit.setBackground(Color.RED);
        Exit.setFocusPainted(false);
        Exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int val =0;

                try{
                    rs = mdb.alldisplay();

                    while (rs.next()){
                        Double angle1;
                        Double angle2;
                        Double angle3;
                        angle1 = rs.getDouble(7);
                        angle2 = rs.getDouble(8);
                        angle3 = Math.atan((rs.getDouble(9))/24);

                        String id = rs.getString(1);

                        mdb.calculate(id,angle1,angle2,angle3);


                    }
                }catch (Exception c){
                    c.printStackTrace();
                }


                Frame.dispose();

            }
        });

//---------------------------------------------end of Button functions---------------------------------------------------------------

        Button_Label.add(add_Item);
        Button_Label.add(del_Item);
        Button_Label.add(Billing);
        Button_Label.add(Exit);
        dash.add(Heading3);
        dash.add(about);
        dash.add(topframe);
        dash.add(Button_Label);

    }

    //-------------------------------------Functions----------------------------------------------------------------------
    void refresh() throws Exception{
        System.out.println(Total_data);
        topframe.removeAll();
        Mtitle.setBounds(450,20,250,50);
        Mtitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        topframe.add(Mtitle);
        int i =0;
        int x = 280;
        int y = 80;
        rs = mdb.alldisplay();
        System.out.println("uploading in the array");
        while (rs.next()){
            y =y+30;
            System.out.println(i);
            System.out.println(y);

            if (i%5==0&&i!=0){
                y = 80+30;
                x =x +125;
            }

            arraydata[i] = new data_from_dbms(rs.getString(1),rs.getString(2),
                    rs.getInt(3),rs.getInt(4),x,y,rs.getString(5),
                    rs.getInt(6),rs.getDouble(7),rs.getDouble(8),rs.getInt(9));
            topframe.add(arraydata[i].check);
            i++;
        }

        System.out.println("Re-Creating the dashboard....");
        dash.repaint();
        Total_data = i;
        System.out.println(Total_data);
    }


}

