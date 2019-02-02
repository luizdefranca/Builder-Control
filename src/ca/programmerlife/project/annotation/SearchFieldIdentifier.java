package ca.programmerlife.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface SearchFieldIdentifier {

	String descriptionField(); //description of the field to the screen
	String SearchField(); //name of the field in the database
	int mainPosition() default 1000;  //main position in the combobox
	
	
}
