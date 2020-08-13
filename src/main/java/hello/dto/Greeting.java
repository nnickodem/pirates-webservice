package hello.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="greetings")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11, nullable = false, unique = true)
	private Integer id;

	@Column(name = "name", length = 45, nullable = false)
	private String name;

	@Column(name = "greeting", length = 45, nullable = false)
	private String greeting;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(final String greeting) {
		this.greeting = greeting;
	}

}
