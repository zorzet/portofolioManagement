package gr.aueb.mscis.sample.resource;

import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Metoxh;

@XmlRootElement
public class MarketInfo {
	private String marketInfo;

	public String getMarketInfo() {
		return marketInfo;
	}

	public void setMarketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
	}
	
	public MarketInfo(String marketInfo) {
		super();
		this.marketInfo = marketInfo;
	}

	public static  MarketInfo wrap(String info) {
		return new  MarketInfo(info);
	}
}
