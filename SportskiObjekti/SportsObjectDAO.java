package data;

import java.util.HashMap;

import beans.SportsObject;

public class SportsObjectDAO {
		private HashMap <Integer, SportsObject> sportsobjects;
		private String artikliPutanja = "";

		public SportsObjectDAO() {
			// TODO Auto-generated constructor stub
		}

		public ArtikliDAO(String contextPath) {
			this.setArtikli(new HashMap<Integer, SportsObject>());
			this.setArtikliPutanja(contextPath);
			
			ucitajArtikle(artikliPutanja);
		}
		
		
		public boolean DeleteObject(string name) {
			if(!getArtikliKolekcija().isEmpty()) {
				for (SportsObject a : getArtikliKolekcija()) {
					if(a.getName() == id) {
						// obrisan = true je kad je artikl logicni obrisan
						a.setObrisan(true);
						sacuvajArtikle();
						return true;
					}
				}
			}
			return false;
		}

		public void dodajArtikl(SportsObject a) {
			int maxId = 0;
			if(!getArtikliKolekcija().isEmpty()) {
				for(SportsObject a1 : getArtikliKolekcija()) {
					if(a1.getId() > maxId) {
						maxId = a1.getId();
					}
				}
				maxId++;
				a.setId(maxId);
			}
			
			sportsobjects.put(maxId, a);
			sacuvajArtikle();
		}
		
		public boolean izmeniArtikl(SportsObject a) {
			boolean ret = false;
			if(!getArtikliKolekcija().isEmpty()) {
				for(SportsObject a1 : getArtikliKolekcija()) {
					if(a1.getId() == a.getId()) {
						a1.setNaziv(a.getNaziv());
						a1.setKolicina(a.getKolicina());
						a1.setJedinicnaCena(a.getJedinicnaCena());
						a1.setOpis(a.getOpis());
						a1.setTipJela(a.getTipJela());
						sacuvajArtikle();
						ret = true;
						break;
					}
				}
			}

			return ret;
		}

		public Collection<SportsObject> getArtikliKolekcija() {
			return sportsobjects.values();
		}

		// Serijalizacija
		private void sacuvajArtikle() {
			File f = new File(artikliPutanja + "/data/sportsobjects.txt");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(f);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringArticles = objectMapper.writeValueAsString(sportsobjects);
				fileWriter.write(stringArticles);
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// Ucitavanje artikala iza fajla sportsobjects.txt
		@SuppressWarnings("unchecked")
		private void ucitajArtikle(String contextPath) {
			FileWriter fileWriter = null;
			BufferedReader in = null;
			File file = null;
			try {
				file = new File(contextPath + "/data/sportsobjects.txt");
				in = new BufferedReader(new FileReader(file));

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setVisibilityChecker(
						VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
				TypeFactory factory = TypeFactory.defaultInstance();
				MapType type = factory.constructMapType(HashMap.class, Integer.class, SportsObject.class);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				sportsobjects = ((HashMap<Integer, SportsObject>) objectMapper.readValue(file, type));
			} catch (FileNotFoundException fnfe) {
				try {
					file.createNewFile();
					fileWriter = new FileWriter(file);
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
					objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
					String stringArticles = objectMapper.writeValueAsString(sportsobjects);
					fileWriter.write(stringArticles);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (fileWriter != null) {
						try {
							fileWriter.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// GET i SET metode
		public HashMap<Integer, SportsObject> getArtikli() {
			return sportsobjects;
		}

		public void setArtikli(HashMap<Integer, SportsObject> sportsobjects) {
			this.sportsobjects = sportsobjects;
		}

		public String getArtikliPutanja() {
			return artikliPutanja;
		}

		public void setArtikliPutanja(String artikliPutanja) {
			this.artikliPutanja = artikliPutanja;
		}
		
		
		private void ucitajTestPodatke() {
			System.out.println("ucitajTestPodatke");
			
			SportsObject a1 = new SportsObject("Belo meso", "250", "Vrlo ukusno", "150", TipJela.JELO);
			SportsObject a2 = new SportsObject("Budweiser", "230", "Toèeno crno pivo", "500", TipJela.PICE);
			SportsObject a3 = new SportsObject("Nutella-plazma", "180", "Slakta zanimacija", "230", TipJela.JELO);
			SportsObject a4 = new SportsObject("Rose", "270", "Slatkasto vino", "175", TipJela.PICE);
			SportsObject a5 = new SportsObject("Quattro stagioni", "970", "Italijanska pica", "800", TipJela.JELO);
			
			dodajArtikl(a1);
			dodajArtikl(a2);
			dodajArtikl(a3);
			dodajArtikl(a4);
			dodajArtikl(a5);
		}
}
