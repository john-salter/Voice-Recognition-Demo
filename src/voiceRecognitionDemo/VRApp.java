package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VRApp {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VRApp window = new VRApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display.setAppName("Voice Recognition Demo");
		Display display = Display.getDefault();
		
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Voice Recognition Demo");
		
		Label lblThisIsA = new Label(shell, SWT.NONE);
		lblThisIsA.setBounds(10, 10, 430, 189);
		lblThisIsA.setText("This is a demo application for some voice detection software I wrote. \nYou can use this software in a couple ways. \nAs a password system: each user creates a profile and speaks their \nown pass phrase (it can be whatever they want, but the longer and \nmore varied it is, the more secure it is). Even if all users in the database \nused the same passphrase, the program should be able to tell them apart.");
		
		Button btnNewUser = new Button(shell, SWT.NONE);
		btnNewUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewUserDialog d = new AddNewUserDialog(shell, 0);
				d.open();
			}
		});
		btnNewUser.setBounds(10, 240, 120, 28);
		btnNewUser.setText("Add New User");
		
		Button btnVoiceRecognition = new Button(shell, SWT.NONE);
		btnVoiceRecognition.setBounds(136, 240, 144, 28);
		btnVoiceRecognition.setText("Voice Recognition");
		
		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				System.exit(0);
			}
		});
		btnQuit.setBounds(286, 240, 95, 28);
		btnQuit.setText("Quit");

	}
}
