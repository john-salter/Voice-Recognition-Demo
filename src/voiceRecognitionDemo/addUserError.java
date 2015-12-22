package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class addUserError extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public addUserError(Shell parent, int style) {
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
		shell.setSize(450, 135);
		shell.setText(getText());
		
		Label lblErrorUsernameAlready = new Label(shell, SWT.NONE);
		lblErrorUsernameAlready.setBounds(10, 10, 430, 14);
		lblErrorUsernameAlready.setText("Error: Username already exists, please go back and choose a unique username");
		
		Button btnGoBack = new Button(shell, SWT.NONE);
		btnGoBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
			}
		});
		btnGoBack.setBounds(10, 75, 95, 28);
		btnGoBack.setText("Go Back");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(345, 75, 95, 28);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
			}
		});
		btnCancel.setText("Cancel");

	}

}
