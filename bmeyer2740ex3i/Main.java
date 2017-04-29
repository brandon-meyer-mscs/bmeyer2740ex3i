package bmeyer2740ex3i;

import javax.swing.DefaultListModel;

public class Main {

	public static void main(String[] args) {
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		DriverExam exam = mapper.getDriverExam();
		
		DefaultListModel responsesListModel = new DefaultListModel();
		responsesListModel.addElement("B");
		responsesListModel.addElement("D");
		responsesListModel.addElement("A");
		responsesListModel.addElement("A");
		responsesListModel.addElement("C");
		responsesListModel.addElement("A");
		responsesListModel.addElement("B");
		responsesListModel.addElement("A");
		responsesListModel.addElement("C");
		responsesListModel.addElement("D");
		exam.setResponses(responsesListModel);
		
		
System.out.println("First invalid response: " + exam.validate());
		
		System.out.println("Correct: " + exam.totalCorrect());
		System.out.println("Incorrect: " + exam.totalIncorrect());
		
		if (exam.passed())
			System.out.println("Passed test");
		else
			System.out.println("Try again");
		
		System.out.print("Questions missed: ");
		int [] missed = exam.questionsMissed();
		int i = 0;
		while (i < missed.length && missed[i] > 0) {
			System.out.print(" " + missed[i]);
			i++;
		}
		
		return ;
//		char[] answers = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
//		DriverExam exam = new DriverExam(answers);
//		return;
	}

}
