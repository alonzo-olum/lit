import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

class Users {
        private List<User> users;

	static class User {
	    private long userID;
	    private LocalDate activatedOn;
	    private LocalDate deactivatedOn;
	    public User(long userID,
                    LocalDate activeOn,
		    LocalDate deactiveOn
		    ) {
		        this.userID = userID;
			this.activatedOn = activeOn;
			this.deactivatedOn = deactiveOn;
		    }
	    LocalDate getActivatedOn() { return activatedOn; }
	    LocalDate getDeactivatedOn() { return deactivatedOn; }
	}

	public Users(ArrayList<User> users) { this.users = users; }
	public List<User> getUsers() { return users; }
    }
