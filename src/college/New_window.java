package college;

import com.mysql.cj.protocol.Resultset;
import com.sun.jdi.event.StepEvent;
import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.css.DOMImplementationCSS;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Dictionary;


// customer cart
class one_sales{
    String item_id;
    String item_name;
    int item_price;
    int item_quantity;
    float angle;
    int sales;


    void get(String item_id,String item_name,int item_price, int item_quantity){
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
    }

}

class New_window extends JFrame {
    int length;
    int breadth;
    String name;
    JFrame Frame;
    Border border;
    mydbms db;
    public data_from_dbms[] arraydata = new data_from_dbms[50];
    int Total_data;


    void create (String name,int length, int breadth) throws Exception{

        this.length = length;
        this.breadth = breadth;
        this.name = name;
        db = new mydbms();
        border = BorderFactory.createLineBorder(Color.BLACK);

        Frame = new JFrame(name);
        Frame.setSize(length,breadth);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);
        Frame.setLayout(null);
        Frame.setVisible(true);


    }

}
//=================================================================ITEM WINDOW=====================================================
class itemwindow extends New_window{

    itemwindow(int length, int breadth,String  id, String name,int quantity, int price,double angle, int sales,String comments,double ang1,double ang2)throws Exception{

        create(name,length,breadth);
        //================================================Math for the graph==========================================================
        System.out.println("item id =" + id);
        System.out.println("angle1 = "+angle);
        System.out.println("angle2 = "+ang1);
        System.out.println("angle3 =" + ang2);

        double Item_angle = (ang1+ang2)/2;
        System.out.println(Item_angle);

        JLabel base = new JLabel();
        base.setBorder(border);
        base.setBounds(2,2,480,458);

        String background = null;
        String background2 = null;
        String com = null;



        if(Item_angle<ang2){
            com = "Good sales";
            background = "uparrow.jpg";
        }
        else if(Item_angle==ang2){
            com = "no change";
            background = "nochange.jpg";
        }
        else{
            com ="sales went down";
            background = "downarrow.jpg";
        }
        
        JLabel one = new JLabel(new ImageIcon(background));
        one.setBounds(160,130,175,175);
        one.setBorder(border);
        JLabel comment = new JLabel(com);
        comment.setBounds(200,300,120,50);
        base.add(comment);





        //========================================================Label Heading=================================================
        JLabel item_id = new JLabel("ID");
        item_id.setFont(new Font("Times New Roman", Font.BOLD, 12));
        item_id.setBorder(border);
        item_id.setBounds(40,20,30,25);
        item_id.setHorizontalAlignment(JLabel.CENTER);
        base.add(item_id);

        JLabel item_name = new JLabel("ITEM NAME");
        item_name.setFont(new Font("Times New Roman", Font.BOLD, 12));
        item_name.setBorder(border);
        item_name.setBounds(90,20,100,25);
        item_name.setHorizontalAlignment(JLabel.CENTER);
        base.add(item_name);

        JLabel item_quantity = new JLabel("ITEM QUANTITY");
        item_quantity.setFont(new Font("Times New Roman", Font.BOLD, 12));
        item_quantity.setBorder(border);
        item_quantity.setBounds(200,20,120,25);
        item_quantity.setHorizontalAlignment(JLabel.CENTER);
        base.add(item_quantity);



        JLabel item_price = new JLabel("ITEM PRICE");
        item_price.setFont(new Font("Times New Roman", Font.BOLD, 12));
        item_price.setBorder(border);
        item_price.setBounds(330,20,100,25);
        item_price.setHorizontalAlignment(JLabel.CENTER);
        base.add(item_price);

        //=====================================================Item details======================================================
        JLabel new_id = new JLabel(""+id);
        new_id.setFont(new Font("Times New Roman", Font.BOLD, 12));
        new_id.setBorder(border);
        new_id.setBounds(40,55,30,25);
        new_id.setHorizontalAlignment(JLabel.CENTER);
        base.add(new_id);

        JLabel new_item_name = new JLabel();
        new_item_name.setText(name);
        new_item_name.setFont(new Font("Times New Roman", Font.BOLD, 12));
        new_item_name.setBorder(border);
        new_item_name.setBounds(90,55,100,25);
        new_item_name.setHorizontalAlignment(JLabel.CENTER);
        base.add(new_item_name);

        JLabel new_item_quantity = new JLabel(""+quantity);
        new_item_quantity.setFont(new Font("Times New Roman", Font.BOLD, 12));
        new_item_quantity.setBorder(border);
        new_item_quantity.setBounds(200,55,120,25);
        new_item_quantity.setHorizontalAlignment(JLabel.CENTER);
        base.add(new_item_quantity);

        JLabel new_item_price = new JLabel(""+price);
        new_item_price.setFont(new Font("Times New Roman", Font.BOLD, 12));
        new_item_price.setBorder(border);
        new_item_price.setBounds(330,55,100,25);
        new_item_price.setHorizontalAlignment(JLabel.CENTER);
        base.add(new_item_price);

        JLabel Disc = new JLabel("Item Description :");
        Disc.setBounds(40,90,120,20);
        base.add(Disc);

        JLabel about = new JLabel(comments);
        about.setBounds(180,90,200,30);
        about.setBorder(border);
        base.add(about);

        JTextArea two = new JTextArea(""+id);
        two.setBounds(40,55,30,25);
        two.setBorder(border);

        JTextArea three = new JTextArea(name);
        three.setBounds(90,55,100,25);
        three.setBorder(border);

        JTextArea four = new JTextArea(""+quantity);
        four.setBounds(200,55,120,25);
        four.setBorder(border);

        JTextArea five = new JTextArea(""+price);
        five.setBounds(330,55,100,25);
        five.setBorder(border);

        JTextArea six = new JTextArea(""+comments);
        six.setBounds(160,90,200,30);
        six.setBorder(border);

        JButton update = new JButton("Update");
        update.setBounds(180,350,200,30);
        update.setBorder(border);


        JButton done = new JButton("Apply update");
        done.setBounds(180,350,200,30);
        done.setBorder(border);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.update(Integer.parseInt(two.getText()),three.getText(),Integer.parseInt(four.getText()),Integer.parseInt(five.getText()),six.getText());
                }catch (Exception c){
                    c.printStackTrace();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.remove(new_id);
                base.add(two);
                base.remove(new_item_name);
                base.add(three);
                base.remove(new_item_quantity);
                base.add(four);
                base.remove(new_item_price);
                base.add(five);
                base.remove(update);
                base.add(done);
                base.remove(about);
                base.add(six);

                base.repaint();
            }
        });
        base.add(update);


        Frame.add(base);
        base.add(one);

    }

        }





