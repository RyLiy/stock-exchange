import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class final_prototype {

	static boolean but1 = true;
	static boolean but2 = false;
	static boolean but3 = false;
	static boolean but4 = false;
	static boolean but5 = false;
	static boolean but6 = false;

	// Pastycheck reset boolean
	static boolean reset;

	static String[][] stocks; // Name, ticker, price
	static Object[][] tradehistory;

	public static void addComponentsToPane(Container pane, JFrame frame) {
		// remove all, update the pane
		pane.removeAll();
		pane.repaint();

		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// natural height, maximum width
		c.weightx = 0.5;

		JPanel panel1 = new JPanel();
		// panel1.setBackground(Color.green);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // make this component Tall
		// c.weightx = 0.0;
		c.gridwidth = 3; // take up 3 grid cells horizontally
		c.gridx = 0;
		c.gridy = 0;
		pane.add(panel1, c);

		// PANEL 1------------------------------------->
		JTextArea info = new JTextArea();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints in = new GridBagConstraints();
		in.fill = GridBagConstraints.BOTH;
		in.weighty = 1;
		in.gridx = 0;
		in.gridy = 0;

		// in.fill = GridBagConstraints.BOTH;
		info.setFont(new Font("Calibri", Font.PLAIN, 22));
		info.setEditable(false);
		info.setOpaque(false);
		info.setBackground(new Color(0, 0, 0, 0));

		info.setText("Account: John Mann \nNet Account balance: $560 700              Cash Balance $8600");
		panel1.add(info, in);

		// Next three panels
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 470; // make this component Tall - 593
		// c.weightx = 0.0;
		c.gridwidth = 1; // take up 3 grid cells horizontally

		Container panel2 = new JPanel();
		c.fill = GridBagConstraints.WEST;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0; // make this component Wide
		pane.add(panel2, c);
		// Panel2 = BLUE
		// panel2.setBackground(Color.blue);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		// 75% of the container will be the chart
		List<Integer> scores = new ArrayList<Integer>();
		Random random = new Random();
		int maxDataPoints = 16;
		int maxScore = 20;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.add(random.nextInt(maxScore));
		}

		// Adding Label
		String stockname = "";
		if (but1) {
			stockname = String.format("%-35s", stocks[0][0] + " (" + stocks[0][1] + ") - " + stocks[0][2]);
		} else if (but2) {
			stockname = stocks[1][0] + " (" + stocks[1][1] + ") - " + stocks[1][2];
			stockname = String.format("%-35s", stockname);
		} else if (but3) {
			stockname = stocks[2][0] + " (" + stocks[2][1] + ") - " + stocks[2][2];
			stockname = String.format("%-35s", stockname);
		} else if (but4) {
			stockname = stocks[3][0] + " (" + stocks[3][1] + ") - " + stocks[3][2];
			stockname = String.format("%-35s", stockname);
		} else if (but5) {
			stockname = stocks[4][0] + " (" + stocks[4][1] + ") - " + stocks[4][2];
			stockname = String.format("%-35s", stockname);
		} else if (but6) {
			stockname = stocks[5][0] + " (" + stocks[5][1] + ") - " + stocks[5][2];
			stockname = String.format("%-35s", stockname);
		}

		JLabel AssetName = new JLabel(stockname + "          ");
		AssetName.setFont(new Font("Calibri", Font.PLAIN, 30));
		AssetName.setAlignmentX(Component.LEFT_ALIGNMENT);
		AssetName.setMaximumSize(new Dimension(600, 500));
		panel2.add(AssetName);
		// Adding graph
		DrawGraph graph = new DrawGraph(scores);
		// graph.setMaximumSize(new Dimension(10, 10));
		panel2.add(graph);

		// Adding bottom panel
		Container boxlayout0 = new Container();
		boxlayout0.setLayout(new GridBagLayout());
		GridBagConstraints b = new GridBagConstraints();
		b.weightx = 0.5;
		b.gridx = 0;
		b.gridy = 0;
		b.fill = GridBagConstraints.HORIZONTAL;

		panel2.add(boxlayout0);
		// Adding button menus
		JPanel buy_menu = new JPanel();
		buy_menu.setLayout(new BoxLayout(buy_menu, BoxLayout.Y_AXIS));
		buy_menu.setBorder(BorderFactory.createLineBorder(Color.black));
		boxlayout0.add(buy_menu, b); // adding buy menu to the left side of the bottom panel

		String[] options = { "Market", "Limit", "Stop-Market" };
		JComboBox choices = new JComboBox(options);
		choices.setSelectedIndex(0);
		choices.setPreferredSize(new Dimension(30, 40));
		choices.setFont(new Font("Calibri", Font.PLAIN, 19));
		choices.setEditable(false);
		buy_menu.add(choices);

		JTextField pricetext = new JTextField("Price: Market");
		pricetext.setFont(new Font("Calibri", Font.PLAIN, 16));
		// pricetext.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricetext.setEditable(false);
		buy_menu.add(pricetext);

		JTextField amount_to_enter = new JTextField();
		amount_to_enter.setFont(new Font("Calibri", Font.PLAIN, 16));
		amount_to_enter.setText("Amount ($): Type to enter ...");
		buy_menu.add(amount_to_enter);

		JSlider slider = new JSlider(0, 3300, 0);
		slider.setMajorTickSpacing(700);
		slider.setMinorTickSpacing(100);
		slider.setPaintTicks(true);
		// slider.setPaintLabels(true);
		buy_menu.add(slider);

		Container last_sub = new Container();
		GridLayout experimentLayout = new GridLayout(0, 2);
		experimentLayout.setHgap(100);

		last_sub.setLayout(experimentLayout);
		last_sub.setBackground(Color.yellow);
		buy_menu.add(last_sub);
		// buy button
		JButton buy = new JButton();
		buy.setText("BUY");
		buy.setFont(new Font("Calibri", Font.BOLD, 20));
		buy.setPreferredSize(new Dimension(400, 30));
		buy.setBackground(Color.green);
		last_sub.add(buy);
		buy.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				JOptionPane confirm = new JOptionPane();
				int result = confirm.showConfirmDialog(null, "Place order to buy?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// String date, String time, String amount, String type
					arrayAdder("11/24/2021", "11:40PM", "12 x $3", "Bought");
					addComponentsToPane(pane, frame);
				}
			}
		});

		// qty field
		JTextField qty = new JTextField(" Qty: XXX");
		qty.setFont(new Font("Calibri", Font.PLAIN, 16));
		qty.setBorder(BorderFactory.createLineBorder(Color.black));
		qty.setPreferredSize(new Dimension(100, 30));
		last_sub.add(qty);
		// ----------------------------------------------------
		b.gridx = 1;
		b.gridy = 0;
		b.insets = new Insets(0, 20, 0, 0);
		// left side--------------------------------------------------///
		JPanel sell_menu = new JPanel();
		sell_menu.setLayout(new BoxLayout(sell_menu, BoxLayout.Y_AXIS));
		sell_menu.setBorder(BorderFactory.createLineBorder(Color.black));
		boxlayout0.add(sell_menu, b); // adding buy menu to the left side of the bottom panel

		String[] options_sell = { "Market", "Limit", "Stop-Market" };
		JComboBox choices_sell = new JComboBox(options_sell);
		choices_sell.setSelectedIndex(0);
		choices_sell.setPreferredSize(new Dimension(30, 40));
		choices_sell.setFont(new Font("Calibri", Font.PLAIN, 19));
		choices_sell.setEditable(false);
		sell_menu.add(choices_sell);

		JTextField pricetext_sell = new JTextField("Price: Market");
		pricetext_sell.setFont(new Font("Calibri", Font.PLAIN, 16));
		// pricetext.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricetext_sell.setEditable(false);
		sell_menu.add(pricetext_sell);

		JTextField amount_to_enter_sell = new JTextField();
		amount_to_enter_sell.setFont(new Font("Calibri", Font.PLAIN, 16));
		amount_to_enter_sell.setText("Amount ($): Type to enter ...");
		sell_menu.add(amount_to_enter_sell);

		JSlider slider_sell = new JSlider(0, 3300, 0);
		slider_sell.setMajorTickSpacing(700);
		slider_sell.setMinorTickSpacing(100);
		slider_sell.setPaintTicks(true);
		// slider.setPaintLabels(true);
		sell_menu.add(slider_sell);

		Container last_sub_sell = new Container();
		GridLayout experimentLayout_sell = new GridLayout(0, 2);
		experimentLayout_sell.setHgap(100);

		last_sub_sell.setLayout(experimentLayout_sell);

		sell_menu.add(last_sub_sell);
		// sell button
		JButton sell = new JButton();
		sell.setText("SELL");
		sell.setFont(new Font("Calibri", Font.BOLD, 20));
		sell.setPreferredSize(new Dimension(400, 30));
		sell.setBackground(Color.red);
		last_sub_sell.add(sell);

		sell.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				JOptionPane confirm = new JOptionPane();
				int result = confirm.showConfirmDialog(null, "Place order to sell?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// String date, String time, String amount, String type
					arrayAdder("11/24/2021", "11:40PM", "12 x $3", "Sold");
					addComponentsToPane(pane, frame);
				}
			}
		});

		// qty field
		JTextField qty_sell = new JTextField(" Qty: XXX");
		qty_sell.setFont(new Font("Calibri", Font.PLAIN, 16));
		qty_sell.setBorder(BorderFactory.createLineBorder(Color.black));
		qty_sell.setPreferredSize(new Dimension(100, 30));
		last_sub_sell.add(qty_sell);

		/// ---PANEL 3------------\\\\\\\\\\\\\\\\
		c.ipadx = 0;
		Container panel3 = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(panel3, c);
		// panel3.setBackground(Color.red);

		panel3.setLayout(new GridBagLayout());
		GridBagConstraints gb3 = new GridBagConstraints();
		gb3.fill = GridBagConstraints.HORIZONTAL;
		// gb3.weightx = 0.5;
		gb3.gridx = 0;
		gb3.gridy = 0;
		gb3.anchor = GridBagConstraints.PAGE_START;
		JTextArea stats = new JTextArea();
		stats.setFont(new Font("Calibri", Font.PLAIN, 19));
		stats.setEditable(false);
		stats.setText("Available cash:    $ 8600\r\n" + "\r\n" + "Stock A balance: $ 34 899.90\r\n" + "Stock A shares acquired: 343        \r\n" + "Change in past 24hrs: +34%");

		//stats.setBorder(BorderFactory.createLineBorder(Color.black));
		stats.setOpaque(false);
		stats.setBackground(new Color(0, 0, 0, 0));
		panel3.add(stats, gb3);

		gb3.gridx = 0;
		gb3.gridy = 1;
		gb3.weighty = 1;
		gb3.fill = GridBagConstraints.BOTH;
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Calibri", Font.PLAIN, 19));
		JComponent tab1 = makeHistoryTablePanel("History");
		tabbedPane.addTab("History", tab1);
		JComponent tab2 = makeActiveTablePanel("Active Trades");
		tabbedPane.addTab("Active Trades", tab2);
		panel3.add(tabbedPane, gb3);

		/// ---PANEL 4------------\\\\\\\\\\\\\\\\
		c.ipadx = 0;
		Container panel4 = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 100;
		// pane.add(panel4, c);
		// panel4.setBackground(Color.yellow);

		panel4.setLayout(new GridBagLayout());
		GridBagConstraints g4 = new GridBagConstraints();
		g4.fill = GridBagConstraints.BOTH;
		g4.weightx = 0.5;
		g4.weighty = 0.5;
		g4.gridx = 0;
		g4.gridy = 0;
		g4.anchor = GridBagConstraints.NORTH;

		JTextField search = new JTextField();
		search.setText("Type to search for stock...");
		panel4.add(search, g4);

		JScrollPane sp = new JScrollPane(panel4);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.add(sp, c);

		JButton b1 = new JButton("Stock A");
		b1.setText(
				"<html>TSC Corporation<br/>(TICKER: Stock A)<br/><br/>Price: $386.06<br/>24hr Change: <font color='green'>+34%</font></html>");
		b1.setHorizontalAlignment(SwingConstants.LEFT);
		b1.setPreferredSize(new Dimension(80, 30));


		g4.weighty = 1;
		g4.gridy = 1;
		panel4.add(b1, g4);

		JButton b2 = new JButton("Stock B");
		b2.setText(
				"<html>ABC Corporation<br/>(TICKER: Stock B)<br/><br/>Price: $38.06<br/>24hr Change: <font color='green'>+54%</font></html>");
		b2.setHorizontalAlignment(SwingConstants.LEFT);
		b2.setPreferredSize(new Dimension(80, 30));

		g4.gridy = 2;
		panel4.add(b2, g4);

		JButton b3 = new JButton("Stock D");
		b3.setText(
				"<html>YZW Corporation<br/>(TICKER: Stock C)<br/><br/>Price: $30.06<br/>24hr Change: <font color='red'>-34%</font></html>");
		b3.setHorizontalAlignment(SwingConstants.LEFT);
		b3.setPreferredSize(new Dimension(80, 30));

		g4.gridy = 3;
		panel4.add(b3, g4);

		JButton b4 = new JButton("Stock E");
		b4.setText(
				"<html>ACo Corporation.<br/>(TICKER: Stock D)<br/><br/>Price: $0.06<br/>24hr Change: <font color='green'>+7%</font></html>");
		b4.setHorizontalAlignment(SwingConstants.LEFT);
		b4.setPreferredSize(new Dimension(80, 30));
		g4.gridy = 4;
		panel4.add(b4, g4);

		JButton b5 = new JButton("Stock F");
		b5.setText(
				"<html>Cro Corporation<br/>(TICKER: Stock E)<br/><br/>Price: $50.09<br/>24hr Change: <font color='red'>-3%</font></html>");
		b5.setHorizontalAlignment(SwingConstants.LEFT);
		b5.setPreferredSize(new Dimension(80, 30));

		g4.gridy = 5;
		panel4.add(b5, g4);

		JButton b6 = new JButton("Stock G");
		b6.setText(
				"<html>Ray Corporation.<br/>(TICKER: Stock F)<br/><br/>Price: $8.06<br/>24hr Change: <font color='red'>-34%</font></html>");
		b6.setHorizontalAlignment(SwingConstants.LEFT);
		b6.setPreferredSize(new Dimension(80, 30));

		g4.gridy = 6;
		panel4.add(b6, g4);

		search.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				search.setText("");
			}

			public void focusLost(FocusEvent e) {
				search.setText("Type to search for stock...");
			}

		});

		
		//BUTTONS-------->>>>>>>>>>>>
		b1.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				but1 = true;
				but2 = false;
				but3 = false;
				but4 = false;
				but5 = false;
				but6 = false;
				pastyCheck(b1, b2, b3, b4, b5, b6);
				addComponentsToPane(pane, frame);
			}
		});
		
		b2.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {

				but2 = true;
				but1 = false;
				but3 = false;
				but4 = false;
				but5 = false;
				but6 = false;
				pastyCheck(b1, b2, b3, b4, b5, b6);
				

				addComponentsToPane(pane, frame);
			}
		});
		
		b3.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				but3 = true;
				but1 = false;
				but2 = false;
				but4 = false;
				but5 = false;
				but6 = false;
				
				pastyCheck(b1, b2, b3, b4, b5, b6);

				addComponentsToPane(pane, frame);
			}
		});
		b4.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				but4 = true;
				but1 = false;
				but2 = false;
				but3 = false;
				but5 = false;
				but6 = false;
				addComponentsToPane(pane, frame);
			}
		});
		b5.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				but5 = true;
				but1 = false;
				but2 = false;
				but3 = false;
				but4 = false;
				but6 = false;
				addComponentsToPane(pane, frame);
			}
		});
		b6.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				but1 = false;
				but2 = false;
				but3 = false;
				but4 = false;
				but5 = false;
				but6 = true;

				addComponentsToPane(pane, frame);
			}
		});
		search.addActionListener(new ActionListener() // This executes when the user presses the button
		{
			public void actionPerformed(ActionEvent e) {
				String result = search.getText();

				if (result.equalsIgnoreCase(stocks[0][1])) {
					but1 = true;
					but2 = false;
					but3 = false;
					but4 = false;
					but5 = false;
					but6 = false;
					pastyCheck(b1, b2, b3, b4, b5, b6);
					addComponentsToPane(pane, frame);
				} else if (result.equalsIgnoreCase(stocks[1][1])) {
					but2 = true;
					but1 = false;
					but3 = false;
					but4 = false;
					but5 = false;
					but6 = false;
					pastyCheck(b1, b2, b3, b4, b5, b6);
					

					addComponentsToPane(pane, frame);
				} else if (result.equalsIgnoreCase(stocks[2][1])) {
					but3 = true;
					but1 = false;
					but2 = false;
					but4 = false;
					but5 = false;
					but6 = false;
					
					pastyCheck(b1, b2, b3, b4, b5, b6);

					addComponentsToPane(pane, frame);
				} else if (result.equalsIgnoreCase(stocks[3][1])) {
					but1 = false;
					but2 = false;
					but3 = false;
					but4 = true;
					but5 = false;
					but6 = false;
					pastyCheck(b1, b2, b3, b4, b5, b6);
					addComponentsToPane(pane, frame);
				} else if (result.equalsIgnoreCase(stocks[4][1])) {
					but1 = false;
					but2 = false;
					but3 = false;
					but4 = false;
					but5 = true;
					but6 = false;
					pastyCheck(b1, b2, b3, b4, b5, b6);
					addComponentsToPane(pane, frame);
				} else if (result.equalsIgnoreCase(stocks[5][1])) {
					but1 = false;
					but2 = false;
					but3 = false;
					but4 = false;
					but5 = false;
					but6 = true;
					pastyCheck(b1, b2, b3, b4, b5, b6);
					addComponentsToPane(pane, frame);
				} else {
				}
				
			}
		});

		// Buy priceinfo, and qty
		amount_to_enter.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				amount_to_enter.setText("");
			}

			public void focusLost(FocusEvent e) {
				amount_to_enter.setText("Amount ($): Type to enter ...");
			}

		});
		qty.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				qty.setText("");
			}

			public void focusLost(FocusEvent e) {
				qty.setText("Qty: XXX");
			}

		});

		// Sell priceinfo, and qty
		amount_to_enter_sell.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				amount_to_enter_sell.setText("");
			}

			public void focusLost(FocusEvent e) {
				amount_to_enter_sell.setText("Amount ($): Type to enter ...");
			}

		});
		qty_sell.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				qty_sell.setText("");
			}

			public void focusLost(FocusEvent e) {
				qty_sell.setText("Qty: XXX");
			}

		});

		//if (reset == true)
			pastyCheck(b1, b2, b3, b4, b5, b6);

		// panel4.add(b7,g4);

