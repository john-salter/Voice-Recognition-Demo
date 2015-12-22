/**
 * 
 */
package voiceRecognitionDemo;

import org.eclipse.swt.widgets.Shell;
import java.io.*;
/**
 * @author johnsalter
 *
 */
public class VoiceRecognition {

	/**
	 * currently everything is being printed to the console. I'm not quite sure how I want to display the info so I'll keep it this way
	 * for now. In reality, this is just a demo shell to show off the voice recognition, so pretty visuals aren't the primary focus	 * 
	 */

	public void addToArchive(Shell parent, String string) {
		// TODO Auto-generated constructor stub		
		//Take care of the initializations
		String baseDir = System.getProperty("user.dir");		
		String archiveDir = baseDir+"/UserArchive/";		
		
		File newUserPath = new File("");
		
			String newUser = string; // User inputs their username									
			newUserPath = new File(archiveDir+newUser+".user");		
			if (newUserPath.isFile()) {
				addUserError err = new addUserError(parent, 0);
				err.open();
				CaptureAudioWindow.bringToFront(parent);
				parent.setVisible(false);
				return;				
			}
			
			CaptureAudioWindow caw = new CaptureAudioWindow(parent, 0);
			caw.open(newUserPath);
	}	
}
