package main;

public interface InputListener {

	public void mouseClicked(InputData data);
	public void mouseEntered(InputData data);
	public void mouseExited(InputData data);
	public void mousePressed(InputData data);
	public void mouseReleased(InputData data);
	public void mouseDragged(InputData data);
	public void mouseMoved(InputData data);
	public void mouseWheelMoved(InputData data);
	public void keyPressed(InputData data);
	public void keyReleased(InputData data);
	public void keyTyped(InputData data);
}
