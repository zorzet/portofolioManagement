package gr.aueb.mscis.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
/*
 * 8A TRAVAME TA DEDOMENA APO ISTOSELIDA
 * */

@Entity
@Table(name = "MarketsData")
public class MarketsData {

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
	
	public MarketsData(String date,float Opening,float Closing, float Max, float Min) {
		this.date=date;
		this.Opening=Opening;
		this.Closing=Closing;
		this.Max=Max;
		this.Min=Min;
	}
	public MarketsData() {
	
	}
	
}
