package com.itcv.rpc.server.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class ServerHandler implements Runnable{

	Socket socket;  
	HashMap<String, Class>  serviceRegistry;
	public ServerHandler(Socket socket,HashMap<String, Class> serviceRegistry) {
		this.socket = socket;
		this.serviceRegistry = serviceRegistry;
	}
	
	 
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			String serviceName = in.readUTF();
			String methodName =  in.readUTF();
			 Class<?>[] parameterTypes = (Class<?>[]) in.readObject();  
             Object[] parameters = (Object[]) in.readObject();  
             Class service = serviceRegistry.get(serviceName);
             if(service ==null) {
            	 throw new ClassNotFoundException(serviceName + " not found");  
             }
             Method method = service.getMethod(methodName, parameterTypes);
            Object result =  method.invoke(service.newInstance(), parameters);
			System.out.println("¿Í»§¶ËËµ£º" +serviceName+"=="+ methodName+"==="+parameterTypes+"=="+parameters+"==================");
			out.writeObject(result);
			out.flush();
		} catch (Exception e) {
			 
			e.printStackTrace();
		} 
		
	}

}
