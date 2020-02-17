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
@Table(name="MarketsData")
public class MarketsData {
 
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name="FTSE", nullable=false)
	private float FTSE;
	
	@Column(name="Closing", nullable=false)
	private float Closing;
	
}
