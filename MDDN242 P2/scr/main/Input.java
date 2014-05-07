package main;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Input implements AWTEventListener{

	private static final long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK | AWTEvent.KEY_EVENT_MASK;
	private InputData data;
	
	Input(){
		Toolkit.getDefaultToolkit().addAWTEventListener(this, eventMask);
		data = new InputData();
	}

	@Override
	public void eventDispatched(AWTEvent e) {
		if(!(e instanceof InputEvent)) return;
		synchronized(data){
			data.event = (InputEvent) e;
			data.oc = ((InputEvent)e).getComponent();

			Object s = e.getSource();
			if(s == Main.getMainWindow()){ process(e); }
			else if(s instanceof java.awt.Canvas){}
			else return;

			switch(e.getID()){
			case MouseEvent.MOUSE_CLICKED		: mouseClicked((MouseEvent)e);			break;
			case MouseEvent.MOUSE_DRAGGED		: mouseDragged((MouseEvent)e);			break;
			case MouseEvent.MOUSE_ENTERED		: mouseEntered((MouseEvent)e);			break;
			case MouseEvent.MOUSE_EXITED		: mouseExited((MouseEvent)e);			break;
			case MouseEvent.MOUSE_MOVED			: mouseMoved((MouseEvent)e);			break;
			case MouseEvent.MOUSE_PRESSED		: mousePressed((MouseEvent)e);			break;
			case MouseEvent.MOUSE_RELEASED		: mouseReleased((MouseEvent)e);			break;
			case MouseEvent.MOUSE_WHEEL	: mouseWheelMoved((MouseWheelEvent)e);	break;
			case KeyEvent.KEY_PRESSED			: keyPressed((KeyEvent)e);				break;
			case KeyEvent.KEY_RELEASED			: keyReleased((KeyEvent)e);				break;
			case KeyEvent.KEY_TYPED				: keyTyped((KeyEvent)e);				break;
			default: return;
			}
		}
	}

	private void process(AWTEvent e){
		e.setSource(Main.getCanvas());
	}

	private void mouseClicked(MouseEvent e) {
		Tool.getCurrentTool().mouseClicked(data);
	}

	private void mouseEntered(MouseEvent e) {
		Tool.getCurrentTool().mouseEntered(data);
	}

	private void mouseExited(MouseEvent e) {
		Tool.getCurrentTool().mouseExited(data);
	}

	private void mousePressed(MouseEvent e) {
		int i = e.getButton() - 1;
		if(i >= 0 && i < data.mouseButtonDown.length){
			data.mouseButtonDown[i] = true;
		}

		Tool.getCurrentTool().mousePressed(data);
	}

	private void mouseReleased(MouseEvent e) {
		int i = e.getButton() - 1;
		if(i >= 0 && i < data.mouseButtonDown.length){
			data.mouseButtonDown[i] = false;
		}

		Tool.getCurrentTool().mouseReleased(data);
	}

	private void mouseDragged(MouseEvent e) {
		data.updateMouseLocation(e);
		Tool.getCurrentTool().mouseDragged(data);
	}

	private void mouseMoved(MouseEvent e) {
		data.updateMouseLocation(e);
		Tool.getCurrentTool().mouseMoved(data);
	}

	private void mouseWheelMoved(MouseWheelEvent e) {		
		Tool.getCurrentTool().mouseWheelMoved(data);
	}

	private void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ALT:		data.modifiersDown[InputData.ALT] = true;		break;
		case KeyEvent.VK_CONTROL:	data.modifiersDown[InputData.CTRL] = true;		break;
		case KeyEvent.VK_SHIFT:		data.modifiersDown[InputData.SHIFT] = true;		break;
		}
		Tool.getCurrentTool().keyPressed(data);
	}

	private void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_ALT:		data.modifiersDown[InputData.ALT] = false;		break;
		case KeyEvent.VK_CONTROL:	data.modifiersDown[InputData.CTRL] = false;		break;
		case KeyEvent.VK_SHIFT:		data.modifiersDown[InputData.SHIFT] = false;	break;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			if(Main.getCanvas() != null){
				Main.getCanvas().deleteSelectedEntity();
			}
		}
		
		Tool.getCurrentTool().keyReleased(data);
	}

	private void keyTyped(KeyEvent e) {
		Tool.getCurrentTool().keyTyped(data);
	}
}