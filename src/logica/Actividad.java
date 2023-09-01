package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataType.DataActividad;
import dataType.DataSalida;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Persistence;

@Entity
public class Actividad {
	
	@Id
	private String nombre;
	private Integer costo;
	private LocalDate fechaHasta;
	private String prove;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Salida> salidas = new ArrayList<>();
	
	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}

	public Actividad() {
		super();
	}
	
	public Actividad(String nombre, Integer costo, LocalDate fechaHasta,String prov ) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.fechaHasta = fechaHasta;
		this.prove=prov;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public DataActividad getDataActividad() {
		return new DataActividad(this.getNombre(),this.getCosto(),this.getFechaHasta(),this.getProve());
	}
	
	// Gestion de la coleccion de salidas
	public void agregarSalida(DataSalida sali) {
		Salida i = new Salida(sali.getNombre(),sali.getFecha(),sali.getHora(),sali.getTuristaMax(),sali.getLugar(),sali.getFechaAlta());
		salidas.add(i);
	}
	
	public void agregarSalidaPersistencia(Actividad a) {
		//Salida i = new Salida(sali.getNombre(),sali.getFecha(),sali.getHora(),sali.getTuristaMax(),sali.getLugar(),sali.getFechaAlta());
		 
		// ENTITY PARA PERSISTENCIA
	  	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
	  	EntityManager em = emf.createEntityManager();

	  	EntityTransaction tx = em.getTransaction();
	  	
	  	tx.begin();
		
			
	     // ES MERGE PORQUE ESTOY EDITANDO LA ACTIVIDAD AGREGANDO PERSISTENCIA
	    em.merge(a);
	    System.out.println("Persisti usuario");
	    tx.commit();
			
		em.close();
		emf.close();
		
		//salidas.add(i);
	}
	public void borrarSalida(String nombre){
		int i = 0;
		Salida sali;
		boolean encontre=false;
		while ((i < salidas.size()) && (!encontre)) {
		    sali=salidas.get(i);                      // sali tiene el objeto de la poscion i
		    if (sali.getNombre().equals(nombre)) {
		    	salidas.remove(i);
		    	encontre=true;
		    }	
		    i++;
		}
	}	
	// Devuelve un arreglo con los datos de todas las salidas
	public ArrayList<DataSalida> obtenerSalidas(){
		ArrayList<DataSalida> lista = new ArrayList<>();
		for(Salida i:salidas) {
			lista.add(i.getDataSalida());
		}
		return lista;
	}
	
	// Traer el objeto Salida, buscando por su nombre y dentro de la actividad
	
	public Salida obtenerSalida(String nomSalida) {
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
       	 EntityManager em = emf.createEntityManager();
	     EntityTransaction tx = em.getTransaction();
	    	
	     tx.begin();                   
	    
	     Salida s = em.find(logica.Salida.class,nomSalida);              
         
	     tx.commit();
	 		
	 	 em.close();
	 	 emf.close();
		
		
		return s;
	
}
}
