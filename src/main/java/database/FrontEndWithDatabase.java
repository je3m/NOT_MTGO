package database;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;



public class FrontEndWithDatabase {
	public static void main(String[] args) throws SQLException{
		FrontEndWithDatabase fd = new FrontEndWithDatabase();
	}
		
	FrontEndWithDatabase() throws SQLException{
		String connectionString =
				"jdbc:sqlserver://titan.csse.rose-hulman.edu;"
						+ "database=!MTGO;"
						+ "user=malinocr;"
						+ "password=#blue1baby1;"
						+ "encrypt=true;"
						+ "trustServerCertificate=true;"
						+ "hostNameInCertificate=*.database.windows.net;"
						+ "loginTimeout=30;";

		// Declare the JDBC objects.
		final Connection connection = DriverManager.getConnection(connectionString);
			
		int width = 1000;
		int height = 1000;
		Dimension buttonDimension = new Dimension((int)(width*.2),25);
		Dimension bigButtonDimension = new Dimension((int)(width*.4),(int)(height*.1));

		final JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Deck Maker");
		Frame.setBounds(0, 0, width, height);
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Frame.setLayout(new FlowLayout());

		JPanel loginPanel = new JPanel();
		loginPanel.setPreferredSize(new Dimension((int)(width*0.3), (int)(height*0.8)));
		
		JLabel unLabel = new JLabel("Username");
		unLabel.setHorizontalAlignment(JLabel.CENTER);
		unLabel.setPreferredSize(new Dimension((int)(width*0.1),(int)(height*.04)));
		
		JTextField userName = new JTextField();
		userName.setPreferredSize(new Dimension((int)(width*.15),25));
		
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setHorizontalAlignment(JLabel.CENTER);
		pwLabel.setPreferredSize(new Dimension((int)(width*0.1),(int)(height*.04)));
		
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension((int)(width*.15),25));

