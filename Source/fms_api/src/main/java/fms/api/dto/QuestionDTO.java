package fms.api.dto;

public class QuestionDTO {
	
	 private int questionID;
	    private int topicID;
	    private String questionContent;
	    private boolean isDeleted;
		public int getQuestionID() {
			return questionID;
		}
		public void setQuestionID(int questionID) {
			this.questionID = questionID;
		}
		public int getTopicID() {
			return topicID;
		}
		public void setTopicID(int topicID) {
			this.topicID = topicID;
		}
		public String getQuestionContent() {
			return questionContent;
		}
		public void setQuestionContent(String questionContent) {
			this.questionContent = questionContent;
		}
		public boolean getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(Boolean boolean1) {
			this.isDeleted = boolean1;
		}
		public QuestionDTO(int questionID, int topicID, String questionContent, Boolean isDeleted) {
			super();
			this.questionID = questionID;
			this.topicID = topicID;
			this.questionContent = questionContent;
			this.isDeleted = isDeleted;
		}
		public QuestionDTO() {
			super();
		}
	    
	    
}
