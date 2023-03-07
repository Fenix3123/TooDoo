package toodoo.toodoo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TooDoo {
	private Long toodooid;
	private String item;
	private User user;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getToodooid() {
		return toodooid;
	}
	public void setToodooid(Long toodooid) {
		this.toodooid = toodooid;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
