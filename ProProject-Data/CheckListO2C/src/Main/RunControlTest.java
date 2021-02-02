package Main;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import com.proma.controler.ControlProcessor;
import com.proma.model.CheckListComposants;
import com.proma.model.Operator;

public class RunControlTest {

//	public static CheckListComposants cl1 = new CheckListComposants(
//			"StartDate", 1, 4445, "1001A002", new Operator("OperatorName",
//					"OperatorSurname", 2), 1);
	public static String[] composantsCheckResult1 = { "S", "M", "OK", "OK",
			"OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK",
			"OK", "OK", "OK", "OK", "OK", "OK", "OK" };
//	public static String[] composantsCheckResult2 = { "OK", "OK", "OK", "OK",
//		"OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK", "OK",
//		"OK", "OK", "OK", "OK", "OK", "OK", "OK" };

	public static void main(String[] args) {
		
		// Create parameter for control object
		String startDate = getStartDate();
		int shift = getShift();
		int numChassis = 4449;
		String gravure = "1001A007";
		Operator ops = new Operator("Mounia", "Surname", 1);
		int status = 1;
		
		// Create Control object
		CheckListComposants cl1 = new CheckListComposants(startDate, shift, numChassis, gravure, ops, status);
		
		// Assigne the control object to the control processor
		ControlProcessor processor = ControlProcessor.getInstance(cl1);
		
		// Create the control as control file
		processor.createControl();

		// fill in the control checks
		for (int i = 0; i < composantsCheckResult1.length; i++) {
			cl1.setComposants(i + 1, composantsCheckResult1[i]);
		}
		
		// save the checked components to the file
		processor.saveControl();
		
		// check the controled components if every thing is ok
		boolean valid = processor.validate();
		if (!valid){
			JOptionPane.showMessageDialog(null, processor.getFailureCount()+" Point non controlés\nRetournez et completez le controle svp!");
			System.out.println(processor.getFailureCount()+" Point non controlés");
		}
		
//		processor.createControl();
//
//
//		for (int i = 0; i < composantsCheckResult2.length; i++) {
//			cl1.setComposants(i + 1, composantsCheckResult2[i]);
//		}
//		
//		processor.saveControl();
//		processor.validate();

	}

	private static int getShift() {
		int shift = 0;
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH");
	    String formattedDate = currentTime.format(forma);
	    int time = Integer.parseInt(formattedDate);
		if (time >= 6 && time < 14){
			shift = 1;
		}else if (time >= 14 && time < 22){
			shift = 2;
		}else{
			shift = 3;
		}
		return shift;
	}

	private static String getStartDate() {
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		String formatedDate = formater.format(date);
		return formatedDate;
	}
}
