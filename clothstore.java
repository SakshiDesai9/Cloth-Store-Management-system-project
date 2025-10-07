package cloth;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import  java.util.ArrayList;
public class LoginPage extends JFrame implements ActionListener {
    public static Connection Conn;

    public static Statement s;
  private   JTextField username;
   private JPasswordField password;

  private   JButton login,cancel;

    LoginPage(){

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "sakshi@09");
           // Conn=DriverManager.getConnection(connectionurl);
            System.out.println("Connected");

            s = Conn.createStatement();
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred connecting to the database.");
            System.exit(1);
        }

        // getContentPane().setBackground(Color.WHITE);

        setLayout(null);

        JLabel user=new JLabel("Username");
        user.setBounds(130,150,100,30);
        user.setFont(new Font("serif",Font.PLAIN,20));
        add(user);

        username=new JTextField();
        username.setBounds(350,150,150,40);
        username.setFont(new Font("serif",Font.PLAIN,20));
        add(username);

        JLabel pass=new JLabel("Password");
        pass.setBounds(130,250,100,20);
        pass.setFont(new Font("serif",Font.PLAIN,20));
        add(pass);
        password=new JPasswordField ();
        password.setBounds(350,250,150,40);
        password.setFont(new Font("serif",Font.PLAIN,20));
        add(password);


        login=new JButton("Login");
        setSize(300, 200);
        login.setBounds(150,400,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("serif",Font.PLAIN,20));
        login.addActionListener(this);
        add(login);

        cancel=new JButton("Cancel");
        cancel.setBounds(300,400,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif",Font.PLAIN,20));
        cancel.addActionListener(this);

        add(cancel);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("cloth/images/login_btn.png"));
        Image i2=i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(800,150,600,600);
        add(image);




        setBounds(350,200,800,570);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String user = username.getText();
            String pass = new String(password.getPassword());

            try {
                String query = "select * from login_form where username = '" + user + "' and password = '" + pass + "'";

                ResultSet rs = s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[]args){
          new LoginPage();
    }
}

class Dashboard extends JFrame implements ActionListener{

    //JButton AddCustomer;
    JMenuItem addCustomerMenuItem;
    Dashboard(){
        setBounds(0,0,1550,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("cloth/images/Small-Boutique-Clothing-Store-Interior-Design-Layout-938x521.jpg"));
        Image i2=i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1550,1000);
        add(image);


        JLabel text=new JLabel("SS CLOTH STORE");
        text.setBounds(600,80,1000,100);
        // image.add(text);
        text.setFont(new Font("Tahoma",Font.PLAIN,60));
        text.setForeground(Color.RED);
        image.add(text);


        JMenuBar mb=new JMenuBar();
        mb.setBounds(0,0,1550,30);
         image.add(mb);

        JMenu cloth=new JMenu("CLOTH MANAGEMENT");
        cloth.setForeground(Color.BLACK);
        mb.add(cloth);
        JMenuItem admin=new JMenuItem("ADMIN");
        admin.addActionListener(this);
        cloth.add(admin);


        JMenu customer=new JMenu("CUSTOMER REGISTRATION");
        customer.setForeground(Color.BLUE);
        mb.add(customer);
        addCustomerMenuItem = new JMenuItem("Add Customer");
        addCustomerMenuItem.addActionListener(this);
        customer.add(addCustomerMenuItem);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("CUSTOMER")) {
            // Handle the CUSTOMER menu item
            // ...
        } else if (ae.getActionCommand().equals("  CUSTOMER  ")) {
            new AddCustomer();
        } else if (ae.getActionCommand().equals("ADMIN")) {
            new Admin();
        } else if (ae.getSource() == addCustomerMenuItem) {
            // Handle the "Add Customer" menu item
            new AddCustomer();
        }
    }

    public static void main(String[] args){

      new Dashboard();
    }


}
class AddCustomer extends JFrame implements ActionListener {

    JTextField tfnumber,tfname,tfcity,tfage,tfphone,tfgender;
    JRadioButton rmale, rfemale;
    JButton submit,back;

    private Object conn;


    AddCustomer() {
        setLayout(null);
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);


        setTitle("ADD CUSTOMER DETAILS");



