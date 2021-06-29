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
public class KlienciDAO {
@Autowired
private JdbcTemplate jdbcTemplate;

public KlienciDAO(JdbcTemplate jdbcTemplate) {
	super();
	this.jdbcTemplate = jdbcTemplate;
}

public List<Klient> list() {
	String sql = "SELECT * FROM KLIENCI order by id_klienta";
	List<Klient> listKlient = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));

	return listKlient;
}

// Insert
public void save(Klient klient) {
	
	SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
	insertActor.withTableName("klienci").usingColumns("id_klienta","imie", "nazwisko", "nr_telefonu", "id_ulgi", "id_adresu");
	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
	insertActor.execute(param);
}

// Read
public Klient get(int id_klienta) {
	Object[] args = {id_klienta};
	String sql = "SELECT * FROM KLIENCI where id_klienta = " + args[0];
	Klient klient= jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Klient.class));
	return klient;
}

// Update
public void update(Klient klient) {
	
	String sql ="UPDATE Klienci SET imie=:imie, nazwisko=:nazwisko, nr_telefonu=:nr_telefonu, id_ulgi=:id_ulgi, id_adresu=:id_adresu WHERE id_klienta=:id_klienta";
	BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
	template.update(sql, param);

}

// Delete
public void delete(int id) {
	String sql = "DELETE FROM klienci WHERE id_klienta = ?";
	jdbcTemplate.update(sql,id);
	
}

}
