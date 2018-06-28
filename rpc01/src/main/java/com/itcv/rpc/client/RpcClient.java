package com.itcv.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import com.itcv.rpc.client.proxy.ProxyHandler;
import com.itcv.rpc.service.HelloService;

public class RpcClient {
	
	  public static void main(String[] args) {
		  RpcClient r = new RpcClient();
		  InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8090);
		  HelloService service = r.getRemoteProxyObj(HelloService.class,addr);
		  System.out.println(service.sayHello("jack"));
		 /* Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 8090);
                System.out.println("please input...");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());  
                out.writeUTF("hello");  
                out.flush(); 
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());  
                System.out.println(in.readUTF());  
             
               // ProxyHandler h = new ProxyHandler(socket);
               // Proxy.newProxyInstance(RpcClient.class.getClassLoader(), new Class<?>[]{HelloService.class}, h);
            } catch (Exception e) {
                e.printStackTrace();
                 
            }*/
	       
	    }
	  
	 @SuppressWarnings("unchecked")
	public <T> T getRemoteProxyObj(final Class<?> interfaceService,final InetSocketAddress addr) {
		  return (T) Proxy.newProxyInstance(interfaceService.getClassLoader(), new Class<?>[]{interfaceService}, new InvocationHandler() {  
            
          public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {  
              Socket socket = new Socket();
              socket.connect(addr);
              System.out.println("Ö´ÐÐÁËÂð£¿");
              ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());  
             // System.out.println(HelloService.class.getName());
              out.writeUTF(interfaceService.getName());
              out.writeUTF(arg1.getName());  
              out.writeObject(arg1.getParameterTypes());  
              out.writeObject(arg2);  
              out.flush();
             // out.writeObject(arg1.getParameterTypes());  
             // out.writeObject(arg2);  
              ObjectInputStream in = new ObjectInputStream(socket.getInputStream());  
              return in.readObject();  
          }  
      }); 
	  }

}
