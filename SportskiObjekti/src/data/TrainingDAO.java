package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.ScheduledTraining;
import beans.SportsObject;
import beans.Training;
import beans.User;
import data.utils.ObjectType;
import data.utils.TrainingType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TrainingDAO{
	private HashMap<Integer, Training> trainingCollection=new HashMap<>();
	private HashMap<Integer, ScheduledTraining> scheduledTrainingCollection=new HashMap<>();
	private String trainingFilepath="";
	
	private int idScheduled;
	
	
	public TrainingDAO(String trainingFilepath) {
		super();
		idScheduled=0;
		this.trainingFilepath = trainingFilepath;
		loadTrainings();
		loadScheduledTrainings();
 	}
	
	public void addTraining(Training t) {
		if(t.getId()==0) setNextKey(t);
		trainingCollection.put(t.getId(), t);
		saveTraining(t);
	}
	private String saveTraining(Training t) {
		try {
			String str = t.getString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(trainingFilepath + "/trainings.csv", true));
		    writer.append(str);
		    writer.append("\n");
		    writer.close();
		    return "Uspešno dodat trening!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Neuspešan upis u fajl";
		}
	}
	
	private void setNextKey(Training t) {
		int largest=0;
		for(int i:trainingCollection.keySet()) {
			if(i>largest) largest=i;
		} 
		t.setId(++largest);
	}
	
	public String addScheduledTraining(ScheduledTraining t) {
		idScheduled++;
		if(t.getId()==0) {
			t.setId(idScheduled);
		}
		scheduledTrainingCollection.put(t.getId(), t);
		return saveScheduledTraining(t);
	}
	private String saveScheduledTraining(ScheduledTraining t) {
		try {
			String str = t.trainingString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(trainingFilepath + "/scheduledTrainings.csv",true));
		    writer.append("\n");
		    writer.append(str);
		    writer.close();
		    return "Uspešno dodat trening!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Neuspešan upis u fajl";
		}
	}
	
	private void saveScheduled() {
		try {
			String str="";
		    BufferedWriter writer = new BufferedWriter(new FileWriter("/scheduledTrainings.csv"));
		    writer.write("");
		    for (ScheduledTraining s : getScheduledTrainingCollection()) {
				str=s.trainingString();
				writer.append(str);
				writer.append("\n");
		    }
		    writer.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	}
	
	
	public Collection<Training> getTrainingsByObject(String objId){
		List<Training> trainings=new ArrayList<Training>();
		for(Training t: getTrainingCollection()) {
			if(t.getsObject().equals(objId)) {
				trainings.add(t);
			}
		}
		return trainings;
	}
	
	public Collection<Training> getTrainingsByUser(String objId){
		List<Training> trainings=new ArrayList<Training>();
		for(Training t: trainingCollection.values()) {
			if(t.getsObject().equals(objId)) {
				trainings.add(t);
			}
		}
		return trainings;
	}


	public Collection<Training> getTrainingCollection() {
		return trainingCollection.values();
	}
	public Collection<ScheduledTraining> getScheduledTrainingCollection() {
		return scheduledTrainingCollection.values();
	}


	public void setTrainingCollection(HashMap<Integer, Training> trainingCollection) {
		this.trainingCollection = trainingCollection;
	}


	public String getTrainingFilepath() {
		return trainingFilepath;
	}


	public void setTrainingFilepath(String trainingFilepath) {
		this.trainingFilepath = trainingFilepath;
	}
	
	private void loadTrainings() {
		BufferedReader in = null;
		try {
			File file = new File(trainingFilepath + "/trainings.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line="";
			StringTokenizer st;
			String id="",name="",type="", object="", duration="",trainerID="",description="",image="";
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ",");
				
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					name = st.nextToken().trim();
					type = st.nextToken().trim();
					object = st.nextToken().trim();
					duration = st.nextToken().trim();
					trainerID = st.nextToken().trim();
					description = st.nextToken().trim();
					image = st.nextToken().trim();
				}
				Training t=new Training(id,name,type,object,duration,trainerID,description,image);
				addTraining(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}}
		}
	
	private void loadScheduledTrainings() {
		BufferedReader in = null;
		try {
			File file = new File(trainingFilepath + "/scheduledTrainings.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line="";
			StringTokenizer st;
			String id="",dateTime="",training="",user="",trainer="",sObject="",type="";
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ",");
				
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					dateTime = st.nextToken().trim();
					training = st.nextToken().trim();
					user = st.nextToken().trim();
					trainer = st.nextToken().trim();
					sObject = st.nextToken().trim();
					type = st.nextToken().trim();
				}
				ScheduledTraining t=new ScheduledTraining(id,dateTime,training,user,trainer,sObject,type);
				addScheduledTraining(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}}
		}

	public Collection<ScheduledTraining> getScheduledTrainingsByCustomer(String username) {
		List<ScheduledTraining> ret=new ArrayList<>();
		for(ScheduledTraining t: getScheduledTrainingCollection()) {
			if(t.getUser().equals(username)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	public Collection<ScheduledTraining> getPersonalTrainingsByTrainer(String username) {
		List<ScheduledTraining> ret=new ArrayList<>();
		for(ScheduledTraining t: getScheduledTrainingCollection()) {
			if(t.getTrainer().equals(username) && t.getType()==TrainingType.PERSONAL) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	public Collection<ScheduledTraining> getGroupTrainingsByTrainer(String username) {
		List<ScheduledTraining> ret=new ArrayList<>();
		for(ScheduledTraining t: getScheduledTrainingCollection()) {
			if(t.getTrainer().equals(username) && t.getType()==TrainingType.GROUP) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	

	public Collection<ScheduledTraining> cancelTraining(ScheduledTraining t) {
		LocalDateTime cancelUntil=t.getDateTime().minusDays(2);
		LocalDateTime now=LocalDateTime.now();
		if(now.isAfter(cancelUntil)) return getPersonalTrainingsByTrainer(t.getTrainer());
		else {
			scheduledTrainingCollection.remove(t.getId());
			saveScheduled();
		}
		return getPersonalTrainingsByTrainer(t.getTrainer());
	}

	public Collection<String> getTypes() {
		List<String> ret=new ArrayList<>();
		for(TrainingType t:TrainingType.values()) {
			ret.add(t.toString());
		} return ret;
	}

	public Collection<String> getTrainers(String sportsObjectID) {
		List<String> ret=new ArrayList<>();
		for(ScheduledTraining t:getScheduledTrainingCollection()) {
			if(t.getsObject().equals(sportsObjectID)) {
				boolean alreadyExists=false;
				for(String trainer:ret) {
					if (trainer.equals(t.getTrainer())) alreadyExists=true;
				}
				
				if(!alreadyExists) ret.add(t.getTrainer());
			}
		}
		
		return ret;
	}
}