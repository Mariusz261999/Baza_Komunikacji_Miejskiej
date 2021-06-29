package bdbt_proj;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class UlgiDAOTest {
	private UlgiDAO dao2;

	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setUrl("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf");
		datasource.setUsername("MNIECIEC");
		datasource.setPassword("mnieciec");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");

		// import JdbcTemplat
		dao2 = new UlgiDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {

		List<Ulga> listUlga = dao2.list();
		assertTrue(!listUlga.isEmpty());
	}

	@Test
	void testSave() {
		Ulga ulga = new Ulga(10,"fajna", "20%","kozak ulga");
		dao2.save(ulga);

	}

	@Test
	void testGet() {
		int id_ulgi=10;
		Ulga ulga = dao2.get(id_ulgi);
		assertNotNull(ulga);
	}

	@Test
	void testUpdate() {

	}

	@Test
	void testDelete() {

		
	}

}
