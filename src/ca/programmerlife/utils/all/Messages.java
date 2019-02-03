package ca.programmerlife.utils.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Messages extends FacesContext implements Serializable{

	private static final long serialVersionUID = 4276292758707831163L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Messages() {
		
	}
	
	public static void msb(String message) {
		if(isAValidContext()) {
			getFacesContext().addMessage("msg", new FacesMessage(message));
			
		}
	}
	
	public static void success() {
		msgSeverityInfor(Constant.SUCCESSFUL_OPERATION);
	}
	
	public static void responseOperation(PersistenceStatus status) {
//		if(status != null && status.equals(PersistenceStatus.SUCCESS)) {
//			success();
//		} else if (status != null && status.equals(PersistenceStatus.REFERENCED_OBJECT)) {
//			msgSeverityFatal(PersistenceStatus.REFERENCED_OBJECT.toString());
//		}  else {
//		OperationError();
//	}
			
		if (status!= null) {
			switch (status) {
			case SUCCESS:
				success();
				break;
			case REFERENCED_OBJECT:
				msgSeverityFatal(PersistenceStatus.REFERENCED_OBJECT.toString());
				break;
			default:
				OperationError();
				break;
			}
		}
	}
	public static void OperationError() {
		if(isAValidContext()) {
			msgSeverityFatal(Constant.WRONG_OPERATION);
		}
	}
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	private static boolean isAValidContext() {
		return getFacesContext() != null;
	}
	
	public static void msgSeverityWarning(String message) {
		if(isAValidContext()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
		}
	}
	public static void msgSeverityFatal(String message) {
		if(isAValidContext()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
		}
	}
	public static void msgSeverityInfor(String message) {
		if(isAValidContext()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
		}
	}
	public static void msgSeverityError(String message) {
		if(isAValidContext()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
		}
	}
	
}
