package ca.programmerlife.project.bean.general;

import java.io.Serializable;
import java.util.Comparator;

public class SearchFieldObject implements Serializable, Comparator<SearchFieldObject>{

	private static final long serialVersionUID = -7349645206392903013L;

	private String description;
	private String dataBaseField;
	private Object classType;
	private Integer mainPosition;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataBaseField() {
		return dataBaseField;
	}

	public void setDataBaseField(String dataBaseField) {
		this.dataBaseField = dataBaseField;
	}

	public Object getClassType() {
		return classType;
	}

	public void setClassType(Object classType) {
		this.classType = classType;
	}

	public Integer getMainPosition() {
		return mainPosition;
	}

	public void setMainPosition(Integer mainPosition) {
		this.mainPosition = mainPosition;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classType == null) ? 0 : classType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchFieldObject other = (SearchFieldObject) obj;
		if (classType == null) {
			if (other.classType != null)
				return false;
		} else if (!classType.equals(other.classType))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public int compare(SearchFieldObject o1, SearchFieldObject o2) {
		
		return ((SearchFieldObject)o1).getMainPosition()
				.compareTo(((SearchFieldObject)o2).getMainPosition());
	}

}
