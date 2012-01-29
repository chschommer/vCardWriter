package main;
public class Entry {

	private String name, givenName, mail;

	public Entry(String name, String givenName, String mail) {
		this.name = name;
		this.givenName = givenName;
		this.mail = mail;
	}

	public boolean equals(Object o) {
		if (o instanceof Entry) {
			Entry a = (Entry) o;
			if (a.getName().equals(name) && a.getMail().equals(mail)
					&& a.getGivenName().equals(givenName))
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

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
