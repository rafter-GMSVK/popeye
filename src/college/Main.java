package college;

import com.mysql.cj.log.Log;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;



class Win extends JFrame {

    public JFrame root;
    public JFrame c;
    public int length;
    public int breadth;




    Win(String Window_name) {

        root = new JFrame(Window_name);
        length = 1200;
        breadth = 1000;
        root.setSize(length, breadth);
        root.setLocationRelativeTo(null);
        root.setResizable(false);
        root.setLayout(null);
        root.setVisible(true);





    }

    //Login window
    void get_Creadentials() {
        c = new JFrame("Popeye");
        String[] a = new String[20];
        int i =0;
        try{
            File open = new File("Details.txt");
            Scanner read = new Scanner(open);
            while (read.hasNext()){
                a[i] = read.nextLine();
                i++;
            }

        }catch (IOException c){
            c.printStackTrace();
        }



        //Popeye Image
        JLabel Background = new JLabel(new ImageIcon("one.gif"));
        Background.setVisible(true);
        Background.setIcon(new ImageIcon("one.gif"));
        Background.setBounds(600, 50, 500, 800);

        //Heading
        JLabel Heading1 = new JLabel("Please Enter your Details ...");
        Heading1.setBounds(350, 100, 1000, 50);
        Heading1.setFont(new Font("Times New Roman", Font.BOLD, 30));

        // Username label
        JLabel Username = new JLabel("Username : ");
        Username.setBounds(250, 250, 200, 50);
        Username.setFont(new Font("Serif", Font.BOLD, 24)); // Font name, Font type, Font Size

        // Usernamae Textbox
        JTextField User = new JTextField();
        User.setBounds(400, 265, 200, 30);

        // Password Label
        JLabel Password = new JLabel("Password  : ");
        Password.setBounds(250, 300, 200, 50);
        Password.setFont(new Font("Serif", Font.BOLD, 24));

        // Usernamae Textbox
        JPasswordField Pass = new JPasswordField();
        Pass.setBounds(400, 315, 200, 30);

        JLabel error = new JLabel("Invalid username or password");
        error.setFont(new Font("Google Sans",Font.PLAIN,25));
        error.setBounds(230,450,500,50);

        // Login Button
        JButton Login = new JButton("Log in");
        Login.setBounds(450, 370, 100, 30);
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("level one clear");
                System.out.println(a[0]);
                System.out.println(a[1]);
                try {

                    mydbms db = new mydbms();
                    ResultSet rs = db.allusers();

                    while (rs.next()){
                       if(rs.getString(2).equals(User.getText()) && rs.getString(3).equals(Pass.getText())){
                           System.out.println("Level two Clear");
                           dashboard main = new dashboard(rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6));
                           c.dispose();
                           break;
                       }
                       else{
                           c.add(error);
                           c.repaint();
                       }
                    }


                }catch (Exception c){
                    c.printStackTrace();
                }
            }
        });

        // should add a action listner
        c.setSize(length, breadth);
        c.setVisible(true);
        c.setLocationRelativeTo(null);
        c.setLayout(null);
        c.add(Login);
        c.add(Background);
        c.add(Heading1);
        c.add(Username);
        c.add(User);
        c.add(Password);
        c.add(Pass);

    }

    //account setup
    void set_my_account() {

        root.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int i;
        JLabel Heading2 = new JLabel("Create a User");
        Heading2.setBounds(50, 30, 1000, 50);
        Heading2.setFont(new Font("Times New Roman", Font.BOLD, 30));

        // username
        JLabel Username = new JLabel("Username");
        Username.setBounds(650, 150, 80, 20);
        Username.setHorizontalAlignment(JLabel.RIGHT);
        Username.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font name, Font type, Font Size

        JTextArea UserEntry = new JTextArea();
        UserEntry.setBounds(660, 175, 150, 30);
        UserEntry.setFont(new Font("Roboto", Font.PLAIN, 16));
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
        UserEntry.setBorder(border);

        //Password
        JLabel Password = new JLabel("Password");
        Password.setBounds(850, 150, 80, 20);
        Password.setHorizontalAlignment(JLabel.RIGHT);
        Password.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font name, Font type, Font Size

        JTextArea PassEntry = new JTextArea();
        PassEntry.setBounds(860, 175, 150, 30);
        PassEntry.setFont(new Font("Roboto", Font.PLAIN, 16));
        PassEntry.setBorder(border);

        //Email address
        JLabel Email = new JLabel("Email");
        Email.setBounds(660, 225, 80, 20);
        Email.setHorizontalAlignment(JLabel.LEFT);
        Email.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font name, Font type, Font Size

        JTextArea EmailEntry = new JTextArea();
        EmailEntry.setBounds(660, 250, 350, 30);
        EmailEntry.setFont(new Font("Roboto", Font.PLAIN, 16));
        EmailEntry.setBorder(border);

        //Address
        JLabel Address = new JLabel("Address");
        Address.setBounds(660, 295, 80, 20);
        Address.setHorizontalAlignment(JLabel.LEFT);
        Address.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font name, Font type, Font Size

        JTextArea AddressEntry = new JTextArea();
        AddressEntry.setBounds(660, 320, 350, 50);
        AddressEntry.setFont(new Font("Roboto", Font.PLAIN, 16));
        AddressEntry.setBorder(border);

        //Phone number
        JLabel Phone = new JLabel("Mobile");
        Phone.setBounds(660, 390, 80, 20);
        Phone.setHorizontalAlignment(JLabel.LEFT);
        Phone.setFont(new Font("Roboto", Font.PLAIN, 16)); // Font name, Font type, Font Size

        JTextArea PhoneEntry = new JTextArea();
        PhoneEntry.setBounds(660, 410, 150, 30);
        PhoneEntry.setFont(new Font("Roboto", Font.PLAIN, 16));
        PhoneEntry.setBorder(border);

        //Popeye profile Image
        JLabel Background = new JLabel(new ImageIcon("profile.png"));
        Background.setVisible(true);
        Background.setIcon(new ImageIcon("profile.png"));
        Background.setBounds(100, 50, 500, 500);
        root.repaint();

        //Create account button
        JButton create = new JButton("Create account");
        create.setBounds(660, 470, 150, 30);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String one = UserEntry.getText();
                String two = PassEntry.getText();
                String three = AddressEntry.getText();
                String four = PhoneEntry.getText();
                String five = EmailEntry.getText();

                try{
                    int i =0;
                    mydbms db = new mydbms();
                    ResultSet rs = db.allusers();
                    while(rs.next()){
                        i++;
                    }
                    db.add_user(""+(i+1),one,two,four,three,five);
                }catch (Exception c){
                    c.printStackTrace();
                }

            }
        });

        //Login button
        JButton have_account = new JButton("Log in");
        have_account.setBounds(850, 470, 150, 30);
        have_account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                root.dispose();// removes the window
                get_Creadentials();
            }
        });

        root.add(Heading2);
        root.add(Username);
        root.add(UserEntry);
        root.add(Password);
        root.add(PassEntry);
        root.add(Email);
        root.add(EmailEntry);
        root.add(Address);
        root.add(AddressEntry);
        root.add(Phone);
        root.add(PhoneEntry);
        root.add(create);
        root.add(have_account);
        root.add(Background);
        root.repaint();

       // root.dispose();

    }


}

public class Main {

    public static void main(String[] args) throws Exception{
      // main login in window
     Win Root = new Win("Popeye");
      Root.set_my_account();
        //getdata get = new getdata();

    }

}


