package bmeyer2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JList responsesList;
	private JLabel resultLabel;
	private JLabel questNumLabel;
	private JTextField inputAnswerTextField;
	private JButton prevButton;
	private JButton nextButton;
	private DefaultListModel responsesListModel;
	private DriverExam exam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
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
	public ExamForm() {
		setTitle("Ex3I Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 216, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses: ");
		lblResponses.setBounds(10, 11, 89, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result: ");
		lblResult.setBounds(97, 11, 56, 14);
		contentPane.add(lblResult);
		
		JList list = new JList();
		list.setBackground(UIManager.getColor("Button.background"));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setEnabled(false);
		list.setBounds(10, 39, 29, 168);
		contentPane.add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_responsesList_valueChanged(arg0);
			}
		});
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBounds(40, 36, 29, 168);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBounds(97, 36, 79, 22);
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(resultLabel);
		
		JButton calcPassButton = new JButton("Pass");
		calcPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcPassButton_actionPerformed(e);
			}
		});
		calcPassButton.setBounds(95, 69, 103, 23);
		contentPane.add(calcPassButton);
		
		JButton calcCorrectButton = new JButton("Correct");
		calcCorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcCorrectButton_actionPerformed(e);
			}
		});
		calcCorrectButton.setBounds(97, 103, 101, 23);
		contentPane.add(calcCorrectButton);
		
		JButton calcIncorrectButton = new JButton("Incorrect");
		calcIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcIncorrectButton_actionPerformed(e);
			}
		});
		calcIncorrectButton.setBounds(97, 137, 101, 23);
		contentPane.add(calcIncorrectButton);
		
		JButton btnListIncorrect = new JButton("List Incorrect");
		btnListIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnListIncorrect_actionPerformed(e);
			}
		});
		btnListIncorrect.setBounds(97, 171, 101, 23);
		contentPane.add(btnListIncorrect);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(10, 222, 23, 14);
		contentPane.add(questNumLabel);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.setBounds(40, 218, 29, 20);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
		
		prevButton = new JButton("Prev");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_prevButton_actionPerformed(e);
			}
		});
		prevButton.setEnabled(false);
		prevButton.setBounds(79, 205, 68, 23);
		contentPane.add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_nextButton_actionPerformed(e);
			}
		});
		nextButton.setBounds(79, 239, 68, 23);
		contentPane.add(nextButton);
		
		
	}
	protected void do_calcPassButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		}
		else {
			if (exam.passed()) 
				this.resultLabel.setText("You passed");
			else 
				resultLabel.setText("You failed");
		}
	}
	protected void do_calcCorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} else {
			String str = String.format("%.0f", exam.totalCorrect());
			resultLabel.setText("Total correct: " + str);
		}	
	}
	protected void do_calcIncorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} else {
			resultLabel.setText("Total incorrect: " + Integer.toString(exam.totalIncorrect()));
		}	
	}
	protected void do_btnListIncorrect_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} else {
			String wrong = new String();
			int [] missed = exam.questionsMissed();
			int i = 0;
			while (i < missed.length && missed[i] > 0) {
				int a = missed[i];
				i++;
				wrong += (Integer.toString(a) + ", ");
			}
			resultLabel.setText("Incorrect: #" + wrong);
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == responsesList) {
			do_responsesList_valueChanged(e);
		}
		}
	protected void do_prevButton_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        nextButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == 0) 
            prevButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_nextButton_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
        
        prevButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            nextButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent arg0) {
		   questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
	        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

	        prevButton.setEnabled(true);
	        nextButton.setEnabled(true);
	        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
	            nextButton.setEnabled(false);
	        if (responsesList.getSelectedIndex() == 0) 
	            prevButton.setEnabled(false);
	        inputAnswerTextField.requestFocus();   
	}
	
	protected void do_inputAnswerTextField_focusGained(FocusEvent e) {
		inputAnswerTextField.selectAll();
	}
}
