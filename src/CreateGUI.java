import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * 
 * Create the GUI for app
 * @author Shivam Sahu
 *
 */
public class CreateGUI extends JFrame{
	
	private JFrame frame;
	private JButton submit;
	private JTextField queryField;
	private JLabel message;
	
	CreateGUI () {
		 frame = new JFrame();
		 frame.setSize(500, 200);
		 
		 //Create the panel for adding the components in frame.
		 JPanel panel = new JPanel(new GridBagLayout());
		 GridBagConstraints c;
		 
		 //Create query field
		 queryField= new JTextField(30);
		 c = new GridBagConstraints();
		 c.gridx = 300;
		 c.gridwidth = 200;
		 c.gridy = 50;
		 c.gridheight = 100;
		 
		 //Add query field to panel.
		 panel.add(queryField, c);
		
		//Create submit button
		 submit = new JButton();
		 submit.setSize(10, 10);
		 c = new GridBagConstraints();
		 c.gridx = 450;
		 c.gridwidth = 0;
		 c.gridy = 300;
		 c.gridheight = 50;
		 
		 //Add submit button to panel.
		 panel.add(submit, c);
		 submit.setSize(4, 4);
		 message = new JLabel("Fetching Results. Please Wait !");
		 message.setVisible(false);
		 c = new GridBagConstraints();
		 c.gridx = 300;
		 c.gridwidth = 0;
		 c.gridy = 600;
		 c.gridheight = 0;
		 panel.add(message, c);
		 submit.setText("Submit");
		 submit.setEnabled(false);
		 
		queryField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (queryField.getText().isEmpty()) {
					submit.setEnabled(false);
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!queryField.getText().isEmpty()) {
					submit.setEnabled(true);
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		queryField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!queryField.getText().isEmpty()) {
					submit.setEnabled(true);
				}
			}
		});
		 
		submit.setText("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				queryField.selectAll();
				submit.setEnabled(false);
				queryField.setEditable(false);
				message.setVisible(true);
				FetchResults result = new FetchResults(queryField.getText());
				try {
					Solution s = result.fetchResults();
					showResultGUI(s);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		 
		//add panel to the frame.
		frame.add(panel);
		
		//terminate on closing this window
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Accept the solution as parameter and display result on GUI.
	 * @param solution
	 */
	public void showResultGUI(Solution solution) {
		 JFrame resultWindow = new JFrame();
		 
		 //If result window is closed then again enable the component of query window.
		 resultWindow.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				submit.setEnabled(true);
				queryField.setEnabled(true);
				message.setVisible(false);
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				submit.setEnabled(true);
				queryField.setEnabled(true);
				message.setVisible(false);
			}
		});
		 
		 JPanel p=new JPanel();
		 String[] columnNames ={"A","B"};
		 Object[][] data = {{"Total no of open issues",solution.getFirstAnswer()},
				 			{"Number of open issuesthat were opened in the last 24 hours",solution.getSecondAnswer()},
				 			{"Number of open issues that were opened more than 24 hours ago but less than 7 days ago",solution.getThirdAnswer()},
				 			{"Number of open issues that were opened more than 7 days ago ",solution.getFourthAnswer()}};
		 JTable table = new JTable(data, columnNames);
		 JScrollPane pane = new JScrollPane(table);
		 p.add(pane);
		 resultWindow.add(p);
		 resultWindow.pack();
		 resultWindow.setVisible(true);
		 
	 }
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JButton getSubmit() {
		return submit;
	}
	public void setSubmit(JButton submit) {
		this.submit = submit;
	}
	public JTextField getQueryField() {
		return queryField;
	}
	public void setQueryField(JTextField queryField) {
		this.queryField = queryField;
	}
}
