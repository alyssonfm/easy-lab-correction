package br.edu.les.easyCorrection.samples.hibernate.dao;
public class Teste { 
	
	public static void main(String[] args) throws Exception { 
			
		try 
		{ 
			String log = "login"; 
			String senha = "abc"; 
			String nome = "Rafael"; 
			String email = "Rafael@email.com.br";

			UsuarioDAO dao = new UsuarioDAO(); 
			Usuario usuario = new Usuario(log,senha,nome,email);		
			dao.UsInserir(usuario);
			System.out.println("Registro inserido com sucesso!!!");
			} 
			catch(Exception e) 
			{    
				System.out.println("Não foi possivel, Erro: " + e.getMessage()); 
			} 
		} 
	}