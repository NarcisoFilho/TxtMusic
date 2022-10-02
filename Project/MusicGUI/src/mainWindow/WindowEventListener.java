package mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WindowEventListener implements ActionListener {

	private String methodName;
	private Object callOn;
	
	public WindowEventListener(String methodName, Object callOn)
	{
		this.methodName = methodName;
		this.callOn = callOn;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
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