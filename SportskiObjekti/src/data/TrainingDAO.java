package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.ScheduledTraining;
import beans.SportsObject;
import beans.Training;
import data.utils.ObjectType;


public class TrainingDAO{
	private HashMap<Integer, Training> trainingCollection=new HashMap<>();
	private HashMap<Integer, ScheduledTraining> scheduledTrainingCollection=new HashMap<>();
	private String trainingFilepath="";
	
	
	public TrainingDAO(String trainingFilepath) {
		super();
		this.trainingFilepath = trainingFilepath;
		loadTrainings();
		loadScheduledTrainings();
 	}
	
	public void addTraining(Training t) {
		int maxId = 0;
		maxId=getTrainingCollection().size();
		maxId++;
		trainingCollection.put(maxId, t);
	}
	
	public void addScheduledTraining(ScheduledTraining t) {
		int maxId = 0;
		maxId=getScheduledTrainingCollection().size();
		maxId++;
		scheduledTrainingCollection.put(maxId, t);
	}
	
	public Collection<Training> getTrainingsByObject(String objId){
		List<Training> trainings=new ArrayList<Training>();
		for(Training t: trainingCollection.values()) {
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
			String name="",type="", object="", duration="",trainerID="",description="",image="";
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ",");
				
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					type = st.nextToken().trim();
					object = st.nextToken().trim();
					duration = st.nextToken().trim();
					trainerID = st.nextToken().trim();
					description = st.nextToken().trim();
					image = st.nextToken().trim();
				}
				String imgFilepath=trainingFilepath+"/images/"+image;
				Training t=new Training(name,type,object,duration,trainerID,description,imgFilepath);
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
			String dateTime="",training="",user="",trainer="",sObject="";
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ",");
				
				while (st.hasMoreTokens()) {
					dateTime = st.nextToken().trim();
					training = st.nextToken().trim();
					user = st.nextToken().trim();
					trainer = st.nextToken().trim();
					sObject = st.nextToken().trim();
				}
				ScheduledTraining t=new ScheduledTraining(dateTime,training,user,trainer,sObject);
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
	
	public Collection<ScheduledTraining> getScheduledTrainingsByTrainer(String username) {
		List<ScheduledTraining> ret=new ArrayList<>();
		for(ScheduledTraining t: getScheduledTrainingCollection()) {
			if(t.getTrainer().equals(username)) {
				ret.add(t);
			}
		}
		return ret;
	}
}