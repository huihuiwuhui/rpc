package com.itcv.thread;

public class Test implements Runnable{

	public void run() {
		 System.out.println("ʲôʱ��ִ���أ�����");
		
	}
	
	public static void main(String[] args) {
		 Test test = new Test();
		 test.run();
	}

}
