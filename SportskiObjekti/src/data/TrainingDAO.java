package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import beans.SportsObject;
import beans.Training;
import data.utils.ObjectType;


public class TrainingDAO{
	private HashMap<Integer, Training> trainingCollection=new HashMap<>();
	private String trainingFilepath="";
	
	
	public TrainingDAO(String trainingFilepath) {
		super();
		this.trainingFilepath = trainingFilepath;
		loadTrainings();
	}
	
	public void addTraining(Training t) {
		int maxId = 0;
		maxId=getTrainingCollection().size();
		maxId++;
		getTrainingCollection().put(maxId, t);
	}


	public HashMap<Integer, Training> getTrainingCollection() {
		return trainingCollection;
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
			String name="";
			String type="";
			String object="";
			String duration="";
			String trainerID="";
			String description="";
			String image="";
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
}