//		button = new JButton("5");
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.ipady = 0; // reset to default
//		c.weighty = 1.0; // request any extra vertical space
//		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
//		c.insets = new Insets(10, 0, 0, 0); // top padding
//		c.gridx = 1; // aligned with button 2
//		c.gridwidth = 2; // 2 columns wide
//		c.gridy = 2; // third row
//		pane.add(button, c);
		frame.setVisible(true);
	}

	private static void pastyCheck(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6) {
		if (but1) {
			b1.setEnabled(false);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			b5.setEnabled(true);
			b6.setEnabled(true);
			
			but2 = false;
			but3 = false;
			but4 = false;
			but5 = false;
			but6 = false;
		} else if (but2 == true) {
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(true);
			b4.setEnabled(true);
			b5.setEnabled(true);
			b6.setEnabled(true);
			

			but1 = false;
			but3 = false;
			but4 = false;
			but5 = false;
			but6 = false;
			
		} else if (but3) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(false);
			b4.setEnabled(true);
			b5.setEnabled(true);
			b6.setEnabled(true);
			
			but1 = false;
			but2 = false;
			but4 = false;
			but5 = false;
			but6 = false;
			
		} else if (but4) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(false);
			b5.setEnabled(true);
			b6.setEnabled(true);
			
			but1 = false;
			but2 = false;
			but3 = false;
			but5 = false;
			but6 = false;
		} else if (but5) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			b5.setEnabled(false);
			b6.setEnabled(true);
			
			but1 = false;
			but2 = false;
			but3 = false;
			but4 = false;
			but6 = false;
			
		} else if (but6) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			b5.setEnabled(true);
			b6.setEnabled(false);

			but1 = false;
			but2 = false;
			but3 = false;
			but4 = false;
			but5 = false;
			
		}

	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Stock Exchange");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1280, 780);// width x height

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane(), frame);

		// Display the window.
		// frame.pack();
		frame.setVisible(true);
	}

	private static JComponent makeHistoryTablePanel(String text) {
		JPanel panel = new JPanel();
//	        JLabel filler = new JLabel(text);
//	        filler.setHorizontalAlignment(JLabel.CENTER);
//	        panel.setLayout(new GridLayout(1, 1));
//	        panel.add(filler);
		String[] columnNames = { "Date", "Time", "Amount", "Type" };

		Object[][] data = tradehistory;

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		// panel.add(scrollPane);
		return scrollPane;
	}

	private static JComponent makeActiveTablePanel(String text) {
		JPanel panel = new JPanel();
//	        JLabel filler = new JLabel(text);
//	        filler.setHorizontalAlignment(JLabel.CENTER);
//	        panel.setLayout(new GridLayout(1, 1));
//	        panel.add(filler);
		String[] columnNames = { "Type", "Price", "QTY", "Type" };

		Object[][] data = {{ "Limit", "$202.02", "32", "Buy"},{ "Stop-market", "$100.00", "1", "Buy"},{ "Limit", "$202.02", "32", "Sell"},{ "Limit", "$20.092", "8", "Buy"},{ "Stop-market", "$2.02", "1", "Buy"}};
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		// panel.add(scrollPane);
		return scrollPane;
	}
	
	public static void arrayAdder(String date, String time, String amount, String type) {
		Object[][] data1 = new Object[tradehistory.length + 1][tradehistory[0].length];

		for (int i = 0; i < tradehistory.length; i++) {
			for (int j = 0; j < tradehistory[i].length; j++) {
				data1[i][j] = tradehistory[i][j];
			}
		}

		data1[data1.length - 1][0] = date;
		data1[data1.length - 1][1] = time;
		data1[data1.length - 1][2] = amount;
		data1[data1.length - 1][3] = type;

		tradehistory = data1;
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		String[][] fstocks = { { "TSC Corporation", "Stock A", "$308.06" }, { "ABC Corporation", "Stock B", "$38.06" },
				{ "YZW Corporation", "Stock C", "$33.46" }, { "ACo Corporation", "Stock D", "$3.09" },
				{ "Cro Corporation", "Stock E", "$54.78" }, { "Ray Corporation", "Stock F", "$8.06" } };
		stocks = fstocks;

		Object[][] data = { { "11/1/2021", "2:32 PM", "1 x $13", "Bought" },
				{ "11/1/2021", "2:31 PM", "12 x $10", "Sold" }, { "11/1/2021", "2:30 PM", "12 x $9", "Bought" },
				{ "11/1/2021", "2:23 PM", "12 x $13", "Sold" }, { "11/1/2021", "2:12 PM", "12 x $13", "Sold" },
				{ "11/1/2021", "2:11 PM", "15 x $5", "Bought" }, { "11/1/2021", "2:11 PM", "16 x $14", "Sold" },
				{ "11/1/2021", "2:10 PM", "2 x $13", "Bought" } };

		tradehistory = data;

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
