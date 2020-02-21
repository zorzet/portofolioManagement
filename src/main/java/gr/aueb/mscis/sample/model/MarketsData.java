package gr.aueb.mscis.sample.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.UniqueConstraint;

import java.util.HashSet;
//import java.util.*;

@Entity
@Table(name = "MarketsData",uniqueConstraints = {
        @UniqueConstraint(columnNames = "MDId")})
public class MarketsData{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MDId", unique = true, nullable = false)
	private int MDId;
	

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name="Operning", nullable=false)
	private double Opening;
	
	@Column(name="Closing", nullable=false)
	private double Closing;
	
	@Column(name="Max", nullable=false)
	private double Max;
	
	@Column(name="Min", nullable=false)
	private double Min;
	
	public MarketsData(int MDID, String date, double Opening, double Closing, double Max, double Min) {
		this.MDId=MDID;
		this.date=date;
		this.Opening=Opening;
		this.Closing=Closing;
		this.Max=Max;
		this.Min=Min;
	}
	public MarketsData() {
	
	}
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public double getOpening() {
		return this.Opening;
	}

	public void setOpening(float Opening) {
		this.Opening = Opening;
	}
	public double getClosing() {
		return this.Closing;
	}

	public void setClosing(float Closing) {
		this.Closing = Closing;
	}
	public double getMax() {
		return this.Max;
	}

	public void setMax(float Max) {
		this.Max = Max;
	}	
	public double getMin() {
		return this.Min;
	}

	public void setMin(float Min) {
		this.Min = Min;
	}
}