        JLabel lblname=new JLabel("Name");
        lblname.setBounds(60,30,120,30);
        lblname.setFont(new Font("Ralelway",Font.PLAIN,17));
        add(lblname);
        tfname=new JTextField();
        tfname.setBounds(200,30,150,30);
        add(tfname);

        JLabel lblnumber=new JLabel("Number");
        lblnumber.setBounds(60,80,120,30);
        lblnumber.setFont(new Font("Ralelway",Font.PLAIN,17));
        add(lblnumber);

        tfnumber=new JTextField();
        tfnumber.setBounds(200,80,150,30);
        add(tfnumber);
        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(60,130,120,30);
        lblgender.setFont(new Font("Ralelway",Font.PLAIN,17));
        add(lblgender);

        rmale=new JRadioButton("MALE");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,130,70,30);
        add(rmale);

        rfemale=new JRadioButton("FEMALE");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(300,130,100,30);
        add(rfemale);
        JLabel lblage=new JLabel("AGE");
        lblage.setBounds(60,180,120,30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblage);
        tfage=new JTextField();
        tfage.setBounds(200,180,150,30);
        add(tfage);
        JLabel lblphone=new JLabel("PHONE");
        lblphone.setBounds(60,230,120,30);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblphone);

        tfphone=new JTextField();
        tfphone.setBounds(200,230,150,30);
        add(tfphone);


        JLabel lblcity=new JLabel("CITY");
        lblcity.setBounds(60,280,120,30);
        lblcity.setFont(new Font("Ralelway",Font.PLAIN,17));
        add(lblcity);

        tfcity=new JTextField();
        tfcity.setBounds(200,280,150,30);
        add(tfcity);

        submit=new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200,400,150,30);
        submit.addActionListener(this);
        add(submit);



        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200, 320, 150, 30);
        add(back);

        setBounds(350,200,800,550);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String number = tfnumber.getText();
            String age = tfage.getText();
            String phone=tfphone.getText();
            String gender = rmale.isSelected() ? "MALE" : (rfemale.isSelected() ? "FEMALE" : "");
            String city = tfcity.getText();

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name should not be empty");
                return;
            }
            if (age.equals("")) {
                JOptionPane.showMessageDialog(null, "Age should not be empty");
                return;
            }

            if (gender.equals("")) {
                JOptionPane.showMessageDialog(null, "Gender should not be empty");
                return;
            }



            try {
               // //Conn conn = new Conn();
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
                String query = "insert into customer values( ?,?,?,?,?,? )";
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, number);
                    preparedStatement.setString(3, age);
                    preparedStatement.setString(4, gender);
                    preparedStatement.setString(5, phone);
                    preparedStatement.setString(6, city);

                    preparedStatement.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                setVisible(false);


            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding customer");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);

        }
    }
    public static void main(String[]args){

        new AddCustomer();
    }
}




