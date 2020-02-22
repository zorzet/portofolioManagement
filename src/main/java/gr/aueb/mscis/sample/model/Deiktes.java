package gr.aueb.mscis.sample.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="deiktes")
public class Deiktes {
		
		 @Id 
		    @Column(name="id")
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private Integer id;
		 
		 // 1 DEIKTES YPOLOGIZONTAI ME VASI * METOXI 
		 
		 @OneToMany(orphanRemoval=true, 
		            cascade = CascadeType.ALL, 
		            mappedBy="deiktes", fetch=FetchType.LAZY)    
		    private Set<Metoxh> metoxi = new HashSet<Metoxh>();
		 
		    @Column(name="ΜΚΟ15", length = 50, nullable = false)
		    private int ΜΚΟ15 ;
		 
		    @Column(name="ΜΚΟ80", length = 50, nullable = false)
		    private int ΜΚΟ80;
		
		   @Column(name="yk20", length = 50, nullable = false)
		    private int yk20;
		 
		   @Column(name="xk20", length = 50, nullable = false)
		    private int xk20;
		 
		   @Column(name="beta", length = 50, nullable = false)
		    private int beta;

		 
		 public Deiktes() { }
		 
		  public Deiktes(int ΜΚΟ15, int ΜΚΟ80, int yk20, int xk20) {
		        this.ΜΚΟ15 = ΜΚΟ15;
		        this.ΜΚΟ80 = ΜΚΟ80;
		        this.yk20 = yk20;
		        this.xk20 = xk20;
		    }
		  
		  public Integer getId() {
		        return id;
		    }

/*
		// ONE TOMANY
		public Set<Metoxh> getMetoxh() {
			return Metoxh;
		}

		public void setMetoxi(Set<Metoxh> metoxi) {
			this.Metoxh = metoxi;
		}
*/		
		//ONE TO MANY
		

	  //Set<Metoxi> friendMetoxi() {
		      //  return metoxi;
		   // }
		
		public int getΜΚΟ15() {
			return ΜΚΟ15;
		}

		public void setΜΚΟ15(int μΚΟ15) {
			ΜΚΟ15 = μΚΟ15;
		}

		public int getΜΚΟ80() {
			return ΜΚΟ80;
		}

		public void setΜΚΟ80(int μΚΟ80) {
			ΜΚΟ80 = μΚΟ80;
		}

		public int getYk20() {
			return yk20;
		}

		public void setYk20(int yk20) {
			this.yk20 = yk20;
		}

		public int getXk20() {
			return xk20;
		}

		public void setXk20(int xk20) {
			this.xk20 = xk20;
		}

		public int getBeta() {
			return beta;
		}

		public void setBeta(int beta) {
			this.beta = beta;
		}
		
		 
	}
