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
public class UlgiDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UlgiDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate=jdbcTemplate;
	}
	public List<Ulga> list(){
		String sql = "SELECT * from ULGI order by id_ulgi";
		List<Ulga> listUlga = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ulga.class));
		return listUlga;
	}
	
	// Insert
		public void save(Ulga ulga) {
			
			SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
			insertActor.withTableName("ulgi").usingColumns("id_ulgi","nazwa", "wysokosc", "opis");
			BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ulga);
			insertActor.execute(param);
		}

		// Read
		public Ulga get(int id_ulgi) {
			Object[] args = {id_ulgi};
			String sql = "SELECT * FROM ULGI where id_ulgi = " + args[0];
			Ulga ulga = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Ulga.class));
			return ulga;
		}

		// Update
		public void update(Ulga ulga) {
			
			String sql ="UPDATE ULGI SET nazwa=:nazwa, wysokosc=:wysokosc, opis=:opis WHERE id_ulgi=:id_ulgi";
			BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ulga);
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
			template.update(sql, param);

		}

		// Delete
		public void delete(int id) {
			String sql = "DELETE FROM ULGI WHERE ID_ULGI = ?";
			jdbcTemplate.update(sql,id);
			
		}
}