class Admin extends JFrame implements ActionListener{
    JButton newCloth,Category,Clothinfo,searchcloth,update, delete,allcustomers,logout;
    Admin(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
       Category =new JButton("Add Category");
        Category.setBounds(10,30,200,30);
        Category.setBackground(Color.BLACK);
        Category.setForeground(Color.WHITE);
        Category.addActionListener(this);
        add(Category);

        newCloth=new JButton("Add Stock");
        newCloth.setBounds(10,70,200,30);
        newCloth.setBackground(Color.BLACK);
        newCloth.setForeground(Color.WHITE);
        newCloth.addActionListener(this);
        add(newCloth);

        Clothinfo=new JButton("Stock Info");
        Clothinfo.setBounds(10,110,200,30);
        Clothinfo.setBackground(Color.BLACK);
        Clothinfo.setForeground(Color.WHITE);
        Clothinfo.addActionListener(this);
        add(Clothinfo);

        searchcloth=new JButton("Search Stock");
        searchcloth.setBounds(10,150,200,30);
        searchcloth.setBackground(Color.BLACK);
        searchcloth.setForeground(Color.WHITE);
        searchcloth.addActionListener(this);
        add(searchcloth);

        update=new JButton("Update ");
        update.setBounds(10,190,200,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        delete=new JButton("Delete ");
        delete.setBounds(10,230,200,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        allcustomers=new JButton(" Customers Info");
        allcustomers.setBounds(10,270,200,30);
        allcustomers.setBackground(Color.BLACK);
        allcustomers.setForeground(Color.WHITE);
        allcustomers.addActionListener(this);
        add(allcustomers);

        logout=new JButton("Logout");
        logout.setBounds(10,310,200,30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("cloth/images/Small-Boutique-Clothing-Store-Interior-Design-Layout-938x521.jpg"));
        JLabel image=new JLabel(i1);
        image.setBounds(250,30,500,470);
        add(image);
        setBounds(350,200,800,570);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == Category) {
            setVisible(false);
            new AddCategory();
        } else if(ae.getSource() == newCloth){
            setVisible(false);
            new AddCloth();
        }else if(ae.getSource() == Clothinfo) {
            setVisible(false);
           new ClothInfo();
        }else if(ae.getSource() == searchcloth) {
            setVisible(false);
            new SearchCloth();
        }else if(ae.getSource() == update) {
            setVisible(false);
            new UpdateCheck();
        }else if(ae.getSource() == delete) {
            setVisible(false);
            new deletecloth();
        }else if(ae.getSource() == allcustomers) {
            setVisible(false);
            new CustomerInfo();

        }else if(ae.getSource() == logout) {
            setVisible(false);
            System.exit(0);

        }

    }
    public static void main(String[]args){

        new Admin();
    }
}

class AddCategory extends JFrame implements ActionListener {
    private JComboBox<String> categoryComboBox;
    private JTextField newCategoryField;
    private ArrayList<String>categories;
    JButton back;
    public AddCategory(){

        categories=new ArrayList<>();
        categories.add("Mens Clothing");
        categories.add("Womens Clothing");
        categories.add("Kids Clothing");
        categoryComboBox=new JComboBox<>(categories.toArray(new String[0]));
        newCategoryField=new JTextField(20);
        JButton addCategoryButton = new JButton("Add Category");
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Category:"));
        panel.add(categoryComboBox);
        panel.add(new JLabel("New Category:"));
        panel.add(newCategoryField);
        panel.add(addCategoryButton);

        setLayout(new FlowLayout());
        add(panel);

        setTitle("Cloth Store Management System");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200,500,120,50);
        add(back);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("cloth/images/new_1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(4000,50,300,400);
        add(image);


        setBounds(350,200,800,550);

        setVisible(true);
    }

    private void addCategory() {
        String newCategory = newCategoryField.getText();
        if (!newCategory.isEmpty() && !categories.contains(newCategory)) {
            categories.add(newCategory);
            categoryComboBox.addItem(newCategory);
            newCategoryField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Category already exists or is empty.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new AddCategory();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Admin();
    }
}


class AddCloth extends JFrame implements  ActionListener  {
    JButton submit, back;

    JTextField clothId,clothName, clothPrice ,clothQuantity;
    JComboBox<String> categoryComboBox;
    private String id;

    AddCloth() {
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setForeground(Color.BLUE);

        setLayout(null);
       setSize(300,150);
        getContentPane().setBackground(Color.WHITE);

        setTitle("ADD CLOTH DETAILS");

        JLabel lblId = new JLabel("Cloth Id");
        lblId.setBounds(35, 80, 100, 30);
        lblId.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblId);
        clothId = new JTextField();
         clothId.setBounds(200, 80, 150, 25);
        add(clothId);

        JLabel lblName = new JLabel("ClothName ");
        lblName.setBounds(35, 120, 100, 30);
        lblName.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblName);
        clothName = new JTextField();
       clothName.setBounds(200, 120, 150, 25);
        add(clothName);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setBounds(35, 160, 100, 30);
        lblCategory.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblCategory);
        categoryComboBox = new JComboBox<>(new String[]{"Mens Clothing", "Womens Clothing","Kids Clothing"});
        categoryComboBox.setBounds(200, 160, 150, 25);
        add(categoryComboBox);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(35, 200, 100, 30);
        lblPrice.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblPrice);
        clothPrice = new JTextField();
        clothPrice.setBounds(200, 200, 150, 25);
       add(clothPrice);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(35, 240, 100, 30);
        lblQuantity.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblQuantity);
        clothQuantity = new JTextField();
        clothQuantity.setBounds(200, 240, 150, 25);
        add(clothQuantity);

        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 280, 150, 25);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200, 320, 150, 30);
        add(back);

        setBounds(350, 200, 700, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {

            String name = clothName.getText();
            String category = (String) categoryComboBox.getSelectedItem();

           // String quantity = clothQuantity.getText();
            try {
                int id = Integer.parseInt(clothId.getText());
                double price = Double.parseDouble(clothPrice.getText());
                double quantity = Double.parseDouble(clothQuantity.getText());

            if (id <=0 || price <=0 || name.equals(" ")||quantity <=0 ){
                JOptionPane.showMessageDialog(null, "Please enter valid cloth details");
                return;
            }



                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
                String query = "INSERT INTO cloth (id, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, category);
                    preparedStatement.setDouble(4, price);
                    preparedStatement.setDouble(5, quantity);

                    preparedStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "New Cloth Added Successfully");
                setVisible(false);
                new AddCloth();

            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "Cloth with ID " + id + " already exists. Please enter a different ID.");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Admin();
        }
    }

    public static void main(String[] args) {

        new AddCloth();
    }
}

