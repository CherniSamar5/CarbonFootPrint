package application.controllers;

public class User {
	private Long id;
    private String userName;
    private String mail;
    private String pwd;
    
    

    public User(Long id, String userName, String mail, String pwd) {
		super();
		this.id = id;
		this.userName = userName;
		this.mail = mail;
		this.pwd = pwd;
	}

	public User(String userName, String mail, String pwd) {
        this.userName = userName;
        this.mail = mail;
        this.pwd = pwd;
    }

    // Getters and setters
    
    

    public String getUserName() {
        return userName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
