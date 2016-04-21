package com.ashish.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDao {
	public List<User> getAllUsers() {
		List<User> userList = null;
		try {
			File file = new File("Users.dat");
			if (!file.exists()) {
				User user = new User(1, "Mahesh", "Teacher");
				userList = new ArrayList<User>();
				userList.add(user);
				saveUserList(userList);
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = getUserListExtracted(ois);
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<User> addUser(String role, String user) {
		List<User> userList = null;
		try {
			File file = new File("Users.dat");
			if (!file.exists()) {
				User userObj = new User(1, user, role);
				userList = new ArrayList<User>();
				userList.add(userObj);
				saveUserList(userList);
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = getUserListExtracted(ois);
				User userObj = new User(userList.size() + 1, user, role);
				userList.add(userObj);
				ois.close();
				saveUserList(userList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<User> removeUser(String role, String user) {
		List<User> userList = null;
		try {
			File file = new File("Users.dat");
			if (!file.exists()) {
				userList = new ArrayList<User>();
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = getUserListExtracted(ois);
				for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
					User user2 = iterator.next();
					if (user2.getName().equals(user) && user2.getProfession().equals(role)) {
						iterator.remove();
						break;
					}
				}
				ois.close();
				saveUserList(userList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@SuppressWarnings("unchecked")
	private List<User> getUserListExtracted(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		return (List<User>) ois.readObject();
	}

	private void saveUserList(List<User> userList) {
		try {
			File file = new File("Users.dat");
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}