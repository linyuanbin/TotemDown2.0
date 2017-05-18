package getMarkUtil;

import com.google.gson.JsonObject;

public class Attribute {
	
	private JsonObject attribute;
	private JsonObject position;
	private JsonObject content;
	public JsonObject getAttribute() {
		return attribute;
	}
	public void setAttribute(JsonObject attribute) {
		this.attribute = attribute;
	}
	public JsonObject getPosition() {
		return position;
	}
	public void setPosition(JsonObject position) {
		this.position = position;
	}
	public JsonObject getContent() {
		return content;
	}
	public void setContent(JsonObject content) {
		this.content = content;
	}
	

}
