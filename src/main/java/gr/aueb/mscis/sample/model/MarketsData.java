package gr.aueb.mscis.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import java.util.*;

@Entity
@Table(name = "MarketsData")
public class MarketsData{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int MDID;
	
	@Column(name = "date", nullable = false)
	private String date;

	@Column(name="Operning", nullable=false)
	private float Opening;
	
	@Column(name="Closing", nullable=false)
	private float Closing;
	
	@Column(name="Max", nullable=false)
	private float Max;
	
	@Column(name="Min", nullable=false)
	private float Min;
	
	public MarketsData(int MDID, String date, float Opening, float Closing, float Max, float Min) {
		this.MDID=MDID;
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
	public float getOpening() {
		return this.Opening;
	}

	public void setOpening(float Opening) {
		this.Opening = Opening;
	}
	public float getClosing() {
		return this.Closing;
	}

	public void setClosing(float Closing) {
		this.Closing = Closing;
	}
	public float getMax() {
		return this.Max;
	}

	public void setMax(float Max) {
		this.Max = Max;
	}	
	public float getMin() {
		return this.Min;
	}

	public void setMin(float Min) {
		this.Min = Min;
	}
}
