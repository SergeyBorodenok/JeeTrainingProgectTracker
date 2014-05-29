package by.epamlab.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epamlab.beans.User;









public class UserHandler extends DefaultHandler {
	private static enum UserEnum {
		USERS, USER, EMAIL, PASSWORD, ROLE,FIRSTNAME,LASTNAME;
	}
	private User curUser;
	private String email,password,role,firstName,lastName;
	private UserEnum currentEnum;
	private List<User> users = new ArrayList<User>();
	public List<User> getUsers() {
		return users;
	}
	
	@Override
	public void startDocument() throws SAXException {
		
		super.startDocument();
		
	}
	
	@Override
	public void startElement(String nameSpaceURI, String localName,
			String qName, Attributes attrs) throws SAXException {
		
		currentEnum = UserEnum.valueOf(qName.toUpperCase());
		if (currentEnum == UserEnum.EMAIL) {
			email=null;
			curUser=new User();
			
		}
		if(currentEnum == UserEnum.PASSWORD){
			password=null;
		}
		if(currentEnum == UserEnum.ROLE){
			role=null;
		}
		if(currentEnum == UserEnum.FIRSTNAME){
			firstName=null;
		}
		if(currentEnum == UserEnum.LASTNAME){
			lastName=null;
			
		}
			
		
	}
	
	public void characters(char[] ch, int start, int lenght)
			throws SAXException {
		
		super.characters(ch, start, lenght);
	
		if (currentEnum == UserEnum.EMAIL && email==null) {
			email=new String(ch, start, lenght);
			curUser.setEmail(email);
		}
		if (currentEnum == UserEnum.PASSWORD && password==null) {
			password=new String(ch, start, lenght);
			curUser.setPassword(password);
		}
		if (currentEnum == UserEnum.ROLE && role==null) {
			role=new String(ch, start, lenght);
			curUser.setRole(role);			
		}
		if (currentEnum == UserEnum.FIRSTNAME && firstName==null) {
			firstName=new String(ch, start, lenght);
			curUser.setFirstName(firstName);
		}
		if (currentEnum == UserEnum.LASTNAME && lastName==null) {
			lastName=new String(ch, start, lenght);
			curUser.setLastName(lastName);
			users.add(curUser);
		}

	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		super.endElement(uri, localName, qName);
		if (currentEnum == UserEnum.EMAIL) {
			currentEnum = null;
		}

	}

	public void printResultList() {
		for (User user : users) {
			System.out.println(user);
		}

	}

	public void endDocument() throws SAXException {
		
		super.endDocument();
		
	}
}
