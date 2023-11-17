package quiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quiz")
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use auto-increment column
	@Column(name="quiz_id")
	private int Quiz_id;
	
	@Column(name="quiz_title")
	private String Quiz_title;
	
	@Column(name="category")
	private String Category;

	public int getquiz_id() {

		return Quiz_id;

	}


	public void setquiz_id(int quiz_id) {

		this.Quiz_id = quiz_id;

	}
	public String getquiz_title() {

		return Quiz_title;

	}
	public void setquiz_title(String quiz_title) {

		this.Quiz_title = quiz_title;

	}

	public String  getcategory() {

		return Category;

	}

	public void setcategory(String category) {

		this.Category = category;

	}
}

