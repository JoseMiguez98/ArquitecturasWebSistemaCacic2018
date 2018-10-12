package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import converters.ListToStringConverter;
@Entity
public class Project implements Serializable {
	@Id
	@GeneratedValue
	private int id_project;
	private String name;
	@Convert(converter = ListToStringConverter.class)
	private List<String> topics = new ArrayList<String>();
	private String category;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	private User author;
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}   
	public int getId_project() {
		return this.id_project;
	}

	public void setId_project(int id_project) {
		this.id_project = id_project;
	}   
	public List<String> getTopics() {
		return new ArrayList<String>(this.topics);
	}

	public void addTopic(String topic) {
		this.topics.add(topic);
	}  
	
	public void setTopics(List<String>newTopics) {
		this.topics = newTopics;
	}
	
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		if(category.equals("Article") || category.equals("Summary") || category.equals("Poster")) {
			this.category = category;
		}
		else {
			this.category = "Invalid Category";
		}
	}

	public User getAuthor() {
		return author;
	}

	public boolean setAuthor(User user) {
		if(user.isAuthor()) {
			this.author = user;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean canBeEvaluated(User user) {
		return user.isEvaluator() && user.getRevisionsSize()<3 && !this.author.equals(user) && user.hasSufficientKnowledges(this);
	}
}