class addItem extends New_window{
    ResultSet rs;

    addItem(String name,int length,int breadth,data_from_dbms[] a,int Total_data) throws Exception{
        create(name,length,breadth);
        System.out.println("Entering add function.....");
        System.out.println(Total_data);
        System.out.println(a.length);
        int i=0;
        rs = db.alldisplay();
        while(rs.next()){
            i = rs.getInt(1);
        }

        // Need better logic

        JLabel Item_name =new JLabel("Item Name :");
        JLabel Item_id = new JLabel("Item Id :");
        JLabel Item_quantity = new JLabel("Item Quantity :");
        JLabel Item_price = new JLabel("Item Price :");

        TextField Item_name_entry = new TextField();
        TextField Item_id_entry = new TextField(""+(i+1));
        TextField Item_quantity_entry = new TextField();
        TextField Item_price_entry = new TextField();

        Item_id.setBounds(70,60,105,20);
        Item_id_entry.setBounds(165,60,105,20);

        Item_name.setBounds(70,90,105,20);
        Item_name_entry.setBounds(165,90,105,20);

        Item_quantity.setBounds(70,120,105,20);
        Item_quantity_entry.setBounds(165,120,105,20);

        Item_price.setBounds(70,150,105,20);
        Item_price_entry.setBounds(165,150,105,20);

        JLabel Msg = new JLabel();
        Msg.setBounds(180,280,230,50);

        JLabel item_discription = new JLabel("Item Description :");
        item_discription.setBounds(70,180,105,20);

        TextArea entry_Item_disc = new TextArea(20,20);
        entry_Item_disc.setBounds(140,210,200,100);
        Frame.add(entry_Item_disc);

        System.out.println(arraydata.length);



        JButton Add = new JButton("Add");
        Add.setBounds(200,390,80,30);

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg;
                String id = Item_id_entry.getText();
                String name = Item_name_entry.getText();
                String quant = Item_quantity_entry.getText();
                String pri = Item_price_entry.getText();
                try{
                  msg=  db.add("items",id,name,quant,pri,entry_Item_disc.getText());



                  Msg.setText(msg);
                  Item_id_entry.setText(null);
                  Item_name_entry.setText(null);
                  Item_quantity_entry.setText(null);
                  Item_price_entry.setText(null);
                  entry_Item_disc.setText(null);
                  Frame.dispose();

                }catch (Exception c){
                    c.printStackTrace();
                    Item_id_entry.setText(null);
                }


            }
        });


        Frame.add(Item_name_entry);
        Frame.add(Item_quantity_entry);
        Frame.add(Item_id_entry);
        Frame.add(Item_price_entry);

        Frame.add(Item_name);
        Frame.add(Item_id);
        Frame.add(Item_quantity);
        Frame.add(Item_price);

        Frame.add(Add);
        Frame.add(Msg);
        Frame.add(item_discription);
    }


}

