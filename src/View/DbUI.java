package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DBUnitHelper;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DbUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DbUI frame = new DbUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DbUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("创建数据库");
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, DBUnitHelper.createDB());
			}
		});
		button.setBounds(129, 97, 152, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("创建表");
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DBUnitHelper.createTables();
				JOptionPane.showMessageDialog(null, "执行成功");
			}
		});
		button_1.setBounds(129, 187, 152, 23);
		contentPane.add(button_1);
		this.setLocationRelativeTo(null);
	}
}
