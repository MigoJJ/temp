import java.util.Arrays;

public class GDSLaboratoryDataModify {

    public static void main(String textFromInputArea) {

        if (textFromInputArea.contains("GOT")) {
            String result = "\n[ GOT - GPT - ALP - GGT - T-Bil - Alb ]";
            GDSLaboratoryDataGUI.appendTextAreas(result);
        }
        if (textFromInputArea.contains("T-Chol")) {
            String result = "\n[T-Chol - HDL - Triglyceride - LDL ]; ";
            GDSLaboratoryDataGUI.appendTextAreas(result);
        }
        if (textFromInputArea.contains("HGB")) {
            String result = "\n[ Hb - WBC - Platelet]; ";
            GDSLaboratoryDataGUI.appendTextAreas(result);
        }
        if (textFromInputArea.contains("Glucose")) {
            String result = "\n[Glucose - HbA1c ]; ";
            GDSLaboratoryDataGUI.appendTextAreas(result);
        }
        if (textFromInputArea.contains("free T-4")) {
            String result = "\n[ T3 - free-T4 - TSH ]; ";
            GDSLaboratoryDataGUI.appendTextAreas(result);
        }

        
        
    }
}