class delItem extends New_window{

    delItem(String name,int length, int breadth)throws Exception {
        create(name,length,breadth);

        JLabel Main_label = new JLabel();
        Main_label.setText("Enter the Item Id");
        Main_label.setBounds(180,40,130,40);

        JLabel Item_Id = new JLabel("Item Id :");
        Item_Id.setBounds(60,105,50,25);

        JTextArea Item_Id_entry = new JTextArea();
        Item_Id_entry.setBounds(125,105,105,25);
        Item_Id_entry.setBorder(border);

        JButton check = new JButton("Check");
        check.setBounds(240,105,80,25);

        JLabel dispaly = new JLabel();
        dispaly.setBounds(10,155,460,200);
        dispaly.setBorder(border);



        JLabel dItemid = new JLabel();
        dItemid.setBounds(50,20,160,20);
        dispaly.add(dItemid);
        JLabel dItem_name = new JLabel();
        dItem_name.setBounds(50,40,160,20);
        dispaly.add(dItem_name);
        JLabel dItem_quantity = new JLabel();
        dItem_quantity.setBounds(50,60,160,20);
        dispaly.add(dItem_quantity);
        JLabel dItem_price = new JLabel();
        dItem_price.setBounds(50,80,160,20);
        dispaly.add(dItem_price);

        JButton del = new JButton("Delete Item");
        del.setBounds(70,120,130,30);

        JLabel result = new JLabel();
        result.setBounds(180,350,150,50);


         check.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String did = "Item Not found";
                 String dItem_na = null;
                 String quant = null;
                 String dPrice= null;
                 String id = Item_Id_entry.getText();

                 try{

                     try{
                         ResultSet ms = db.display(id);
                         ms.next();
                         try {
                              did = ms.getString(1);
                              dItem_na = ms.getString(2);
                              quant = ms.getString(3);
                              dPrice = ms.getString(4);
                         }catch (java.sql.SQLException d){
                             d.printStackTrace();
                         }
                         dispaly.add(del);
                         dItemid.setText("Item ID             : "+did);
                         dItem_name.setText("Item                  : "+dItem_na);
                         dItem_quantity.setText("Item Quantity  : "+quant);
                         dItem_price.setText("Item Price        : "+dPrice);

                     }catch (SQLException c){
                         c.printStackTrace();
                     }

                     del.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e){
                             try{
                                 String id = Item_Id_entry.getText();
                                 try{
                                     String out = db.del("items",id);
                                     result.setText(out);
                                 }catch(SQLException b){
                                     b.printStackTrace();
                                 }


                                Frame.dispose();

                             }catch (Exception c){
                                 c.printStackTrace();
                             }

                         }
                     });



                    // System.out.println();


                 }catch (Exception c) {
                     c.printStackTrace();
                 }

             }
         });


        Frame.add(result);
        Frame.add(Item_Id);
        Frame.add(Main_label);
        Frame.add(Item_Id_entry);
        Frame.add(check);
        Frame.add(dispaly);



    }
}

