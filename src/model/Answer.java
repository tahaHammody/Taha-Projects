package model;

import java.util.Objects;

public class Answer {

 	private int id;
	private String content;

	private boolean isCorrect;


	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean correct) {
		isCorrect = correct;
	}

	public Answer(String content, boolean isCorrect) {
		this.content = content;
		this.isCorrect = isCorrect;
	}
	public Answer(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	@Override
	public int hashCode() {
		return Objects.hash(content, id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		return Objects.equals(content, other.content) && id == other.id;
	}



	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + "]";
	}
	
	
	
	
	

}
