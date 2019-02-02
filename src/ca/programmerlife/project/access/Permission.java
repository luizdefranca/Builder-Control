package ca.programmerlife.project.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permission {
	ADMIN("ADMIN", "Administrator"),
	USER("USER", "Standard user"),
	RECORD_ACCESS("RECORD_ACCESS", "Access to record new data"),
	FINANCIAL_ACCESS("FINANCIAL_ACCESS", "Access to record financial registries"),
	MESSAGE_ACCESS("MESSAGE_ACCESS", "Access to received message");
	
	private String value;
	private String description;
	
	Permission() {
		value = "";
		description = "";
	}
	
	Permission(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		
		return value;
	}
	
	public static List<Permission> getListPermission(){
		List<Permission> list = new ArrayList<>();
		for (Permission permission: Permission.values()) {
			list.add(permission);
		}
		
		Collections.sort(list, new Comparator<Permission>() {

			@Override
			public int compare(Permission o1, Permission o2) {
				
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
	
		});
		
		return list;
	}
	
}
