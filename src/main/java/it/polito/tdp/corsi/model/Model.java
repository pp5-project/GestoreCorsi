package it.polito.tdp.corsi.model;
import java.util.*;
import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO corsoDao;
	
	public Model() { //costruttore
		corsoDao=new CorsoDAO();
	}
	public List<Corso> getCorsiByPeriodo(Integer pd){
		return corsoDao.getCorsiByPeriodo(pd);
	}
	public Map<Corso,Integer> getIscrittiByPeriodo(Integer periodo){
		return corsoDao.getIscrittiByPeriodo(periodo);
	}
	public List<Studente> getStudentiByCorso(String codice) {
		return corsoDao.getStudentibyCorso(new Corso(codice,null,null,null)); //supponiamo che il controllore ci passa la stringa 
		                                                                      // e il modello costruisce un corso
			}
	public boolean esisteCorso(String codice) {
		return CorsoDAO.esisteCorso(new Corso(codice,null,null,null));
		
	}
	public Map<String,Integer> getDivisioneCDS_uno (String codice) {
		Map<String, Integer> divisione=new HashMap<String,Integer>();
		List<Studente> studenti=this.getStudentiByCorso(codice);
		for(Studente s:studenti) {
			if(!s.getCDS().equals("")) {
			if(divisione.get(s.getCDS())==null) {
				divisione.put(s.getCDS(),1);
			}
			else {
				divisione.put(s.getCDS(), divisione.get(s.getCDS())+1);
			}
		}
		}
		return divisione;
	}
	public Map<String,Integer> getDivisioneCDS_due (String codice) {
		return corsoDao.getDivisione(new Corso(codice,null,null,null));
	}
}