class ClothInfo extends JFrame implements ActionListener{
    JTable table;
    JButton back;

    ClothInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel l1 = new JLabel("Cloth Id");
        l1.setBounds(160, 10, 100, 20);
        add(l1);
        JLabel l2 = new JLabel("Cloth Name");
        l2.setBounds(290, 10, 100, 20);
        add(l2);
        JLabel l3 = new JLabel("Category");
        l3.setBounds(410, 10, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Price(As Per)");
        l4.setBounds(540, 10, 100, 20);
        add(l4);
        JLabel l5 = new JLabel("Quantity");
        l5.setBounds(640, 10, 100, 20);
        add(l5);

        table = new JTable();
        table.setBounds(160, 40, 600, 400);
        add(table);
        fetchData();
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(420,500,120,50);
        add(back);
        setBounds(300,200,1000,600);
        setVisible(true);
    }

    private void fetchData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cloth");
            //table.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Cloth Id");
            model.addColumn("Cloth Name");
            model.addColumn("Category");
            model.addColumn("Price");
            model.addColumn("Quantity");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("price"),
                        rs.getString("quantity")
                });
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

        public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Admin();
    }
    public static void main(String[]args){
        new ClothInfo();
    }
}

class SearchCloth extends JFrame implements ActionListener {
    JComboBox<String> categoryComboBox;
    JButton searchButton, back;
    JTable table;
    SearchCloth() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel lblCategory = new JLabel("Select Category");
        lblCategory.setBounds(30, 30, 120, 20);
        add(lblCategory);

        categoryComboBox = new JComboBox<>(new String[]{"Mens Clothing", "Womens Clothing", "Kids Clothing"});
        categoryComboBox.setBounds(160, 30, 140, 25);
        add(categoryComboBox);
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBounds(300, 30, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        table = new JTable();
        table.setBounds(30, 80, 600, 400);
        add(table);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200, 500, 120, 50);
        add(back);
        setBounds(300, 200, 700, 600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            fetchClothByCategory(selectedCategory);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Admin();
            // Add code to navigate back to the admin page or any other appropriate page
        }
    }
    private void fetchClothByCategory(String category) {
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM cloth WHERE category = '" + category + "'";
        ResultSet rs = stmt.executeQuery(query);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cloth Id");
        model.addColumn("Cloth Name");
        model.addColumn("Category");
        model.addColumn("Price");
        model.addColumn("Quantity");

        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getString("price"),
                    rs.getString("quantity")
            });
        }

        table.setModel(model);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching cloth data.");
    }
}


    public static void main(String[] args) {
        new SearchCloth();
    }
}

class UpdateCheck extends JFrame implements ActionListener {
    private JTextField clothIdField, clothNameField, clothPriceField, clothQuantityField;
    private JComboBox<String> categoryComboBox;
    private JButton updateButton, backButton;
    public UpdateCheck() {
        setLayout(null);
        setTitle("UPDATE CLOTH DETAILS");
        JLabel lblId = new JLabel("Cloth Id");
        lblId.setBounds(35, 30, 100, 30);
        lblId.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblId);
        clothIdField = new JTextField();
        clothIdField.setBounds(200, 30, 150, 25);
        add(clothIdField);

