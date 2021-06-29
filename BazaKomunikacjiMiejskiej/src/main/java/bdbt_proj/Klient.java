package bdbt_proj;

public class Klient {

	private int id_klienta;
	private String imie;
	private String nazwisko;
	private String nr_telefonu;
	private int id_ulgi;
	private int id_adresu;
	
	public Klient() {}

	
	public Klient(int id_klienta, String imie, String nazwisko, String nr_telefonu, int id_ulgi, int id_adresu) {
		super();
		this.id_klienta = id_klienta;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_telefonu = nr_telefonu;
		this.id_ulgi = id_ulgi;
		this.id_adresu = id_adresu;
	}


	public int getId_klienta() {
		return id_klienta;
	}

	public void setId_klienta(int id_klienta) {
		this.id_klienta = id_klienta;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNr_telefonu() {
		return nr_telefonu;
	}

	public void setNr_telefonu(String nr_telefonu) {
		this.nr_telefonu = nr_telefonu;
	}

	public int getId_ulgi() {
		return id_ulgi;
	}

	public void setId_ulgi(int id_ulgi) {
		this.id_ulgi = id_ulgi;
	}

	public int getId_adresu() {
		return id_adresu;
	}

	public void setId_adresu(int id_adresu) {
		this.id_adresu = id_adresu;
	}


	@Override
	public String toString() {
		return "Klient [id_klienta=" + id_klienta + ", imie=" + imie + ", nazwisko=" + nazwisko + ", nr_telefonu="
				+ nr_telefonu + ", id_ulgi=" + id_ulgi + ", id_adresu=" + id_adresu + "]";
	}
	
	
}
