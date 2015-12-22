package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class CaptureAudioWindow extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @param newUserPath 
	 */
	public CaptureAudioWindow(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(File newUserPath) {
		createContents(newUserPath);
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
	 * @param newUserPath 
	 */
	private void createContents(File newUserPath) {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 145);
		shell.setText(getText());
		
		Button btnRecord = new Button(shell, SWT.NONE);
		btnRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);		        
		        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		        for (Mixer.Info info: mixerInfos){
		        	Mixer m = AudioSystem.getMixer(info);
		        	Line.Info[] lineInfos = m.getSourceLineInfo();
		        	for (Line.Info lineInfo:lineInfos){
		        		System.out.println (info.getName()+"---"+lineInfo);
		        		Line line = null;
		        		try {
		        			line = m.getLine(lineInfo);
		        		} catch (LineUnavailableException e1) {
		        			// TODO Auto-generated catch block
		        			e1.printStackTrace();
		        		}
		        		System.out.println("\t-----"+line);
		        	}
		        	lineInfos = m.getTargetLineInfo();
		        	for (Line.Info lineInfo:lineInfos){
		        		System.out.println (m+"---"+lineInfo);
		        		Line line = null;
		        		try {
		        			line = m.getLine(lineInfo);
		        		} catch (LineUnavailableException e1) {
		        			// TODO Auto-generated catch block
		        			e1.printStackTrace();
		        		}
		        		System.out.println("\t-----"+line);
		        	}
		        }
		    	DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
		    	if (!AudioSystem.isLineSupported(info)) {
		    	    // Handle the error ... 

		    	}
		    	// Obtain and open the line.
		    	try {		 
		    		TargetDataLine line = AudioSystem.getTargetDataLine(format);
		    	    line.open(format);
		    	 // Assume that the TargetDataLine, line, has already
			    	// been obtained and opened.			    	
			    	byte[] data = new byte[line.getBufferSize() / 5];
			    	// Open the window with the stop button			    	
			    	// Begin audio capture.
			    	line.start();
			    	ByteArrayOutputStream out  = new ByteArrayOutputStream();
			        int numBytesRead;
			        boolean stopped = false;
			        long startTime = System.currentTimeMillis();
			        while(!stopped){			        	
			        	// Read the next chunk of data from the TargetDataLine.
			        	numBytesRead =  line.read(data, 0, data.length);
			        	// Save this chunk of data.
			        	out.write(data, 0, numBytesRead);			        	
			        	if(System.currentTimeMillis() > startTime+5000) {
			        		stopped = true; // after 5 seconds, stop recording				        	
			        	}			        	
			        }			        
			        try (FileOutputStream writer = new FileOutputStream(newUserPath)) {
						writer.write(data);							
					}
					catch (IOException e1) {
						// print out why the process failed
						e1.printStackTrace();
					}
			        FinishedRecording FR = new FinishedRecording(shell, 0); // open the finished recording window
					FR.open();
	        		out.close();
			    				    	
		    	} catch (LineUnavailableException ex) {
		    	    // Handle the error ... 
		    	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		    	
			}			
		});
		btnRecord.setBounds(10, 87, 95, 28);
		btnRecord.setText("Record");
		
		Label lblWhenYoureReady = new Label(shell, SWT.NONE);
		lblWhenYoureReady.setBounds(10, 10, 430, 105);
		lblWhenYoureReady.setText("When you're ready, hit record to begin recording your passphrase. \nAfter 5 seconds, the recording stops, and your profile will be saved \nautomatically.\n\nA Window will open when recording has finished.");

	}
	public static void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
	}
}
