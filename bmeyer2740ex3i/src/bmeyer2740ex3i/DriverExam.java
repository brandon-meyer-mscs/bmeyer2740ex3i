package bmeyer2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
		private char[] answers;
		//private char[] responses;
		private char[] responses = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'Z'};
		private final double requiredPct = 0.7;
		
		public DriverExam(char[] answers) {
			this.answers = new char[answers.length];
			for(int i = 0; i < answers.length; i++) {
				this.answers[i] = answers[i];
			}
		}
		
		public DriverExam(DefaultListModel answers) {
			this.answers = new char[answers.getSize()];
			for(int i = 0; i < answers.getSize(); i++) {
				String a = (String) answers.get(i);
				this.answers[i] = a.charAt(0);
			}
		}
		
		public void setResponses(DefaultListModel responses) {
			this.responses = new char[responses.getSize()];
			for (int i = 0; i < responses.getSize(); i++) {
				String r = (String) responses.get(i);
				this.responses[i] = r.charAt(0);
			}
		}
		
		public DefaultListModel getAnswers() {
			DefaultListModel answersListModel = new DefaultListModel();
			
			for (int i = 0; i < this.answers.length; i++) {
				answersListModel.addElement(this.answers[i]);
			}
			
			return answersListModel;
		}
	
		
		public int validate() {
			for(int i = 0; i < answers.length; i++) {
				char[] valid = {'A', 'B', 'C', 'D'};
				int x = 0;
				while (x <= valid.length) {
					if (responses[i] != valid[x]) {
						x++;
						if (x == valid.length) {
							return i;
						}
						else {
							break;
						}
					}
				}
				
			}
			return -1;
		}
		
		public boolean passed() {
			double score = (totalCorrect() / answers.length);
			if (score >= requiredPct) {
				return true;
			}
			else {
				return false;
			}
		}
	
		public int totalCorrect() {
			int correct = 0;
			for(int i = 0; i < answers.length; i++) {
				if(answers[i] == responses[i]) correct++;
			}
			return correct;
		}
		
		public int totalIncorrect() {
			int incorrect = 0;
			for(int i = 0; i < answers.length; i++) {
				if(answers[i] != responses[i]) incorrect++;
			}
			return incorrect;
		}
		
		public int[] questionsMissed() {
			int[] missed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int m = 0;
			for(int i = 0; i < answers.length; i++) {
				if (answers[i] != responses[i]) {
					missed[m] = i + 1;
					m++;
				}
			}
			return missed;
		}
		}

