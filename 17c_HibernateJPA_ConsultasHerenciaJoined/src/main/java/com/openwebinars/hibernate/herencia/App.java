package com.openwebinars.hibernate.herencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Herencia con Joined - Consultas
 * www.openwebinars.net
 * @LuisMLopezMag
 */
public class App {
	static EntityManagerFactory emf;

	static EntityManager em;

	public static void main(String[] args) {

		// Configuramos el EMF a través de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("ConsultasJoined");

		// Generamos un EntityManager
		em = emf.createEntityManager();
		
		//Inicializamos unos datos de ejemplo
		initData();
		
		
		//Consulta 
		//Query query1 = em.createQuery("select c from Cuenta c");
		//printList(query1.getResultList());
		
		//Query query2 = em.createQuery("select c from CuentaDebito c");
		//printList(query2.getResultList());

		//Consulta por tipo
		Query query3 = em.createQuery("select c from Cuenta c Where TYPE(c) = Cuenta");
		printList(query3.getResultList());
		
		// Cerramos el EntityManager
		em.close();

	}

	public static void initData() {
		// Iniciamos una transacción
		em.getTransaction().begin();

		String[] names = { "Alberto", "Cecilio", "Luis", "Miguel", "Ángel", "Rafael", "Raúl", "Justo", "José",
				"Antonio" };

		Random r = new Random();

		// Generamos Cuentas
		for (int i = 0; i < r.nextInt(5)+5; i++) {
			Cuenta cuenta = new Cuenta();
			cuenta.setTitular(names[r.nextInt(10)]);
			cuenta.setBalance(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue() * r.nextInt(50)+50);
			cuenta.setTipoInteres(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
			em.persist(cuenta);
		}

		// Generamos Cuentas de crédito
		for (int i = 0; i < r.nextInt(5)+5; i++) {
			CuentaCredito cuentaCredito = new CuentaCredito();
			cuentaCredito.setTitular(names[r.nextInt(10)]);
			cuentaCredito.setBalance(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue() * r.nextInt(50)+50);
			cuentaCredito.setTipoInteres(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
			cuentaCredito.setLimiteCredito(r.nextInt(10) * 100.0);
			em.persist(cuentaCredito);
		}

		// Generamos Cuentas de débito
		for (int i = 0; i < r.nextInt(5)+5; i++) {
			CuentaDebito cuentaDebito = new CuentaDebito();
			cuentaDebito.setTitular(names[r.nextInt(10)]);
			cuentaDebito.setBalance(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue() * r.nextInt(50)+50);
			cuentaDebito.setTipoInteres(new BigDecimal(r.nextDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
			cuentaDebito.setCargoPorDescubierto(r.nextDouble() + r.nextInt(5));
			em.persist(cuentaDebito);
		}
		
		// Commiteamos la transacción
		em.getTransaction().commit();
	}
	
	public static void printList(List<Cuenta> list) {
		System.out.println("\n\n\n");
		for(Cuenta c: list) {
			System.out.println(c);
		}
	}
	

}
