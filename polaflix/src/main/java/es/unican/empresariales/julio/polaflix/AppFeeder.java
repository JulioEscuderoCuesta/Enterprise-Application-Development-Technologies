package es.unican.empresariales.julio.polaflix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.empresariales.julio.polaflix.repositories.BillRepository;
import es.unican.empresariales.julio.polaflix.repositories.UserRepository;

@Component
public class AppFeeder implements CommandLineRunner {
	
	@Autowired
	protected BillRepository br;
	@Autowired
	protected UserRepository ur;

	
	@Override
	public void run(String... args) throws Exception {
		feedDatabase();
		testViajeRepository();
		
		System.out.println("Application feeded");
	}

	private void feedDatabase() {
		User u1 = new MonthlyUser("Julio", "1234", "ES66 2100 0418 4012 3456 7891");
		User u2 = new NormalUser("Marta", "5678", "ES66 2100 0418 4012 3456 7892");
		ur.save(u1);
		ur.save(u2);

		Bill c = new Bill(5, 2023, u1);
		Bill c2 = new Bill(6,2023, u1);
		br.save(c);
        br.save(c2);
	}

	private void testViajeRepository() {
		
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date sample = null;
		try {
			sample = dateParser.parse("01-01-2020");
		} catch (ParseException e) {
			System.out.println("Crujo parseando fecha");
			e.printStackTrace();
		}
		
		List<Bill> billsU1 = br.findByStatus(BillStatus.INPROGRESS.toString());
		
		System.out.println("Número facturas = " + billsU1.size());
	
		for(Bill b : billsU1) {
			System.out.println("Importe factura: " + b.getTotalCost());
		}

		
	}

}
