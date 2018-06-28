package com.itcv.rpc.client.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProxyHandler implements InvocationHandler{
	
	private  Socket socket;
	
	
	public ProxyHandler(Socket socket) {
	   this.socket = socket;	
	}
	
	public ProxyHandler() {}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// ���͸�������������
        ObjectOutputStream out = new ObjectOutputStream (socket.getOutputStream());
        out.writeUTF("hello world");
        out.flush();
       // ���շ������ķ�������
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		return null;
	}

}