		JButton login = new JButton("Login");
		login.setPreferredSize(buttonDimension);
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(SQLDatabaseConnection.isRegistered(connection, userName.getText(), password.getText())){
						Frame.remove(loginPanel);
						Frame.add(createInitialScreen(connection, width, height, bigButtonDimension, buttonDimension, Frame, loginPanel, userName.getText(), password.getText()));
						Frame.revalidate();
						Frame.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Username or password was not valid. Please try again.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton register = new JButton("Register");
		register.setPreferredSize(buttonDimension);
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(SQLDatabaseConnection.register(connection, userName.getText(), password.getText())){
						JOptionPane.showMessageDialog(null, "Registration successful.");
					} else {
						JOptionPane.showMessageDialog(null, "Username is already in use. Please try again.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		loginPanel.add(unLabel);
		loginPanel.add(userName);
		loginPanel.add(pwLabel);
		loginPanel.add(password);
		loginPanel.add(login);
		loginPanel.add(register);

		Frame.add(loginPanel,BorderLayout.CENTER);

		Frame.revalidate();
		Frame.repaint();
	}

	public JPanel createDeckBuilder(Connection connection, int width, int height, Dimension buttonDimension, JPanel initialScreen, JFrame Frame, String text1, String text2, String username, String password) throws SQLException{
		String[] str;
		String[] str1;
		String[] lol = {"str"};
		
		JPanel deckBuilder = new JPanel();
		deckBuilder.setPreferredSize(new Dimension((int)(width*1.1), height));
		deckBuilder.setLayout(new FlowLayout());

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension((int)(width*1.5),(int)(height*.05)));

		JLabel label1 = new JLabel(text1);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setPreferredSize(new Dimension((int)(width*0.35),(int)(height*.04)));

		String[] leftDropText;
		
		if(text1.equals("Card List")){
			leftDropText = new String[0];
		} else {
			leftDropText = SQLDatabaseConnection.getCollectionNames(connection, username, password);
		}
		
		JComboBox<String> dropDownLeft = new JComboBox<String>(leftDropText);
		dropDownLeft.setPreferredSize(new Dimension((int)(width*.15),25));
		
		String[] rightDropText;
		
		if(text2.equals("Deck")){
			rightDropText = SQLDatabaseConnection.getDeckNames(connection, username, password);
		} else {
			rightDropText = SQLDatabaseConnection.getCollectionNames(connection, username, password);
		}
		
		JLabel label2 = new JLabel(text2);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setPreferredSize(new Dimension((int)(width*0.35),(int)(height*.04)));

		JComboBox<String> dropDownRight = new JComboBox<String>(rightDropText);
		dropDownRight.setPreferredSize(new Dimension((int)(width*.15),25));
		
		if(text1.equals("Card List")){
			str = SQLDatabaseConnection.getCardList(connection);
		} else {
			if(dropDownLeft.getSelectedItem() != null){
				str = SQLDatabaseConnection.getCardsinCollection(connection, username, password, dropDownLeft.getSelectedItem().toString());
			} else {
				str = new String[0];
			}
		}

		if(dropDownRight.getSelectedItem() != null){
			if(text2.equals("Collection")){
				str1 = SQLDatabaseConnection.getCardsinCollection(connection, username, password, dropDownRight.getSelectedItem().toString());
			} else {
				str1 = SQLDatabaseConnection.getCardsinDeck(connection, username, password,dropDownRight.getSelectedItem().toString());
			}
		} else {
			str1 = new String[0];
		}
		
		JList<String> jlist = new JList<String>(str);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL);
		jlist.setVisibleRowCount(-1);

		JList<String> jlist1 = new JList<String>(str1);
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist1.setLayoutOrientation(JList.VERTICAL);
		jlist1.setVisibleRowCount(-1);
		
		dropDownLeft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text2.equals("Card List")){
				} else {
					try {
						jlist.setListData(SQLDatabaseConnection.getCardsinCollection(connection, username, password,dropDownLeft.getSelectedItem().toString()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		dropDownRight.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text2.equals("Deck")){
					try {
						jlist1.setListData(SQLDatabaseConnection.getCardsinDeck(connection, username, password,dropDownRight.getSelectedItem().toString()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						jlist1.setListData(SQLDatabaseConnection.getCardsinCollection(connection, username, password,dropDownRight.getSelectedItem().toString()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane listScroller = new JScrollPane(jlist);
		listScroller.setPreferredSize(new Dimension((int)(width*.5),(int)(height*.8)));
		JScrollPane listScroller1 = new JScrollPane(jlist1);
		listScroller1.setPreferredSize(new Dimension((int)(width*.5),(int)(height*.8)));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension((width),(int)(height*.2)));

		JTextField searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension((int)(width*.15),25));

		String[] dropDownOptions = new String[6];
		dropDownOptions[0] = "Name";
		dropDownOptions[1] = "Mana Cost";
		dropDownOptions[2] = "Type";
		dropDownOptions[3] = "Color";
		dropDownOptions[4] = "Power";
		dropDownOptions[5] = "Thoughness";

		JComboBox<String> dropDown = new JComboBox<String>(dropDownOptions);
		dropDown.setPreferredSize(new Dimension((int)(width*.15),25));

		JButton search = new JButton("Search");
		search.setPreferredSize(buttonDimension);
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = dropDown.getSelectedItem().toString();
				String otherSelected = searchBar.getText();
				System.out.println("Searching by " +  selected + " for " + otherSelected);
			}
		});


		JButton add = new JButton("Add to " + text2);
		add.setPreferredSize(buttonDimension);
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text2.equals("Deck")){
					if(SQLDatabaseConnection.addCardtoDeck(connection, username, password, dropDownRight.getSelectedItem().toString(), jlist.getSelectedValue().toString(), "1")){
						try {
							jlist1.setListData(SQLDatabaseConnection.getCardsinDeck(connection, username, password,dropDownRight.getSelectedItem().toString()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Card cannot be added to deck.");
					}
				} else {
					if(SQLDatabaseConnection.addCardtoCollection(connection, username, password, dropDownRight.getSelectedItem().toString(), jlist.getSelectedValue().toString(), "1")){
						try {
							jlist1.setListData(SQLDatabaseConnection.getCardsinCollection(connection, username, password,dropDownRight.getSelectedItem().toString()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Card cannot be added to collection.");
					}
				}
			}
		});

		JButton remove = new JButton("Remove from " + text2);
		remove.setPreferredSize(buttonDimension);
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text2.equals("Deck")){
					if(SQLDatabaseConnection.addCardtoDeck(connection, username, password, dropDownRight.getSelectedItem().toString(), jlist1.getSelectedValue().toString().split(":")[0], "-1")){
						try {
							jlist1.setListData(SQLDatabaseConnection.getCardsinDeck(connection, username, password,dropDownRight.getSelectedItem().toString()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Card cannot be added to deck.");
					}
				} else {
					if(SQLDatabaseConnection.addCardtoCollection(connection, username, password, dropDownRight.getSelectedItem().toString(), jlist1.getSelectedValue().toString().split(":")[0], "-1")){
						try {
							jlist1.setListData(SQLDatabaseConnection.getCardsinCollection(connection, username, password,dropDownRight.getSelectedItem().toString()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Card cannot be added to collection.");
					}
				}
			}
		});
		
		JTextField newDeckName = new JTextField();
		newDeckName.setPreferredSize(new Dimension((int)(width*.15),25));
		
		JButton newDeck = new JButton("Create new " + text2);
		newDeck.setPreferredSize(buttonDimension);
		newDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text2.equals("Deck")){
					if(SQLDatabaseConnection.addNewDeck(connection, username, password,newDeckName.getText())){
						JOptionPane.showMessageDialog(null, "New deck created!");
						dropDownRight.addItem(newDeckName.getText());
					} else {
						JOptionPane.showMessageDialog(null, "Deck name already exists. Please choose another.");
					}
				} else {
					if(SQLDatabaseConnection.addNewCollection(connection, username, password,newDeckName.getText())){
						JOptionPane.showMessageDialog(null, "New collection created!");
						dropDownRight.addItem(newDeckName.getText());
					} else {
						JOptionPane.showMessageDialog(null, "Collection name already exists. Please choose another.");
					}
				}
			}
		});

		JButton mainMenu = new JButton("Return to Main Menu");
		mainMenu.setPreferredSize(buttonDimension);
		mainMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(deckBuilder);
				Frame.add(initialScreen);
				Frame.revalidate();
				Frame.repaint();
				Frame.setLayout(new FlowLayout());
			}
		});

		buttonPanel.add(searchBar);
		buttonPanel.add(dropDown);
		buttonPanel.add(search);
		buttonPanel.add(add);
		buttonPanel.add(remove);
		buttonPanel.add(newDeckName);
		buttonPanel.add(newDeck);
		buttonPanel.add(mainMenu);

		labelPanel.add(label1);
		labelPanel.add(dropDownLeft);
		labelPanel.add(label2);
		labelPanel.add(dropDownRight);

		deckBuilder.add(labelPanel);
		deckBuilder.add(listScroller);
		deckBuilder.add(listScroller1);
		deckBuilder.add(buttonPanel);
		
		return deckBuilder;
		
		
	}
	
