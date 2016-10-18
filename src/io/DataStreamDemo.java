package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamDemo {

	public static void main(String[] args) {
		Member[] members = new Member[]{
				new Member("Bob", 22),
				new Member("Tom", 21),
				new Member("Jerry", 24)
		};
		
		try {
			//写入
			DataOutputStream out = new DataOutputStream(
					new FileOutputStream("src/io/DataStream.txt"));
			
			for (Member member : members) {
				out.writeUTF(member.getName());
				out.writeInt(member.getAge());
			}
			
			out.flush();
			out.close();
			
			//还原
			DataInputStream in = new DataInputStream(
					new FileInputStream("src/io/DataStream.txt"));
			Member[] restoredMembers = new Member[3];
			for (int i = 0; i < restoredMembers.length; i++) {
				restoredMembers[i] = new Member(in.readUTF(), in.readInt()); 
			}
			in.close();
			
			for (Member member : restoredMembers) {
				System.out.println("name: "+member.getName()+", age: "+member.getAge());
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

class Member{
	private String name;
	private Integer age;
	public Member(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
