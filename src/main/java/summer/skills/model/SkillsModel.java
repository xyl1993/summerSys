package summer.skills.model;

import java.util.Date;

public class SkillsModel implements java.io.Serializable{

	private static final long serialVersionUID = 6330324094758317801L;

	private String id;
	
	private String text;
	
	private String creater;
	
	private Date createrTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}
	
	
}
