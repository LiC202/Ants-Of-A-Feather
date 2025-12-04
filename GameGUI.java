import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameGUI {
	private final Game game;
	private JTextArea output;

	public GameGUI(Game game) {
		this.game = game;
		initUI();
	}

	private void initUI() {
		JFrame frame = new JFrame("Game window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		output = new JTextArea();
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setMargin(new Insets(5, 5, 5, 5));
		output.setFont(new Font("Monospaced", Font.BOLD, 16));
		frame.add(new JScrollPane(output), BorderLayout.CENTER);

		JPanel controls = new JPanel(new GridLayout(2, 5, 5, 5));
		JPanel saveLoad = new JPanel(new GridLayout(1, 2, 5, 5));

		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton east  = new JButton("East");
		JButton west  = new JButton("West");
		JButton up    = new JButton("Up");
		JButton down  = new JButton("Down");
		JButton pickup = new JButton("Pick Up");
		JButton drop = new JButton("Drop");
		JButton use = new JButton("Use");
		JButton inventory = new JButton("Inventory");

		JButton save = new JButton("Save");
		JButton load = new JButton("Load");

		JButton[] buttons = {north, south, east, west, up, down, pickup, drop, use, inventory, save, load};
		for (JButton button : buttons) {
			button.setFont(new Font("Monospaced", Font.BOLD, 20));
			button.setPreferredSize(new Dimension(100, 40));
			button.setMargin(new Insets(10, 10, 10, 10));
		}

		controls.add(down);
		controls.add(north);
		controls.add(up);
		controls.add(pickup);
		controls.add(use);
		controls.add(west);
		controls.add(south);
		controls.add(east);
		controls.add(drop);
		controls.add(inventory);

		saveLoad.add(save);
		saveLoad.add(load);

		frame.add(controls, BorderLayout.SOUTH);
		frame.add(saveLoad, BorderLayout.NORTH);

		north.addActionListener(e -> sendCommand("go north"));
		south.addActionListener(e -> sendCommand("go south"));
		east.addActionListener(e -> sendCommand("go east"));
		west.addActionListener(e -> sendCommand("go west"));
		up.addActionListener(e -> sendCommand("go up"));
		down.addActionListener(e -> sendCommand("go down"));
		inventory.addActionListener(e -> sendCommand("inventory"));
		pickup.addActionListener(e -> {
			Player player = game.getPlayer();
			Room room = player.getCurrentRoom();

			if (room.isEmpty()) {
				appendText("There are no items to pick up.");
			} else {
				String selectedItemName = selectItemDialogue(room.getItems(), "Pickup:");
				if (selectedItemName != null) {
					sendCommand("pickup " + selectedItemName);
				}
			}
		});
		drop.addActionListener(e -> {
			Player player = game.getPlayer();

			if (game.getPlayer().inventoryEmpty()) {
				appendText("You have no items to drop.");
			} else {
				String selectedItemName = selectItemDialogue(player.getInventory(), "Drop:");
				if (selectedItemName != null) {
					sendCommand("drop " + selectedItemName);
				}
			}
		});
		use.addActionListener(e -> {
			Player player = game.getPlayer();

			if (game.getPlayer().inventoryEmpty()) {
				appendText("You have no items to use.");
			} else {
				String selectedItemName = selectItemDialogue(player.getInventory(), "Use:");
				if (selectedItemName != null) {
					sendCommand("use " + selectedItemName);
				}
			}
		});

		save.addActionListener(e -> sendCommand("save"));
		load.addActionListener(e -> sendCommand("load"));

		frame.setVisible(true);
	}

	private String selectItemDialogue(ArrayList<Item> items, String title) {
		JList<Item> itemList = new JList<>(items.toArray(new Item[0]));
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		itemList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Item item) {
					setText(item.getName());
				}
				return this;
			}
		});

		JScrollPane scrollPane = new JScrollPane(itemList);

		int option = JOptionPane.showConfirmDialog(null, scrollPane, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			return itemList.getSelectedValue().getName();
		} else {
			return null;
		}
	}

	private void sendCommand(String command) {
		Scanner tokeniser = new Scanner(command);
		Command c;

		String word1 = tokeniser.next();
		if (tokeniser.hasNext()) {
			String word2 = tokeniser.next();
			c = new Command(word1, word2);
		} else {
			c = new Command(word1, null);
		}

		game.processCommand(c);

		tokeniser.close();
	}

	public void appendText(String text) {
		output.append(text + "\n");
		output.setCaretPosition(output.getDocument().getLength());
	}
}
