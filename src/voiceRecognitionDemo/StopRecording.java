package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StopRecording extends Dialog {

	protected Object result;
	protected Shell shell;
	public volatile boolean threadRunning;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public StopRecording(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(273, 94);
		shell.setText(getText());
		
		Button btnStop = new Button(shell, SWT.NONE);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				threadRunning = false;
				shell.setVisible(false);				
			}
		});
		btnStop.setBounds(82, 34, 95, 28);
		btnStop.setText("STOP");
		
		Label lblPressStopWhen = new Label(shell, SWT.NONE);
		lblPressStopWhen.setBounds(10, 10, 325, 14);
		lblPressStopWhen.setText("Press Stop when you are finished recording");

	}

}
