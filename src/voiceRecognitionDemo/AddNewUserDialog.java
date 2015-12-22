package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddNewUserDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text newUsername;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddNewUserDialog(Shell parent, int style) {
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
		shell.setSize(234, 132);
		shell.setText(getText());
		
		Label lblPleaseEnterThe = new Label(shell, SWT.NONE);
		lblPleaseEnterThe.setBounds(10, 10, 203, 14);
		lblPleaseEnterThe.setText("Please enter the new user's name");
		
		newUsername = new Text(shell, SWT.BORDER);
		newUsername.setBounds(20, 30, 183, 19);
		
		Button btnAddUserNext = new Button(shell, SWT.NONE);
		btnAddUserNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				VoiceRecognition VR = new VoiceRecognition();				
				VR.addToArchive(shell, newUsername.getText());				
			}
		});
		btnAddUserNext.setBounds(129, 72, 95, 28);
		btnAddUserNext.setText("Next");

	}
}
