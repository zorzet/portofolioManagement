package gr.aueb.mscis.sample.persistence; 

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.model.Xartofulakio;


public class Initializer {

	public void eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Query query = em.createNativeQuery("delete from MarketsData");
        query.executeUpdate();
        tx.commit();
        em.close();
	} 
	
	public void prepareData() {
		eraseData();
		
        MarketsData temp1 = new MarketsData(1,"29/11/2019",1603.1,1636.48,1603.1,1633.34);
        MarketsData temp2 = new MarketsData(2,"30/11/2019",1629.27,1657.17,1608.11,1657.17);
        MarketsData temp3 = new MarketsData(3,"12/01/2016",1709.15,1709.15,1662.25,1664.59);
        MarketsData temp4 = new MarketsData(4,"12/02/2016",1661.81,1674.62,1638.8,1665.46);
        MarketsData temp5 = new MarketsData(5,"12/05/2016",1664.83,1696.47,1664.83,1679.01);
        MarketsData temp6 = new MarketsData(6,"12/06/2016",1683.09,1696.68,1661.44,1682.3);
        MarketsData temp7 = new MarketsData(7,"12/07/2016",1692.03,1746.57,1692.03,1726.59);
        MarketsData temp8 = new MarketsData(8,"12/08/2016",1739.24,1760.4,1733.08,1758.96);
        MarketsData temp9 = new MarketsData(9,"12/09/2016",1751.39,1751.39,1717.56,1739.56);
        MarketsData temp10 = new MarketsData(10,"12/12/2016",1734.23,1747.74,1728.1,1741.24);
        MarketsData temp11 = new MarketsData(11,"12/13/2016",1734.94,1742.07,1718.16,1736.04);
        MarketsData temp12 = new MarketsData(12,"12/14/2016",1732.71,1732.71,1666.56,1673.38);
        MarketsData temp13 = new MarketsData(13,"12/15/2016",1665.48,1667.61,1632.2,1663.88);
        MarketsData temp14 = new MarketsData(14,"12/16/2016",1674.55,1727.78,1671.37,1725.41);
        MarketsData temp15 = new MarketsData(15,"12/19/2016",1710.63,1710.77,1672.86,1688.44);
        MarketsData temp16 = new MarketsData(16,"12/20/2016",1687.55,1700.12,1666.84,1683.97);
        MarketsData temp17 = new MarketsData(17,"12/21/2016",1694.62,1725.12,1693.05,1712.68);
        MarketsData temp18 = new MarketsData(18,"12/22/2016",1705.1,1710.86,1684.29,1695.79);
        MarketsData temp19 = new MarketsData(19,"12/23/2016",1692.09,1703.05,1677.29,1695.26);
        MarketsData temp20 = new MarketsData(20,"12/27/2016",1707.6,1735.02,1707.6,1714.24);
        MarketsData temp21 = new MarketsData(21,"12/28/2016",1708.8,1729.57,1703.29,1710.3);
        MarketsData temp22 = new MarketsData(22,"12/29/2016",1711.99,1719.89,1700.71,1717.52);
        MarketsData temp23 = new MarketsData(23,"12/30/2016",1716.18,1742.02,1716.18,1740.86);
        MarketsData temp24 = new MarketsData(24,"1/02/2017",1741.46,1753.04,1740.67,1744.94);
        MarketsData temp25 = new MarketsData(25,"1/03/2017",1750.98,1778.75,1745.83,1769.69);
        MarketsData temp26 = new MarketsData(26,"1/04/2017",1778.02,1780.06,1763.24,1773.84);
        MarketsData temp27 = new MarketsData(27,"1/05/2017",1773.71,1773.94,1752.64,1773.94);
        MarketsData temp28 = new MarketsData(28,"1/09/2017",1773.62,1773.81,1751.83,1769.74);
        MarketsData temp29 = new MarketsData(29,"1/10/2017",1770.81,1782.69,1762.37,1772.33);
        MarketsData temp30 = new MarketsData(30,"1/11/2017",1770.94,1781.5,1767.13,1780.84);
        MarketsData temp31 = new MarketsData(31,"1/12/2017",1783.33,1797.3,1774.43,1782.88);
        
        Metoxh st1=new Metoxh(1, "AEGN" , "22/02/2020", 8.70, 8.60, 8.69,0.6,132000);
        Metoxh st2=new Metoxh(2, "INTRK" , "22/02/2020", 0.9, 0.80, 0.88,0.0,69000);
        Metoxh st3=new Metoxh(3, "MOH" , "22/02/2020", 20.6, 20.45, 20.52,1.3,80200);
        Metoxh st4=new Metoxh(4, "ELPE" , "22/02/2020", 8.41, 8.20,8.36,0.75,123000);
        Metoxh st5=new Metoxh(5, "OPAP" , "22/02/2020", 11.46,11.39,11.44,1.71,654000);
        
        Xartofulakio x1=new Xartofulakio(1, "created",1001,"AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234");
        Xartofulakio x2=new Xartofulakio(2, "created",2002,"AE12345", "123456789", "Marios","Papas", "2121212121", "msp@gmail.com", "16/07/1980",
    			12000, "GE075 5678 5678 5678 5678");
      
        
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        em.persist(temp1);
        em.persist(temp2);
        em.persist(temp3);
        em.persist(temp4);
        em.persist(temp5);
        em.persist(temp6);
        em.persist(temp7);
        em.persist(temp8);
        em.persist(temp9);
        em.persist(temp10);
        em.persist(temp11);
        em.persist(temp12);
        em.persist(temp13);
        em.persist(temp14);
        em.persist(temp15);
        em.persist(temp16);
        em.persist(temp17);
        em.persist(temp18);
        em.persist(temp19);
        em.persist(temp20);
        em.persist(temp21);
        em.persist(temp22);
        em.persist(temp23);
        em.persist(temp24);
        em.persist(temp25);
        em.persist(temp26);
        em.persist(temp27);
        em.persist(temp28);
        em.persist(temp29);
        em.persist(temp30);
        em.persist(temp31);
        
        em.persist(st1);
        em.persist(st2);
        em.persist(st3);
        em.persist(st4);
        em.persist(st5);
        
        em.persist(x1);
        em.persist(x2);
        
        tx.commit();
	}
}
