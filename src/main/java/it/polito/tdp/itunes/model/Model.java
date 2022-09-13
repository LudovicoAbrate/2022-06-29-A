package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private Graph<Vertici,DefaultWeightedEdge> grafo;
	ItunesDAO dao;
	Map<Integer,Vertici> idMap = new HashMap<>();
	List<Vertici> vertici ;
	
	
	public Model() {
		this.dao = new ItunesDAO();
		this.vertici =  new LinkedList<Vertici>();
	
	}
	
	
	public void creaGrafo(Integer numero) {
		
		
		vertici.clear();
		for( Vertici b : dao.getVertici(numero)) {
			
		vertici.add(b);
		idMap.put(b.id, b);
			
	}
		

		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		Graphs.addAllVertices(this.grafo,vertici);
		
		for (Vertici v1: this.grafo.vertexSet()) {
			for(Vertici v2 : this.grafo.vertexSet()) {
				
				if(v1.nome.compareTo(v2.nome)!= 0 && v1.durata - v2.durata < 0) {
					
					int peso = (int) (v2.durata - v1.durata) ;
					Graphs.addEdgeWithVertices(this.grafo, v1, v2, Math.abs(peso) );
					
				}
				if(v1.nome.compareTo(v2.nome)!= 0 && v1.durata - v2.durata > 0) {
					
					int peso = (int) (v2.durata - v1.durata) ;
					Graphs.addEdgeWithVertices(this.grafo, v2, v1, Math.abs(peso) );
					
				}
			}
		}
		
	}
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public List<ArchiGrafo> getSuccessori(Vertici v){
		
		List<ArchiGrafo> atemp = new ArrayList<>();
		
		List<Vertici> vicini = Graphs.successorListOf(this.grafo, v);
		
		for(Vertici v1: vicini) {
			
			double somma = 0.0;
			double archie= 0.0;
			double archiu= 0.0;
			
			
		
		for (DefaultWeightedEdge e1 : this.grafo.outgoingEdgesOf(v1)) {
			
			
			archiu += this.grafo.getEdgeWeight(e1)  ;
		}
		for (DefaultWeightedEdge e2 : this.grafo.incomingEdgesOf(v1)) {
			
	
			archie +=  this.grafo.getEdgeWeight(e2) ;
		}


		somma = archie - archiu;
		
		ArchiGrafo a = new ArchiGrafo(v1,(int) somma);
		
		atemp.add(a);
			
			
		}	
		Collections.sort(atemp);
		
		return atemp;
		/*List<ArchiGrafo> listaBilanci = new ArrayList<ArchiGrafo>();
		
		List<Vertici> vicini = Graphs.successorListOf(this.grafo, v1);
		
		for(Vertici v : vicini) {
			
			int bilancio = 0;
			int pesoentrante = 0;
			int pesouscente = 0;
		
		for(DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(v)) {
			
			pesouscente = (int) (pesouscente + this.grafo.getEdgeWeight(e));
				
		}

		for(DefaultWeightedEdge e : this.grafo.incomingEdgesOf(v)) {
			
			pesoentrante = (int) (pesoentrante + this.grafo.getEdgeWeight(e));
				
		}
		
		bilancio = pesoentrante + pesouscente;
		
		ArchiGrafo a = new ArchiGrafo(v,bilancio);
		listaBilanci.add(a);
		}
		
		Collections.sort(listaBilanci);
		
		return listaBilanci;
		
		
		*/
	}

	public List<Vertici> VerticiGrafo(int canzoni) {
		
		
		List<Vertici> atemp = new ArrayList<>();
		
		
		for (Vertici v :this.grafo.vertexSet()) {
			atemp.add(v);
		}
			
			Collections.sort(atemp);
		
		
		return atemp;
	
	}
	
	
	
}
