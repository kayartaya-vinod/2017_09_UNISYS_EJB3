package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//@Stateful
@Singleton
@Startup
public class UserServiceBean implements UserService {

	@PostConstruct
	public void afterConstruction() {
		System.out.println(">>> inside the UserServiceBean@PostConstruct method");
	}

	@PreDestroy
	public void beforeDestruction() {
		System.out.println(">>> inside the UserServiceBean@PreDestroy method");
	}

	@PrePassivate
	public void beforePassivate() {
		System.out.println(">>> inside the UserServiceBean@PrePassivate method");
	}

	@PostActivate
	public void afterActivate() {
		System.out.println(">>> inside the UserServiceBean@PostActivate method");
	}

	// state (conversational state)
	private String username;

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

}