        JLabel lblName = new JLabel("Cloth Name");
        lblName.setBounds(35, 70, 100, 30);
        lblName.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblName);
        clothNameField = new JTextField();
        clothNameField.setBounds(200, 70, 150, 25);
        add(clothNameField);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setBounds(35, 110, 100, 30);
        lblCategory.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblCategory);
        categoryComboBox = new JComboBox<>(new String[]{"Mens Clothing", "Womens Clothing","Kids Clothing"});
        categoryComboBox.setBounds(200, 110, 150, 25);
        add(categoryComboBox);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(35, 150, 120, 30);
        lblPrice.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblPrice);
        clothPriceField = new JTextField();
        clothPriceField.setBounds(200, 150, 150, 25);
        add(clothPriceField);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(35, 190, 120, 30);
        lblQuantity.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblQuantity);
        clothQuantityField = new JTextField();
        clothQuantityField.setBounds(200, 190, 150, 25);
        add(clothQuantityField);

        updateButton = new JButton("UPDATE");
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(200, 230, 150, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setBounds(200, 270, 150, 30);
        add(backButton);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cloth/images/Zdjęcie-23.02.2018-09-31-22.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        add(image);

        setBounds(350, 200, 500, 350);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            String idString = clothIdField.getText();
            String name = clothNameField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            String priceString = clothPriceField.getText();
            String quantityString= clothQuantityField.getText();
            if (idString.equals("") || name.equals("") || priceString.equals("") || quantityString.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter cloth details");
                return;
            }

                try {
                    // Convert String values to appropriate types
                    int id = Integer.parseInt(idString);
                    double price = Double.parseDouble(priceString);
                    double quantity = Double.parseDouble(quantityString);

                    // Check if id is positive
                    if (id <= 0 || price <= 0 || quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter valid cloth details");
                        return;
                    }


                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
                String query = "UPDATE cloth SET name=?, category=?, price=?, quantity=? WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, category);
                    preparedStatement.setDouble(3, price);
                    preparedStatement.setDouble(4, quantity);
                    preparedStatement.setInt(5, id);

                    preparedStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Cloth Details Updated Successfully");
                setVisible(false);
                new ClothInfo();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating cloth details.");
            }

        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Admin();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateCheck());
    }
}


class CustomerInfo extends JFrame implements ActionListener{
    JTable table;
    JButton back;

    DefaultTableModel model;
    CustomerInfo(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel(new GridLayout(1, 6));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel(""));

        add(headerPanel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(back);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //setBounds(300, 200, 1000, 600);
        setVisible(true);

        // Fetch data and populate the table
        fetchDataAndPopulateTable();
    }
    private void fetchDataAndPopulateTable() {

        try {
            //Conn c= new Conn();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
            //table.setModel(DbUtils.resultSetToTableModel(rs));
            model = new DefaultTableModel();
            model.addColumn("name");
            model.addColumn("number");
            model.addColumn("age");
            model.addColumn("gender");
            model.addColumn("phone");
            model.addColumn("city");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("number"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("city")
                });
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

        public void actionPerformed(ActionEvent ae){
            if (ae.getSource() == back) {
        setVisible(false);
        new Admin();

            }
                }
    public static void main(String[]args){
        new CustomerInfo();
    }
}




class deletecloth extends JFrame implements ActionListener{
    JButton delete, back;
    JTextField clothId;

    deletecloth() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(300, 150);
        getContentPane().setBackground(Color.WHITE);

        setTitle("DELETE CLOTH");

        JLabel lblId = new JLabel("Cloth Id");
        lblId.setBounds(35, 80, 100, 30);
        lblId.setFont(new Font("Ralelway", Font.PLAIN, 17));
        add(lblId);
        clothId = new JTextField();
        clothId.setBounds(200, 80, 150, 25);
        add(clothId);

        delete = new JButton("DELETE");
        delete.setBackground(Color.RED);
        delete.setForeground(Color.WHITE);
        delete.setBounds(200, 120, 150, 25);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200, 160, 150, 30);
        add(back);

        setBounds(350, 200, 400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                int id = Integer.parseInt(clothId.getText());

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth store", "root", "PHW#84#jeor");
                String query = "DELETE FROM cloth WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setInt(1, id);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cloth with ID " + id + " deleted successfully");
                        setVisible(false);
                        new  ClothInfo();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cloth with ID " + id + " not found");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            // Replace 'YourMainClass' with the actual class name of your main program
            new Admin();
        }
    }

    public static void main(String[] args) {
        new deletecloth();
    }
}
