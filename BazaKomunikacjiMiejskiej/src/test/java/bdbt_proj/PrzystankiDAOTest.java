package bdbt_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class PrzystankiDAOTest {
	private PrzystankiDAO dao;

	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf");
		datasource.setUsername("MNIECIEC");
		datasource.setPassword("mnieciec");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		// import JdbcTemplat
		dao = new PrzystankiDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {

		List<Przystanek> listPrzystanek = dao.list();
		assertTrue(listPrzystanek.isEmpty());
	}

	@Test
	void testSave() {

		Przystanek przystanek = new Przystanek(187, "fajny", 59.8462f, 68.469f, "T");
		dao.save(przystanek);

	}

	@Test
	void testGet() {

		int id_przystanku = 999;
		Przystanek przystanek = dao.get(id_przystanku);
		assertNotNull(przystanek);
	}

	@Test
	void testUpdate() {

		Przystanek przystanek = new Przystanek();
		przystanek.setID_przystanku(187);
		przystanek.setSzerokosc_geograficzna(50.008f);
		przystanek.setDlugosc_geograficzna(69.002f);
		przystanek.setNa_zadanie("T");
		przystanek.setNazwa("SuperPrzystanek");
		dao.update(przystanek);
	}

	@Test
	void testDelete() {
		int id = 99;
		dao.delete(id);
		
	}

}
