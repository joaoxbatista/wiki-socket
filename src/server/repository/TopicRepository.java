package server.repository;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.db4o.ObjectSet;

import server.models.*;
import server.database.*;

public class TopicRepository {
	/*
	 * Method: store
	 * Return: Topic 
	 * Description: Salva um topic no banco de dados e retorna o objeto salvo
	 */
	public Topic store(Topic topic)
	{
		try {
			Database.getInstance().store(topic);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar topic no banco de dados. Erro: " + e.getMessage());
			return null;
		}
		Database.close();
		System.out.println("[LOG] - TopicRespository -> Topic salvo com sucesso! \n");
		return topic;
	}
	
	/*
	 * Method: update
	 * Return: Topic 
	 * Description: Atualiza os atributos de um topic
	 */
	public Topic update(Topic topic)
	{
		Topic topicresult = null;
		Topic topicBusca = new Topic();
		topicBusca.setCode(topic.getCode());
		
		try {
			
			ObjectSet resultObject = Database.getInstance().queryByExample(topicBusca);
			topicresult = (Topic) resultObject.next();
			
			topicresult.setText(topic.getText());
			topicresult.setDiscipline(topic.getDiscipline());
			topicresult.setTitle(topic.getTitle());
			
			Database.getInstance().store(topicresult);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar topic no banco de dados. Erro: " + e.getMessage());
		}
		
		Database.close();
		System.out.println("[LOG] - TopicRespository -> Topic atualizado com sucesso! \n");
		return topicresult;
	}
	
	/*
	 * Method: all
	 * Return: ArrayList<Topic> 
	 * Description: Retorna uma lista com os topics cadastrados
	 */
	public ArrayList<Topic> all()
	{
		ArrayList<Topic> listaTopics = new ArrayList<>();
		try {
			ObjectSet resultObject = Database.getInstance().queryByExample(Topic.class);
			
			for (Object topic: resultObject)
			{
				listaTopics.add((Topic)topic);
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "[LOG] - TopicRespository -> Erro ao obter topics do banco de dados. Erro: " + e.getMessage());
		}
		Database.close();
		System.out.println("[LOG] - TopicRespository -> Topics obtidos com sucesso! \n");
		return listaTopics;
	}
	
	/*
	 * Method: find
	 * Return: Topic 
	 * Description: Procura um topic pelo código do topic no banco de dados
	 */
	public Topic find(Topic topic)
	{	
		Topic topicresult = null;
		try
		{
			ObjectSet resultObject = Database.getInstance().queryByExample(topic);
			topicresult = (Topic) resultObject.next();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "[LOG] - TopicRespository -> Erro ao procurar topic do banco de dados. Erro: " + e.getMessage());
		}
		Database.close();
		System.out.println("[LOG] - TopicRespository -> Topic obtido com sucesso! \n");
		return topicresult;
	}
	
	/*
	 * Method: remove
	 * Return: Topic 
	 * Description: Remove um topic do banco de dados
	 */
	public Topic remove(String code)
	{
		Topic topic = new Topic();
		Topic topicresult = null;
		try
		{
			topic.setCode(code);
			ObjectSet resultObejct = Database.getInstance().queryByExample(topic);
			topicresult = (Topic) resultObejct.next();
			
			if(topicresult != null)
			{
				Database.getInstance().delete(topicresult);
				System.out.println("[LOG] - TopicRespository -> Topic removido com sucesso! \n");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "[LOG] - TopicRespository -> Erro ao remover topic do banco de dados. Erro: " + e.getMessage());
		}
		Database.close();
		
		return topicresult;
	}
}
