package com.itcv.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.itcv.rpc.server.handler.ServerHandler;
import com.itcv.rpc.service.HelloService;
import com.itcv.rpc.service.impl.HelloServiceImpl;

public class RpcServer {
	
	private int port;
	
	private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();  
	
	public RpcServer(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) {
		RpcServer server = new RpcServer(8090);
		System.out.println("Æô¶¯·þÎñ");
		server.register(HelloService.class, HelloServiceImpl.class);
		server.start(); 
	}
	
	
	public void start() {
		try {
			ServerSocket server = new ServerSocket();
			server.bind(new InetSocketAddress(port));
			while (true) {
				Socket socket = server.accept();
				int i = 0;
				new Thread(new ServerHandler(socket,serviceRegistry), "Thread " + i++).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

    public void register(Class serviceInterface, Class impl) {  
        serviceRegistry.put(serviceInterface.getName(), impl);  
    }  
	

}
