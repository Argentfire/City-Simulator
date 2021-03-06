package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Base extends JFrame implements ActionListener {


    ResultSet result = null;
    Connection con;
    PreparedStatement state;

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Settings");
    JMenuItem population = new JMenuItem("Population");

    JButton button1 = new JButton("City Hall");
    JButton button2 = new JButton("Add City Hall");


    JButton addHuman = new JButton("Add Human");
    JButton addHumans = new JButton("Add Humans");

    JPanel topPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel botPanel = new JPanel();

    CityHall cityHall = new CityHall();

    static int generalPopulation;
    int totalPopulation;
    public static Humans[] people;

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    JScrollBar scrollBar = new JScrollBar();

    JTable table2 = new JTable();
    JScrollPane scrollPane2 = new JScrollPane(table2);

    JButton testButton = new JButton("Test Button");

    ArrayList<Humans> list = new ArrayList<>();

    public Base(){

        this.setSize(800, 800);
        this.setTitle("City Simulator");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(population);
        population.addActionListener(this);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con = DBConnect.getConnection();

                    state = con.prepareStatement("select * from departments where type=?;");
                    state.setInt(1, 1);
                    result = state.executeQuery();
                    while (result.next()) {
                        String name = result.getString("name");
                        int level = result.getInt("level");
                        int workers = result.getInt("workers");

                        String cityHallInformation = "City Hall Name: " + name + "\nCity Hall Level: " + level + "\nCity Hall Workers: " + workers;
                        JOptionPane.showMessageDialog(null, cityHallInformation, "City Hall", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCityHallPanel asdf = new setCityHallPanel();
            }
        });

        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(button1);
        topPanel.add(button2);

        midPanel.setLayout(new GridLayout(2, 1));
        midPanel.add(scrollPane);
        midPanel.add(scrollPane2);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        scrollPane2.setPreferredSize(new Dimension(400, 100));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
        refreshTable(table, "population");
        refreshTable(table2, "humans");

        botPanel.add(addHuman);
        botPanel.add(addHumans);
        botPanel.add(testButton);


        addHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Humans human = Humans.HumanGenerator.generate();
                try {
                    con = DBConnect.getConnection();

                    String sql = "INSERT INTO humans VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);" + "";

                    state = con.prepareStatement(sql);
                    state.setString(1, human.getFirstName());
                    state.setString(2, human.getLastName());
                    state.setInt(3, human.getAge());
                    state.setString(4, human.getEthnicity());
                    state.setString(5, human.getReligion());
                    state.setFloat(6, human.getHeight());
                    state.setFloat(7, human.getWeight());
                    state.setBoolean(8, human.isElementary());
                    state.setBoolean(9, human.isHighSchool());
                    state.setBoolean(10, human.isMaster());
                    state.setBoolean(11, human.isEmployed());
                    state.setDouble(12, human.getSalary());
                    state.setBoolean(13, human.isAlive());
                    state.setBoolean(14, human.isHealthy());
                    state.setString(15, human.getGender());
                    state.execute();

                    int id = 1;
                    int alive = 0;
                    int deceased = 0;
                    int healthy = 0;
                    int sick = 0;
                    int total = 0;

                    state = con.prepareStatement("SELECT alive, healthy FROM humans");
                    result = state.executeQuery();
                    while(result.next()){
                        if(result.getBoolean("alive") == true) {
                            alive++;
                        }
                        else {
                            deceased++;
                        }
                        if(result.getBoolean("healthy") == true) {
                            healthy++;
                        }
                        else {
                            sick++;
                        }
                        total++;
                    }

                    if(human.isAlive() == true) {
                        alive++;
                        if(human.isHealthy() == true) {
                            healthy++;
                        }
                        else {
                            sick++;
                        }
                    }
                    else {
                        deceased++;
                    }

                    state = con.prepareStatement("SELECT total FROM population WHERE id=?;");
                    state.setInt(1,1);
                    state.executeQuery();
                    while (result.next()) {
                        total = result.getInt("total");
                    }
                    total++;

                    state = con.prepareStatement("UPDATE population SET alive=?, deceased=?, healthy=?, sick=?, total=? WHERE id=?;");

                    state.setInt(1, alive);
                    state.setInt(2, deceased);
                    state.setInt(3, healthy);
                    state.setInt(4, sick);
                    state.setInt(5, total);
                    state.setInt(6, id);
                    state.execute();

                    refreshTable(table, "population");
                    refreshTable(table2, "humans");
                } catch(SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if(result != null) {
                        try {
                            result.close();
                        } catch(SQLException ex) { }
                    }
                    if(state != null) {
                        try {
                            state.close();
                        } catch(SQLException ex) { }
                    }
                    if(con != null) {
                        try {
                            con.close();
                        } catch(SQLException ex) { }
                    }
                }
            }
        });

        addHumans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                make();
            }
        });

        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //testMethod();

                try {
                    con = DBConnect.getConnection();

                    int alive = 0;
                    int deceased = 0;
                    int healthy = 0;
                    int sick = 0;
                    int total = 0;
                    state = con.prepareStatement("SELECT alive, healthy FROM humans");
                    result = state.executeQuery();
                    while(result.next()){
                        if(result.getBoolean("alive") == true) {
                            alive++;
                        }
                        else {
                            deceased++;
                        }
                        if(result.getBoolean("healthy") == true) {
                            healthy++;
                        }
                        else {
                            sick++;
                        }
                        total++;
                    }
                    state = con.prepareStatement("UPDATE population SET alive=?, deceased=?, healthy=?, sick=?, total=? WHERE id=?");

                    state.setInt(1, alive);
                    state.setInt(2, deceased);
                    state.setInt(3, healthy);
                    state.setInt(4, sick);
                    state.setInt(5, total);
                    state.setInt(6, 1);
                    state.execute();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if(result != null) {
                        try {
                            result.close();
                        } catch(SQLException ex) { }
                    }
                    if(state != null) {
                        try {
                            state.close();
                        } catch(SQLException ex) { }
                    }
                    if(con != null) {
                        try {
                            con.close();
                        } catch(SQLException ex) { }
                    }
                }
                refreshTable(table, "population");
                refreshTable(table2, "humans");
            }
        });


        container.add(topPanel, BorderLayout.NORTH);
        container.add(midPanel, BorderLayout.CENTER);
        container.add(botPanel, BorderLayout.SOUTH);
    }

    public static void make() {
        people = new Humans[generalPopulation];

        for(int i = 0; i < people.length; i++) {
            Humans person = Humans.HumanGenerator.generate();
            people[i] = person;
        }
    }

    void refreshTable(JTable table, String tableName) {
        try {
            table.setModel(new MyModel(selectTable(tableName)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet selectTable(String table) {
        con = DBConnect.getConnection();
        String sql = "SELECT * FROM " + table;

        try {
            state = con.prepareStatement(sql);
            result = state.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    void changeCityHallDB(String name, int level, int workers) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == population) {
            setPopulation a = new setPopulation();
        }
    }


    class setPopulation extends JFrame {
        JButton setButton = new JButton("Set");
        JPanel topPane = new JPanel();
        JPanel botPane = new JPanel();
        JLabel popLabel = new JLabel("Population:");
        JTextField popTF = new JTextField();

        public setPopulation() {
            this.setSize(300, 200);
            this.setTitle("Population Settings");
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            topPane.setLayout(new GridLayout(1, 2));
            topPane.add(popLabel);
            topPane.add(popTF);

            botPane.add(setButton);

            Container container = this.getContentPane();
            container.setLayout(new BorderLayout());
            container.add(topPane, BorderLayout.NORTH);
            container.add(botPane, BorderLayout.SOUTH);

            setButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    generalPopulation = Integer.parseInt(popTF.getText());
                    dispose();
                }
            });
        }
    }
    class setCityHallPanel extends JFrame {

        JButton setButton = new JButton("Set");
        JPanel topPane = new JPanel();
        JPanel botPane = new JPanel();

        JLabel nameSetLabel = new JLabel("Name:");
        JLabel levelSetLabel = new JLabel("Level:");
        JLabel workersSetLabel = new JLabel("Workers:");

        JTextField nameSetTF = new JTextField();
        JTextField levelSetTF = new JTextField();
        JTextField workersSetTF = new JTextField();

        public setCityHallPanel() {
            this.setSize(350, 200);
            this.setTitle("City Hall Menu");
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            this.setLayout(new GridLayout(2, 1));
            this.add(topPane);
            this.add(botPane);

            topPane.setLayout(new GridLayout(3, 2));
            topPane.add(nameSetLabel);
            topPane.add(nameSetTF);
            topPane.add(levelSetLabel);
            topPane.add(levelSetTF);
            topPane.add(workersSetLabel);
            topPane.add(workersSetTF);

            botPane.add(setButton);
            setButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String checker = nameSetTF.getText();
                    checker = checker.trim();
                    nameSetTF.setText(nameSetTF.getText().trim());
                    if(nameSetTF.getText().length() != 0 && levelSetTF != null && workersSetTF != null) {
                        /*try {
                            con = DBConnect.getConnection();

                            state = con.prepareStatement("select * from departments where name=?;");
                            state.setInt(1, 1);
                            result = state.executeQuery();
                            while (result.next()) {
                                String name = result.getString("name");
                                int level = result.getInt("level");
                                int workers = result.getInt("workers");
                            }
                        }catch(SQLException ex) {
                            ex.printStackTrace();
                        }*/
                        try {
                            cityHall.setName(nameSetTF.getText());
                            cityHall.setLevel(Integer.parseInt(levelSetTF.getText()));
                            cityHall.setWorkers(Integer.parseInt(workersSetTF.getText()));

                            try {
                                con = DBConnect.getConnection();
                                String sql = "Insert into departments values(null, ?,?,?,?,?,?);" + "";

                                state = con.prepareStatement(sql);
                                String name = cityHall.getName();
                                int level = cityHall.getLevel();
                                int workers = cityHall.getWorkers();
                                int type = 1;
                                double vault = 0.0;
                                double debt = 0.0;

                                state.setString(1, name);
                                state.setInt(2, level);
                                state.setInt(3, workers);
                                state.setInt(4, type);
                                state.setDouble(5, vault);
                                state.setDouble(6, debt);

                                state.execute();
                            } catch(SQLException ex) {
                                ex.printStackTrace();
                            } finally {
                                if(result != null) {
                                    try {
                                        result.close();
                                    } catch(SQLException ex) { }
                                }
                                if(state != null) {
                                    try {
                                        state.close();
                                    } catch(SQLException ex) { }
                                }
                                if(con != null) {
                                    try {
                                        con.close();
                                    } catch(SQLException ex) { }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"One or more fields are empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
