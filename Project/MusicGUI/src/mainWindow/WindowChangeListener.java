package mainWindow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WindowChangeListener implements ChangeListener {

	private String methodName;
	private Object callOn;
	
	public WindowChangeListener(String methodName, Object callOn)
	{
		this.methodName = methodName;
		this.callOn = callOn;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			Method method = callOn.getClass().getDeclaredMethod(methodName);
			method.invoke(callOn);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