	public JPanel createInitialScreen(Connection connection, int width, int height, Dimension bigButtonDimension, Dimension buttonDimension, JFrame Frame, JPanel loginPanel, String username, String password) throws SQLException{
		JPanel initialScreen = new JPanel();
		initialScreen.setPreferredSize(new Dimension(width, height));
		
		JButton goToDB = new JButton("Go to Deck Builder");
		goToDB.setPreferredSize(bigButtonDimension);
		goToDB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(initialScreen);
				try {
					Frame.add(createDeckBuilder(connection, width, height, buttonDimension, initialScreen, Frame, "Card List", "Deck", username, password));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Frame.revalidate();
				Frame.repaint();
			}
		});

		JButton goToCB = new JButton("Go to Collection Builder");
		goToCB.setPreferredSize(bigButtonDimension);
		goToCB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(initialScreen);
				try {
					Frame.add(createDeckBuilder(connection, width, height, buttonDimension, initialScreen, Frame, "Card List", "Collection",  username, password));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Frame.revalidate();
				Frame.repaint();
			}
		});

		JButton goToCDB = new JButton("Go to Collection Deck Builder");
		goToCDB.setPreferredSize(bigButtonDimension);
		goToCDB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(initialScreen);
				try {
					Frame.add(createDeckBuilder(connection, width, height, buttonDimension, initialScreen, Frame, "Collection", "Deck",  username, password));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Frame.revalidate();
				Frame.repaint();
			}
		});
		
		JButton logOut = new JButton("Log Out");
		logOut.setPreferredSize(bigButtonDimension);
		logOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(initialScreen);
				Frame.add(loginPanel);
				Frame.revalidate();
				Frame.repaint();
			}
		});
		
		String[] decks1 = SQLDatabaseConnection.getDeckNames(connection, username, password);
		
		String[] decks2 = SQLDatabaseConnection.getDeckNames(connection, username, password);
		
		JLabel deck1 = new JLabel("Deck 1");
		deck1.setHorizontalAlignment(JLabel.CENTER);
		deck1.setPreferredSize(new Dimension((int)(width*0.2),(int)(height*.04)));
		
		JList<String> deck1List = new JList<String>(decks1);
		deck1List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deck1List.setLayoutOrientation(JList.VERTICAL);
		deck1List.setVisibleRowCount(-1);
		
		JScrollPane deck1listScroller = new JScrollPane(deck1List);
		deck1listScroller.setPreferredSize(new Dimension((int)(width*.2),(int)(height*.1)));
		
		JLabel deck2 = new JLabel("Deck 2");
		deck2.setHorizontalAlignment(JLabel.CENTER);
		deck2.setPreferredSize(new Dimension((int)(width*0.2),(int)(height*.04)));
		
		JList<String> deck2List = new JList<String>(decks2);
		deck2List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deck2List.setLayoutOrientation(JList.VERTICAL);
		deck2List.setVisibleRowCount(-1);
		
		JScrollPane deck2listScroller = new JScrollPane(deck2List);
		deck2listScroller.setPreferredSize(new Dimension((int)(width*.2),(int)(height*.1)));
		
		JButton playGame = new JButton("Play Game");
		playGame.setPreferredSize(bigButtonDimension);
		playGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = deck1List.getSelectedValue();
				String selected1 = deck2List.getSelectedValue();
				System.out.println("Play game with " + selected + " against " + selected1);
			}
		});

		initialScreen.add(goToDB);
		initialScreen.add(goToCB);
		initialScreen.add(goToCDB);
		initialScreen.add(logOut);
		
		initialScreen.add(deck1);
		initialScreen.add(deck1listScroller);
		initialScreen.add(deck2);
		initialScreen.add(deck2listScroller);
		initialScreen.add(playGame);
		return initialScreen;
	}

}
