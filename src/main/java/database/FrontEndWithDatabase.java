package database;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;



public class FrontEndWithDatabase {
	public static void main(String[] args){
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

		JPanel initialScreen = new JPanel();
		initialScreen.setMaximumSize(new Dimension(width, height));
		initialScreen.setLayout(new FlowLayout());

		JPanel deckBuilder = createDeckBuilder(width, height, buttonDimension, initialScreen, Frame, "Card List", "Deck");

		JPanel collectionBuilder = createDeckBuilder(width, height, buttonDimension, initialScreen, Frame, "Card List", "Collection");

		JPanel collectionDeckBuilder = createDeckBuilder(width, height, buttonDimension, initialScreen, Frame, "Collection", "Deck");

		JButton goToDB = new JButton("Go to Deck Builder");
		goToDB.setPreferredSize(bigButtonDimension);
		goToDB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.remove(initialScreen);
				Frame.add(deckBuilder);
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
				Frame.add(collectionBuilder);
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
				Frame.add(collectionDeckBuilder);
				Frame.revalidate();
				Frame.repaint();
			}
		});

		initialScreen.add(goToDB);
		initialScreen.add(goToCB);
		initialScreen.add(goToCDB);

		Frame.add(initialScreen);

		Frame.revalidate();
		Frame.repaint();
	}

	public static JPanel createDeckBuilder(int width, int height, Dimension buttonDimension, JPanel initialScreen, JFrame Frame, String text1, String text2){
		JPanel deckBuilder = new JPanel();
		deckBuilder.setLayout(new FlowLayout());

		int strLength = 1000;
		String[] str = new String[strLength];
		String[] lol = SQLDatabaseConnection.getCollectionCardNames("jim");

		for(int i = 0; i< strLength; i++){
			if(i < lol.length)
				str[i] = lol[i];
			else
				str[i] = "" + i;
		}

		int strLength1 = 1000;
		String[] str1 = new String[strLength1];
		for(int i = 0; i< strLength1; i++){
			str1[i] = "1" + i;
		}

		JList<String> jlist = new JList<String>(str);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL);
		jlist.setVisibleRowCount(-1);

		JList<String> jlist1 = new JList<String>(str1);
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist1.setLayoutOrientation(JList.VERTICAL);
		jlist1.setVisibleRowCount(-1);

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension((int)(width*1.5),(int)(height*.05)));

		JLabel label1 = new JLabel(text1);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setPreferredSize(new Dimension((int)(width*0.5),(int)(height*.04)));

		JLabel label2 = new JLabel(text2);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setPreferredSize(new Dimension((int)(width*0.5),(int)(height*.04)));

		JScrollPane listScroller = new JScrollPane(jlist);
		listScroller.setPreferredSize(new Dimension((int)(width*.5),(int)(height*.8)));
		JScrollPane listScroller1 = new JScrollPane(jlist1);
		listScroller1.setPreferredSize(new Dimension((int)(width*.5),(int)(height*.8)));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension((width),(int)(height*.2)));

		JTextField searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension((int)(width*.15),25));

		String[] dropDownOptions = new String[5];
		dropDownOptions[0] = "Name";
		dropDownOptions[1] = "Mana Cost";
		dropDownOptions[2] = "Color";
		dropDownOptions[3] = "Power";
		dropDownOptions[4] = "Toughness";

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
				String selected = jlist.getSelectedValue();
				System.out.println(selected);
			}
		});

		JButton remove = new JButton("Remove from " + text2);
		remove.setPreferredSize(buttonDimension);
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = jlist1.getSelectedValue();
				System.out.println(selected);
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
			}
		});

		buttonPanel.add(searchBar);
		buttonPanel.add(dropDown);
		buttonPanel.add(search);
		buttonPanel.add(add);
		buttonPanel.add(remove);
		buttonPanel.add(mainMenu);

		labelPanel.add(label1);
		labelPanel.add(label2);

		deckBuilder.add(labelPanel);
		deckBuilder.add(listScroller);
		deckBuilder.add(listScroller1);
		deckBuilder.add(buttonPanel);

		return deckBuilder;
	}

}
