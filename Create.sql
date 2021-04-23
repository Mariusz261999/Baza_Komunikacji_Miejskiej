/*
Project: Baza Komunikacji Miejskiej
Company: Politechnika Warszawska
Database: Oracle 12c
*/


-- Create tables section -------------------------------------------------

-- Table Pracownicy

CREATE TABLE Pracownicy(
  ID_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Plec Char(1 ) NOT NULL
        CONSTRAINT PlecC CHECK (Plec IN ('M','K')),
  Data_zatrudnienia Date NOT NULL,
  Nr_telefonu Varchar2(12 ) NOT NULL,
  Data_zwolnienia Date,
  PESEL Char(11 ),
  ID_biura Integer NOT NULL,
  ID_adresu Integer NOT NULL,
  ID_stanowiska Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Zatrudnia ON Pracownicy (ID_biura)
/

CREATE INDEX IX_Relationship4 ON Pracownicy (ID_adresu)
/

CREATE INDEX Pracownik_ma_stanowisko ON Pracownicy (ID_stanowiska)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Pracownicy_PK PRIMARY KEY (ID_pracownika)
/

-- Table Biura_Komunikacji_Miejskiej

CREATE TABLE Biura_Komunikacji_Miejskiej(
  ID_biura Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Miasto Varchar2(20 ) NOT NULL,
  Data_zalozenia Date NOT NULL,
  Strona_WWW Varchar2(50 )
)
/

-- Add keys for table Biura_Komunikacji_Miejskiej

ALTER TABLE Biura_Komunikacji_Miejskiej ADD CONSTRAINT BiuroKomunikacjiMiejskiej_PK PRIMARY KEY (ID_biura)
/

-- Table Bilety

CREATE TABLE Bilety(
  ID_biletu Integer NOT NULL,
  Rodzaj_biletu Varchar2(30 ) NOT NULL
        CONSTRAINT Rodzaj_biletuC CHECK (Rodzaj_biletu IN ('Calodobowy','20 minutowy','Miesieczny','75 minutowy','30 minutowy')),
  Data_skasowania Date,
  Id_Klienta Integer,
  ID_biura Integer NOT NULL
)
/

-- Create indexes for table Bilety

CREATE INDEX IX_Jest w posiadaniu ON Bilety (Id_Klienta)
/

CREATE INDEX IX_Biuro obsluguje bilety ON Bilety (ID_biura)
/

-- Add keys for table Bilety

ALTER TABLE Bilety ADD CONSTRAINT BiletyPK PRIMARY KEY (ID_biletu)
/

-- Table Klienci

CREATE TABLE Klienci(
  ID_Klienta Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Nr_telefonu Varchar2(12 ),
  ID_ulgi Integer,
  ID_adresu Integer NOT NULL
)
/

-- Create indexes for table Klienci

CREATE INDEX IX_Relationship2 ON Klienci (ID_ulgi)
/

CREATE INDEX IX_Relationship7 ON Klienci (ID_adresu)
/

-- Add keys for table Klienci

ALTER TABLE Klienci ADD CONSTRAINT Klient_PK PRIMARY KEY (ID_Klienta)
/

-- Table Pojazdy

CREATE TABLE Pojazdy(
  ID_pojazdu Integer NOT NULL,
  Rodzaj_pojazdu Varchar2(30 ) NOT NULL
        CONSTRAINT Rodzaj_pojazduC CHECK (Rodzaj_pojazdu IN ('AUTOBUS','TRAMWAJ','METRO')),
  Ilosc_miejsc Integer NOT NULL,
  Rodzaj_napedu Varchar2(15 ) NOT NULL
        CONSTRAINT Rodzaj_napeduC CHECK (Rodzaj_napedu IN ('Silnik spalinowy','Hybrydowy','Elektryczny')),
  Rok_produkcji Integer NOT NULL,
  ID_biura Integer NOT NULL
)
/

-- Create indexes for table Pojazdy

CREATE INDEX IX_Biuro posiada dany pojazd ON Pojazdy (ID_biura)
/

-- Add keys for table Pojazdy

ALTER TABLE Pojazdy ADD CONSTRAINT Pojazdy_PK PRIMARY KEY (ID_pojazdu)
/

-- Table Kursy

CREATE TABLE Kursy(
  ID_kursu Integer NOT NULL,
  Czy_nocny Char(3 ) NOT NULL
        CONSTRAINT Czy_nocnyC CHECK (Czy_nocny IN ('Tak','Nie')),
  Data Date NOT NULL,
  ID_pojazdu Integer NOT NULL,
  ID_pracownika Integer NOT NULL,
  ID_biura Integer NOT NULL,
  Id_trasy Integer NOT NULL
)
/

-- Create indexes for table Kursy

CREATE INDEX IX_Pojazd kursuje ON Kursy (ID_pojazdu)
/

CREATE INDEX IX_Pracownik prowadzi kurs ON Kursy (ID_pracownika)
/

CREATE INDEX IX_Obsluguje ON Kursy (ID_biura)
/

CREATE INDEX IX_Relationship5 ON Kursy (Id_trasy)
/

-- Add keys for table Kursy

ALTER TABLE Kursy ADD CONSTRAINT KursyPK PRIMARY KEY (ID_kursu)
/

-- Table Przystanki

CREATE TABLE Przystanki(
  ID_przystanku Integer NOT NULL,
  Nazwa Varchar2(40 ) NOT NULL,
  Szerokosc_geograficzna Number(8,6) NOT NULL,
  Dlugosc_geograficzna Number(8,6) NOT NULL,
  Na_zadanie Char(256 ) NOT NULL
        CONSTRAINT Na_zadanieC CHECK (Na_zadanie IN ('T','N'))
)
/

-- Add keys for table Przystanki

ALTER TABLE Przystanki ADD CONSTRAINT Przystanki_PK PRIMARY KEY (ID_przystanku)
/

-- Table Kierowcy

CREATE TABLE Kierowcy(
  ID_pracownika Integer NOT NULL,
  Data_waznosci_badan Date NOT NULL
)
/

-- Add keys for table Kierowcy

ALTER TABLE Kierowcy ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (ID_pracownika)
/

-- Table Kontrolerzy

CREATE TABLE Kontrolerzy(
  ID_pracownika Integer NOT NULL,
  Nr_urzadzenia Varchar2(30 ) NOT NULL,
  Premia Number(10,2)
)
/

-- Add keys for table Kontrolerzy

ALTER TABLE Kontrolerzy ADD CONSTRAINT Unique_Identifier2 PRIMARY KEY (ID_pracownika)
/

-- Table Mandaty

CREATE TABLE Mandaty(
  ID_mandatu Integer NOT NULL,
  Wysokosc_mandatu Number(10,2) NOT NULL,
  Data Date NOT NULL,
  Id_Klienta Integer NOT NULL,
  ID_pracownika Integer
)
/

-- Create indexes for table Mandaty

CREATE INDEX IX_Klient_dostal ON Mandaty (Id_Klienta)
/

CREATE INDEX IX_Kontrolet_wystawil ON Mandaty (ID_pracownika)
/

-- Add keys for table Mandaty

ALTER TABLE Mandaty ADD CONSTRAINT Mandat_PK PRIMARY KEY (ID_mandatu)
/

-- Table Trasy

CREATE TABLE Trasy(
  ID_trasy Integer NOT NULL,
  Nazwa Varchar2(40 ) NOT NULL
)
/

-- Table and Columns comments section

COMMENT ON COLUMN Trasy.ID_trasy IS 'Unikatowy identyfikator trasy'
/

-- Table Pracownicy_Pojazdy

CREATE TABLE Pracownicy_Pojazdy(
  ID_pracownika Integer NOT NULL,
  ID_pojazdu Integer NOT NULL
)
/

-- Table Ulgi

CREATE TABLE Ulgi(
  ID_ulgi Integer NOT NULL,
  Nazwa Varchar2(40 ) NOT NULL,
  Wysokosc Varchar2(3 ) NOT NULL,
  Opis Varchar2(200 ) NOT NULL
)
/

-- Add keys for table Ulgi

ALTER TABLE Ulgi ADD CONSTRAINT PK_Ulgi PRIMARY KEY (ID_ulgi)
/

ALTER TABLE Ulgi ADD CONSTRAINT Opis UNIQUE (Opis)
/

-- Table and Columns comments section

COMMENT ON COLUMN Ulgi.ID_ulgi IS 'Unikatowy identyfikator ulgi'
/
COMMENT ON COLUMN Ulgi.Nazwa IS 'Unikatowa nazwa ulgi'
/
COMMENT ON COLUMN Ulgi.Wysokosc IS 'Wysokosc ulgi'
/

-- Table Adresy

CREATE TABLE Adresy(
  ID_adresu Integer NOT NULL,
  Miasto Varchar2(20 ) NOT NULL,
  Ulica Varchar2(30 ) NOT NULL,
  Nr_lokalu Varchar2(4 ) NOT NULL,
  ID_poczty Integer NOT NULL
)
/

-- Create indexes for table Adresy

CREATE INDEX IX_Relationship3 ON Adresy (ID_poczty)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (ID_adresu)
/

-- Table and Columns comments section

COMMENT ON TABLE Adresy IS 'Encja Adresy'
/
COMMENT ON COLUMN Adresy.ID_adresu IS 'Unikatowy klucz glowny encji Adresy'
/
COMMENT ON COLUMN Adresy.Miasto IS 'Atrybut miasto '
/
COMMENT ON COLUMN Adresy.Ulica IS 'Ulica'
/
COMMENT ON COLUMN Adresy.Nr_lokalu IS 'Atrybut numer lokalu'
/

-- Table Poczty

CREATE TABLE Poczty(
  ID_poczty Integer NOT NULL,
  Kod_poczty Char(6 ) NOT NULL,
  Poczta Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Poczty

ALTER TABLE Poczty ADD CONSTRAINT PK_Poczty PRIMARY KEY (ID_poczty)
/

ALTER TABLE Poczty ADD CONSTRAINT Kod_poczty UNIQUE (Kod_poczty)
/

-- Table and Columns comments section

COMMENT ON TABLE Poczty IS 'Relacja slownikowa poczty'
/
COMMENT ON COLUMN Poczty.ID_poczty IS 'Unikatowy identyfikator poczty'
/
COMMENT ON COLUMN Poczty.Kod_poczty IS 'Kod pocztowy'
/
COMMENT ON COLUMN Poczty.Poczta IS 'Lokalizacja poczty'
/

-- Table Stanowiska

CREATE TABLE Stanowiska(
  ID_stanowiska Integer NOT NULL,
  Nazwa Varchar2(40 ) NOT NULL,
  Opis Varchar2(150 ) NOT NULL,
  Min_zarobki Number(9,2) NOT NULL,
  Max_zarobki Number(9,2) NOT NULL
)
/

-- Add keys for table Stanowiska

ALTER TABLE Stanowiska ADD CONSTRAINT PK_Stanowiska PRIMARY KEY (ID_stanowiska)
/

ALTER TABLE Stanowiska ADD CONSTRAINT Nazwa UNIQUE (Nazwa)
/

-- Table and Columns comments section

COMMENT ON TABLE Stanowiska IS 'Relacja slownikowa stanowiska'
/
COMMENT ON COLUMN Stanowiska.ID_stanowiska IS 'Unikatowy indentyfikator stanowiska'
/
COMMENT ON COLUMN Stanowiska.Nazwa IS 'Unikatowa nazwa stanowiska'
/
COMMENT ON COLUMN Stanowiska.Opis IS 'Opis stanowiska'
/
COMMENT ON COLUMN Stanowiska.Min_zarobki IS 'Minimalne zarobki na stawowisku'
/
COMMENT ON COLUMN Stanowiska.Max_zarobki IS 'Maksymalne zarobki na stanowisku'
/

-- Table Prawa_jazdy

CREATE TABLE Prawa_jazdy(
  ID_prawo_jazdy Integer NOT NULL,
  Nr_prawa_jazdy Varchar2(10 ) NOT NULL,
  Data_wydania Date NOT NULL,
  Data_waznosci Date NOT NULL,
  ID_pracownika Integer NOT NULL
)
/

-- Create indexes for table Prawa_jazdy

CREATE INDEX IX_Relationship6 ON Prawa_jazdy (ID_pracownika)
/

-- Add keys for table Prawa_jazdy

ALTER TABLE Prawa_jazdy ADD CONSTRAINT PK_Prawa_jazdy PRIMARY KEY (ID_prawo_jazdy)
/

ALTER TABLE Prawa_jazdy ADD CONSTRAINT Nr_prawa_jazdy UNIQUE (Nr_prawa_jazdy)
/

-- Table and Columns comments section

COMMENT ON TABLE Prawa_jazdy IS 'Encja prawa jazdy'
/
COMMENT ON COLUMN Prawa_jazdy.ID_prawo_jazdy IS 'Unikatowy identyfikator prawo jazdy'
/
COMMENT ON COLUMN Prawa_jazdy.Data_wydania IS 'Data wydania prawo jazdy'
/
COMMENT ON COLUMN Prawa_jazdy.Data_waznosci IS 'Data waznosci prawo jazdy'
/

-- Table Wynagrodzenia

CREATE TABLE Wynagrodzenia(
  ID_wynagrodzenia Integer NOT NULL,
  Data Date NOT NULL,
  Kwota_podstawowa Number(10,2) NOT NULL,
  Kwota_dodatkowa Number(10,2),
  ID_pracownika Integer NOT NULL
)
/

-- Create indexes for table Wynagrodzenia

CREATE INDEX IX_Relationship1 ON Wynagrodzenia (ID_pracownika)
/

-- Add keys for table Wynagrodzenia

ALTER TABLE Wynagrodzenia ADD CONSTRAINT PK_Wynagrodzenia PRIMARY KEY (ID_wynagrodzenia)
/

-- Table and Columns comments section

COMMENT ON COLUMN Wynagrodzenia.ID_wynagrodzenia IS 'Klucz unikatowy wynagrodzenia'
/
COMMENT ON COLUMN Wynagrodzenia.Data IS 'Data wynagrodzenia'
/

-- Table Trasy_przystanki

CREATE TABLE Trasy_przystanki(
  Id_trasy Integer NOT NULL,
  ID_przystanku Integer NOT NULL,
  Kolejnosc Integer NOT NULL
)
/

-- Add keys for table Trasy_przystanki

ALTER TABLE Trasy_przystanki ADD CONSTRAINT PK_Trasy_przystanki PRIMARY KEY (Id_trasy,ID_przystanku)
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Pracownicy ADD FOREIGN KEY (ID_biura) REFERENCES Biura_Komunikacji_Miejskiej (ID_biura)
/



ALTER TABLE Bilety ADD FOREIGN KEY (Id_Klienta) REFERENCES Klienci (ID_Klienta)
/



ALTER TABLE Bilety ADD FOREIGN KEY (ID_biura) REFERENCES Biura_Komunikacji_Miejskiej (ID_biura)
/



ALTER TABLE Pojazdy ADD FOREIGN KEY (ID_biura) REFERENCES Biura_Komunikacji_Miejskiej (ID_biura)
/



ALTER TABLE Kursy ADD FOREIGN KEY (ID_pojazdu) REFERENCES Pojazdy (ID_pojazdu)
/



ALTER TABLE Kursy ADD FOREIGN KEY (ID_pracownika) REFERENCES Pracownicy (ID_pracownika)
/



ALTER TABLE Mandaty ADD FOREIGN KEY (Id_Klienta) REFERENCES Klienci (ID_Klienta)
/



ALTER TABLE Mandaty ADD FOREIGN KEY (ID_pracownika) REFERENCES Kontrolerzy (ID_pracownika)
/



ALTER TABLE Kursy ADD FOREIGN KEY (ID_biura) REFERENCES Biura_Komunikacji_Miejskiej (ID_biura)
/



ALTER TABLE Klienci ADD FOREIGN KEY (ID_ulgi) REFERENCES Ulgi (ID_ulgi)
/



ALTER TABLE Adresy ADD FOREIGN KEY (ID_poczty) REFERENCES Poczty (ID_poczty)
/



ALTER TABLE Pracownicy ADD FOREIGN KEY (ID_adresu) REFERENCES Adresy (ID_adresu)
/



ALTER TABLE Pracownicy ADD FOREIGN KEY (ID_stanowiska) REFERENCES Stanowiska (ID_stanowiska)
/



ALTER TABLE Prawa_jazdy ADD FOREIGN KEY (ID_pracownika) REFERENCES Kierowcy (ID_pracownika)
/



ALTER TABLE Klienci ADD FOREIGN KEY (ID_adresu) REFERENCES Adresy (ID_adresu)
/



ALTER TABLE Wynagrodzenia ADD FOREIGN KEY (ID_pracownika) REFERENCES Pracownicy (ID_pracownika)
/



ALTER TABLE Kursy ADD FOREIGN KEY (Id_trasy) REFERENCES Trasy (ID_trasy)
/



ALTER TABLE Trasy_przystanki ADD FOREIGN KEY (Id_trasy) REFERENCES Trasy (ID_trasy)
/



ALTER TABLE Trasy_przystanki ADD FOREIGN KEY (ID_przystanku) REFERENCES Przystanki (ID_przystanku)
/





