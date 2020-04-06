package pl.szymonleyk.jpa.hibernatesample;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.sound.midi.Soundbank;

public class AddressControler {
	public static void main(String[] args) {
		// pobierz adres o id = 5

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db-unit");
		EntityManager em = emf.createEntityManager();

		// pobierz z bazy danych
		Address address = em.find(Address.class, 5);
		System.out.println(address);

		// zmieñ
		String houseNumber = String.valueOf(LocalDateTime.now().getSecond());
		address.setHouseNumber(houseNumber);

		// zapisz w bazie danych
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(address);
			et.commit();
		} catch (Exception e) {
			et.rollback();
		}

		// pobierz z bazy danych
		address = em.find(Address.class, 5);
		System.out.println(address);

		// zmieñ
		address.setHouseNumber("99A");

		// usuwanie z bazy
		et = em.getTransaction();
		try {
			et.begin();
			em.remove(address);
			et.commit();
		} catch (Exception e) {
			et.rollback();
		}

		// C-create em.persist add
		// R-read em.find get
		// U-update em.find -> em.persist get(1).setImie("Marian")
		// D-delete em.find -> em.remove remove(1)

		// wyœwietl adresy z Gdyni
		// przygotowanie zapytania
		TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
		// wywo³anie
		List<Address> addresses = query.getResultList();
		// wyœwietlenie
		for (Address a : addresses) {
			System.out.println(a);
		}

		
		displayAddressByCity("Gdynia");
		displayAddressByCity("Gliwice");
		
	}
	
	
	private static void displayAddressByCity(String cityName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db-unit");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("------------------- adresy "+cityName+" -------------------");
		
		TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE city=:cityParam", Address.class);
		query.setParameter("cityParam", cityName);
		
		List<Address> addresses = query.getResultList();
		
		for (Address a : addresses) {
			System.out.println(a);
		}
	}
}
