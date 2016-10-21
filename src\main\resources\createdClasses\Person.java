public class Person
{
	private String lastName;
	private String firstName;

	public Person( String firstName, String lastName )
	{
		this.lastName = lastName;
		this.firstName = firstName;

	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;

	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;

	}

}