package it.polito.tdp.itunes.model;

public class ArchiGrafo implements Comparable <ArchiGrafo> {
	
	
	Vertici vertice;
	int bilancio;
	public ArchiGrafo(Vertici vertice, int bilancio) {
		super();
		this.vertice = vertice;
		this.bilancio = bilancio;
	}
	public Vertici getVertice() {
		return vertice;
	}
	public void setVertice(Vertici vertice) {
		this.vertice = vertice;
	}
	public int getBilancio() {
		return bilancio;
	}
	public void setBilancio(int bilancio) {
		this.bilancio = bilancio;
	}
	@Override
	public int compareTo(ArchiGrafo altro) {
		// TODO Auto-generated method stub
		return altro.bilancio - this.bilancio;
	}
	
	

}
