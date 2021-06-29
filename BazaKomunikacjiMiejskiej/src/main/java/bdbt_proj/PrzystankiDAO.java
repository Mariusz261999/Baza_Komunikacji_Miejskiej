package bdbt_proj;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PrzystankiDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PrzystankiDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Przystanek> list() {
		String sql = "SELECT * FROM PRZYSTANKI order by id_przystanku";
		List<Przystanek> listPrzystanek = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Przystanek.class));

		return listPrzystanek;
	}

	// Insert
	public void save(Przystanek przystanek) {
		
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("przystanki").usingColumns("ID_przystanku","nazwa", "szerokosc_geograficzna", "dlugosc_geograficzna", "na_zadanie");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(przystanek);
		insertActor.execute(param);
	}

	// Read
	public Przystanek get(int id_przystanku) {
		Object[] args = {id_przystanku};
		String sql = "SELECT * FROM PRZYSTANKI where id_przystanku = " + args[0];
		Przystanek przystanek = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Przystanek.class));
		return przystanek;
	}

	// Update
	public void update(Przystanek przystanek) {
		
		String sql ="UPDATE PRZYSTANKI SET nazwa=:nazwa, szerokosc_geograficzna=:szerokosc_geograficzna, dlugosc_geograficzna=:dlugosc_geograficzna, na_zadanie=:na_zadanie WHERE ID_przystanku=:ID_przystanku";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(przystanek);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);

	}

	// Delete
	public void delete(int id) {
		String sql = "DELETE FROM PRZYSTANKI WHERE ID_PRZYSTANKU = ?";
		jdbcTemplate.update(sql,id);
		
	}
}
