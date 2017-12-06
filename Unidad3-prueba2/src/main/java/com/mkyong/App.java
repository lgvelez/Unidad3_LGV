package com.mkyong;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.mkyong.util.HibernateUtil;
import com.mkyong.user.DBUser;
import com.mkyong.stock.Category;
import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDailyRecord;


public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
//
//		DBUser user = new DBUser();
//		user.setUserId(100);
//		user.setUsername("superman");
//		user.setCreatedBy("system");
//		user.setCreatedDate(new Date());
//		session.saveOrUpdate(user);
//
//		user = new DBUser();
//		user.setUserId(101);
//		user.setUsername("batman");
//		user.setCreatedBy("superman");
//		user.setCreatedDate(new Date());
//		session.saveOrUpdate(user);

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		//session.saveOrUpdate(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		

	    Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        session.saveOrUpdate(stock);
        
        session.saveOrUpdate(stockDailyRecords);
		
		session.getTransaction().commit();
	}
}
