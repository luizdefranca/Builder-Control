package ca.programmerlife.project.bean.general;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

@SuppressWarnings("unchecked")
public class ViewScope implements Scope, Serializable{

	
	private static final long serialVersionUID = -5821280307733641530L;
	
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callBacks";
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		
		Object instance = getViewMap().get(name);
		if(instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}
	@Override
	public String getConversationId() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(facesRequestAttributes.getSessionId()); //Session Id
		stringBuilder.append("-");
		stringBuilder.append(facesContext.getViewRoot().getViewId()); //View Id
		return stringBuilder.toString();
	}
	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {

		Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if(callBacks != null) {
			callBacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}
	}
	@Override
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);
		if(instance != null) {
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if(callBacks != null) {
				callBacks.remove(name);
			}
		}
		return instance;
	}
	@Override
	public Object resolveContextualObject(String name) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(name);
	}

	private Map<String, Object> getViewMap(){
		//Create a instance of the current FacesContext
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		if(currentInstance != null) {
			UIViewRoot viewRoot = currentInstance.getViewRoot(); //Root component associated with the request
			Map<String, Object> viewMap = viewRoot.getViewMap(); //Map which works as interface to stock the data
			return viewMap;
		} else {
			return new HashMap<String,Object>();
		}
		
	}
	
	

}
