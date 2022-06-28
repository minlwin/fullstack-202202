package com.jdc.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jdc.demo.entity.Account;

public class PersistenceContextSyncTest {

	EntityManagerFactory emf;
	
	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@AfterEach
	void close() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Test
	void test() {
		
		var operation1 = getThreadOne();
		var operation2 = getThreadTwo();
		
		operation1.start();
		operation2.start();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private Thread getThreadOne() {
		return new Thread(
				() -> {
					System.out.println("Start Thread 1");
					var em = emf.createEntityManager();
					var account = em.find(Account.class, 1);
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					em.getTransaction().begin();
					System.out.println("Before Operation 1 : Balance of %s is %d.".formatted(account.getName(), account.getBalance()));

					account.setBalance(account.getBalance() + 100_000);
					
					em.flush();
					
					System.out.println("After Operation 1: Balance of %s is %d.".formatted(account.getName(), account.getBalance()));
					
					em.getTransaction().commit();
					
				});
	}
	
	private Thread getThreadTwo() {
		return new Thread(
				() -> {
					System.out.println("Start Thread 1");
					var em = emf.createEntityManager();
					var account = em.find(Account.class, 1);

					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					em.getTransaction().begin();
					System.out.println("Before Operation 2 : Balance of %s is %d.".formatted(account.getName(), account.getBalance()));
					
					em.refresh(account);

					System.out.println("Before Operation 2 : Balance of %s is %d. (Refreshed)".formatted(account.getName(), account.getBalance()));

					account.setBalance(account.getBalance() - 50_000);
					System.out.println("After Operation 2: Balance of %s is %d.".formatted(account.getName(), account.getBalance()));
					
					em.getTransaction().commit();
				});
	}
	

}
