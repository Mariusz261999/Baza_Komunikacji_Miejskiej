package bdbt_proj;

public class Ulga {
	private int id_ulgi;
	private String nazwa;
	private String wysokosc;
	private String opis;

	public Ulga() {

	}

	public Ulga(int id_ulgi, String nazwa, String wysokosc, String opis) {
		super();
		this.id_ulgi = id_ulgi;
		this.nazwa = nazwa;
		this.wysokosc = wysokosc;
		this.opis = opis;
	}

	public int getId_ulgi() {
		return id_ulgi;
	}

	public void setId_ulgi(int id_ulgi) {
		this.id_ulgi = id_ulgi;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getWysokosc() {
		return wysokosc;
	}

	public void setWysokosc(String wysokosc) {
		this.wysokosc = wysokosc;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "Ulga [id_ulgi=" + id_ulgi + ", nazwa=" + nazwa + ", wysokosc=" + wysokosc + ", opis=" + opis + "]";
	}

}
