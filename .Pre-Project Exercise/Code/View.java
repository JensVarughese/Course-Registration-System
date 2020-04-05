//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel app = new JLabel("An application to maintain student records");
	private JButton insert = new JButton("Insert");
	private JButton find = new JButton("Find");
	private JButton browse = new JButton("Browse");
	private JButton create = new JButton("Create Tree from File");
	private JTextArea list = new JTextArea(20,40);
	private JScrollPane bar = new JScrollPane(list);
	
	public View() {
		super("Main Frame");
		list.setEditable(false);
		
		JPanel mainPanel = new JPanel();
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.add(app);
		mainPanel.add(bar);
		mainPanel.add(insert);
		mainPanel.add(find);
		mainPanel.add(browse);
		mainPanel.add(create);
		add(mainPanel);
	}
	
	public void setActionListener(ActionListener listener) {
		insert.addActionListener(listener);
		find.addActionListener(listener);
		browse.addActionListener(listener);
		create.addActionListener(listener);
	}
	
	public JButton getInsert() {
		return insert;
	}
	public JButton getFind() {
		return find;
	}
	public JButton getBrowse() {
		return browse;
	}
	public JButton getCreate() {
		return create;
	}
	public JTextArea getList() {
		return list;
	}
}
