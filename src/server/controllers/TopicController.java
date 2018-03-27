package server.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.Object;

import org.json.simple.JSONObject;

import server.models.Topic;
import server.repository.TopicRepository;

import com.sun.xml.internal.ws.util.StringUtils;

public class TopicController {
	
	public void find(String field, String value) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		
		String class_name = "server.models.Topic";
		Class<?> tpClass = Class.forName(class_name);
		Object tc = tpClass.newInstance();

		String method_string = "set" + StringUtils.capitalize(field);
		Method method = tc.getClass().getMethod(method_string, String.class);
		method.invoke(tc, value);
		
		Topic topic = (Topic) tc;
		TopicRepository tr = new TopicRepository();
		
		System.out.println(tr.find(topic));
	}
	
	public void delete(String value)
	{
		TopicRepository tr = new TopicRepository();
		System.out.println(tr.remove(value));
	}
	
	public void all()
	{
		TopicRepository tr = new TopicRepository();
		System.out.println(tr.all());
	}

}
