package it.polito.tdp.itunes.model;

public class Vertici implements Comparable<Vertici>{
	
	
	int  id;
	String nome;
	double durata;
	
		
		
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	public Vertici(int id, String nome, double durata) {
		super();
		this.id = id;
		this.nome = nome;
		this.durata = durata;
	}
	
	public String toString() {
		return this.nome;
	}
	
	@Override
	public int compareTo(Vertici altro) {
		// TODO Auto-generated method stub
		return this.nome.compareTo(altro.nome);
	}
	

}
