package summer.module.model;

import java.util.List;

public class TreeModule {

	private String id;
	
	private String text;
	private String iconCls;
	private String state;
	private String url;
	private List<TreeModule> children;
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<TreeModule> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModule> children) {
		this.children = children;
	}
	
}