//-------------------------Billing----------------------------
class daily_slales extends New_window{
    mydbms ds = new mydbms();
    private one_sales[] list = new one_sales[50];
    private int i =0;
    private int y= 28;
    ResultSet rs = ds.alldisplay();
    int Totalitems=0;
    int total_v = 0;
    String str_total_v;

    daily_slales(data_from_dbms[] a) throws Exception{

         create("Billing",1000,1000);

         JLabel Heading = new JLabel(" Billing ");
         JLabel cart  = new JLabel();
         cart.setIcon(new ImageIcon("cart.png"));
         cart.setBounds(2,1,70,80);

         Heading.setBorder(border);
         Heading.setHorizontalAlignment(JLabel.CENTER);
         Heading.setFont(new Font("Times New Roman", Font.BOLD, 18));
         Heading.setBounds(250,20,250,80);
         Heading.add(cart);

         JLabel base = new JLabel();
         base.setLayout(null);
         base.setBounds(75,80,1000,1000);
         base.requestFocus(true);


        // entry and label Item Id
         JLabel Itemid = new JLabel("Item ID :");
         TextField entry_Itemid = new TextField();
         Itemid.setBorder(border);
         base.setBorder(border);
         Frame.add(base);

         Itemid.setBounds(150,150,60,25);
         entry_Itemid.setBounds(220,150,50,25);

         // Item Quantity Label and entry
         JLabel ItemQuantity = new JLabel("Item Quantity :");
         TextField entry_ItemQuantity = new TextField();
         ItemQuantity.setBorder(border);

         ItemQuantity.setBounds(300,150,90,25);
         entry_ItemQuantity.setBounds(400,150,50,25);

         // Add to cart button
         // billing area

         JLabel billing_list = new JLabel();
         //billing_list.setBorder(border);
         billing_list.setBounds(150,200,450,550);
         billing_list.setOpaque(true);
         billing_list.setBackground(Color.WHITE);
         base.add(billing_list);


         // Item id label
         JLabel item_id = new JLabel("ITEM ID");
         item_id.setBounds(2,2,60,25);
         item_id.setBorder(border);
         item_id.setHorizontalAlignment(JLabel.CENTER);
         item_id.setOpaque(true);
         item_id.setBackground(Color.cyan);
         billing_list.add(item_id);

         // Item name label
         JLabel item_name = new JLabel("ITEM NAME");
         item_name.setBounds(64,2,140,25);
         item_name.setBorder(border);
         item_name.setHorizontalAlignment(JLabel.CENTER);
         item_name.setOpaque(true);
         item_name.setBackground(Color.cyan);
         billing_list.add(item_name);

         // Item queantity label
         JLabel item_Quantity = new JLabel(" QUANTITY");
         item_Quantity.setBounds(206,2,120,25);
         item_Quantity.setBorder(border);
         item_Quantity.setHorizontalAlignment(JLabel.CENTER);
         item_Quantity.setOpaque(true);
         item_Quantity.setBackground(Color.cyan);
         billing_list.add(item_Quantity);

         // Item price label
         JLabel item_price = new JLabel(" PRICE");
         item_price.setBounds(328,2,120,25);
         item_price.setBorder(border);
         item_price.setHorizontalAlignment(JLabel.CENTER);
         item_price.setOpaque(true);
         item_price.setBackground(Color.cyan);
         billing_list.add(item_price);

         JButton sold = new JButton("Payment Received ");
         sold.setBounds(600,150,150,30);
         sold.setOpaque(true);
         sold.setBackground(Color.GREEN);
         sold.setForeground(Color.BLACK);
         sold.setFocusPainted(false);
         base.add(sold);

         JLabel Total = new JLabel("Total : ");
         JLabel value = new JLabel("0");


         Total.setBounds(152,800,50,30);
         value.setBounds(230,800,50,30);
         base.add(Total);
         base.add(value);




         JButton add_cart = new JButton("Add to cart");
         add_cart.setBounds(460,150,120,30);
         add_cart.setFocusPainted(false);
         add_cart.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {


                 System.out.println(entry_Itemid.getText());
                 System.out.println(entry_ItemQuantity.getText());

                 try {
                     String id = entry_Itemid.getText();
                     ResultSet rs= db.display(id);
                     rs.next();
                     System.out.println("getting output....");
                     System.out.println(rs.getString(2));


                     int item = Integer.parseInt(entry_ItemQuantity.getText());
                     int cost = item* Integer.parseInt(rs.getString(3));
                     System.out.println("its ok");
                     list[i] = new one_sales();
                     list[i].get(rs.getString(1),rs.getString(2),cost,item);

                     JLabel c_item_id = new JLabel(rs.getString(1));
                     c_item_id.setBounds(2,y,60,25);
                     c_item_id.setBorder(border);
                     c_item_id.setOpaque(true);
                     c_item_id.setBackground(Color.CYAN);
                     c_item_id.setHorizontalAlignment(0);
                     c_item_id.areFocusTraversalKeysSet(1);
                     billing_list.add(c_item_id);

                     JLabel c_item_name = new JLabel(rs.getString(2));
                     c_item_name.setBounds(64,y,140,25);
                     c_item_name.setBorder(border);
                     c_item_name.setOpaque(true);
                     c_item_name.setBackground(Color.CYAN);
                     c_item_name.setHorizontalAlignment(0);
                     c_item_name.areFocusTraversalKeysSet(1);
                     billing_list.add(c_item_name);

                     JLabel c_item_quantity = new JLabel(entry_ItemQuantity.getText());
                     c_item_quantity.setBounds(206,y,120,25);
                     c_item_quantity.setBorder(border);
                     c_item_quantity.setOpaque(true);
                     c_item_quantity.setBackground(Color.CYAN);
                     c_item_quantity.setHorizontalAlignment(0);
                     c_item_quantity.areFocusTraversalKeysSet(1);
                     billing_list.add(c_item_quantity);

                     JLabel c_item_price = new JLabel(""+cost);
                     c_item_price.setBounds(328,y,120,25);
                     c_item_price.setBorder(border);
                     c_item_price.setOpaque(true);
                     c_item_price.setBackground(Color.CYAN);
                     c_item_price.setHorizontalAlignment(0);
                     c_item_price.areFocusTraversalKeysSet(1);
                     billing_list.add(c_item_price);

                     total_v += cost;
                     str_total_v = ""+total_v;

                     value.setText(str_total_v);

                     //JLabel contains = new JLabel(list[i].item_name);
                    // contains.setBounds(250,300,50,30);
                     // base.add(contains);
                     i++;
                     y=y+28;
                     Totalitems = Totalitems + 1;
                     Frame.repaint();


                 }catch (Exception c) {
                     //c.printStackTrace();
                     System.out.println("error");
                 }
             }


         });


         sold.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 for(int i=0;i<Totalitems;i++){

                     try {
                         rs = db.display(list[i].item_id);
                         rs.next();
                         int quant = Integer.parseInt(rs.getString(4));
                         quant = quant - list[i].item_quantity;
                        // String hi = quant+"";

                         int sold =rs.getInt(9);
                         sold = sold+list[i].item_quantity;


                         db.C_update(list[i].item_id,quant,sold);
                         dispose();
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }

             }
         });


         base.add(Heading);
         base.add(add_cart);
         base.add(Itemid);
         base.add(entry_Itemid);
         base.add(ItemQuantity);
         base.add(entry_ItemQuantity);
         Frame.repaint();

     }
}


